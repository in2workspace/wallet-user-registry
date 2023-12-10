package es.in2.walletuserregistry.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.in2.walletuserregistry.configuration.properties.KeycloakProperties;
import es.in2.walletuserregistry.domain.UserRegistryRequest;
import es.in2.walletuserregistry.exception.KeycloakResponseParserException;
import es.in2.walletuserregistry.exception.UserAlreadyExists;
import es.in2.walletuserregistry.exception.UserCreationException;
import es.in2.walletuserregistry.exception.UserNotFoundException;
import es.in2.walletuserregistry.service.KeycloakService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static es.in2.walletuserregistry.utils.HttpUtils.BEARER_PREFIX;
import static es.in2.walletuserregistry.utils.HttpUtils.postRequestWithResponse;
import static org.keycloak.OAuth2Constants.GRANT_TYPE;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {

    private final ObjectMapper objectMapper;
    private final KeycloakProperties keycloakProperties;

    @Override
    @Transactional
    public Mono<String> registerUserInKeycloak(String processId, UserRegistryRequest userRegistryRequest) {
        // Create CredentialRepresentation
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userRegistryRequest.password());
        credentialRepresentation.setTemporary(false);
        List<CredentialRepresentation> credentials = Collections.singletonList(credentialRepresentation);
        // Create User Representation
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userRegistryRequest.username());
        userRepresentation.setEmail(userRegistryRequest.email());
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(credentials);
        // Get Keycloak Client Token
        return getKeycloakClientToken(processId)
                // Create Keycloak Builder with Keycloak Client Token
                .flatMap(token -> Mono.just(KeycloakBuilder.builder()
                        .serverUrl(keycloakProperties.url())
                        .realm(keycloakProperties.realm())
                        .authorization(BEARER_PREFIX + token)
                        .build())
                )
                // Register User in Keycloak
                .flatMap(keycloak -> userRegistryRequest(processId, keycloak, userRepresentation)
                        // Get User Representation ID by Username
                        .then(getUserRepresentationIdByUsername(keycloak, userRegistryRequest.username()))
                );
    }

    private Mono<String> getKeycloakClientToken(String processId) {
        // URL
        String url = keycloakProperties.url() + "/realms/" + keycloakProperties.realm() + "/protocol/openid-connect/token";
        // Headers
        List<Map.Entry<String, String>> headers = new ArrayList<>();
        headers.add(new AbstractMap.SimpleEntry<>(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED));
        // Body
        String body = "grant_type=" + URLEncoder.encode(GRANT_TYPE, StandardCharsets.UTF_8) +
                "&client_id=" + URLEncoder.encode(keycloakProperties.clientId(), StandardCharsets.UTF_8) +
                "&client_secret=" + URLEncoder.encode(keycloakProperties.clientSecret(), StandardCharsets.UTF_8);
        return postRequestWithResponse(processId, url, headers, body)
                .flatMap(response -> parseKeycloakClientTokenResponse(processId, response))
                .doOnError(e -> log.error("ProcessID: {}, Error getting Keycloak client token: {}", processId, e.getMessage()));
    }

    private Mono<String> parseKeycloakClientTokenResponse(String processId, String response) {
        try {
            Map<String, Object> jsonObject = objectMapper.readValue(response, new TypeReference<>() {
            });
            return Mono.just(jsonObject.get("access_token").toString());
        } catch (JsonProcessingException e) {
            log.error("ProcessID: {}, Error parsing Keycloak response: {}", processId, e.getMessage());
            throw new KeycloakResponseParserException("Error parsing Keycloak response: " + e.getMessage(), e.getCause());
        }
    }

    private Mono<Void> userRegistryRequest(String processId, Keycloak keycloak, UserRepresentation userRepresentation) {
        // Create Keycloak User
        try (Response response = keycloak.realm(keycloakProperties.realm()).users().create(userRepresentation)) {
            log.info("ProcessID: {}, Response {}", processId, response.getStatus());
            String responseBody = response.readEntity(String.class);
            // Check response status
            if (response.getStatus() == 409) {
                return Mono.error(new UserAlreadyExists("User already exists: " + responseBody));
            } else if (response.getStatus() < 200 || response.getStatus() > 299) {
                return Mono.error(new UserCreationException("Response status " + response.getStatus() + ", user not created because " + responseBody));
            } else {
                return Mono.empty();
            }
        }
    }

    private Mono<String> getUserRepresentationIdByUsername(Keycloak keycloak, String username) {
        return Mono.just(keycloak.realm(keycloakProperties.realm()).users().search(username))
                .flatMap(users -> {
                    if (users != null && users.size() == 1) {
                        UserRepresentation user = users.get(0);
                        return Mono.just(user.getId());
                    } else {
                        return Mono.error(new UserNotFoundException("User " + username + " not found"));
                    }
                });
    }

}
