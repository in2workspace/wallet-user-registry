package es.in2.wallet.user.registry.api.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.in2.wallet.user.registry.api.exception.*;
import es.in2.wallet.user.registry.api.model.KeycloakUserDTO;
import es.in2.wallet.user.registry.api.model.UserRequest;
import es.in2.wallet.user.registry.api.service.KeycloakService;
import es.in2.wallet.user.registry.api.util.ApplicationUtils;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static es.in2.wallet.user.registry.api.util.ApiUtils.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.client-id}")
    private String clientId;

    private final ApplicationUtils applicationUtils;


    @Override
    @Transactional
    public String registerUserInKeycloak(UserRequest userRequest) throws UserKeycloakRegistryException {

        try {
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(userRequest.getPassword());
            credentialRepresentation.setTemporary(false);

            List<CredentialRepresentation> credentials = Collections.singletonList(credentialRepresentation);

            return createUserInKeycloak(KeycloakUserDTO.builder()
                    .username(userRequest.getUsername())
                    .email(userRequest.getEmail())
                    .id(null)
                    .credentials(credentials)
                    .enabled(true)
                    .build());

        } catch (Exception e) {
            // FailedCommunicationException, IOException, InterruptedException, UserNotFoundException,
            // FailedCreatingUserException, UserAlreadyExists,
            throw new UserKeycloakRegistryException(e.getMessage(), e.getCause());
        }
    }

    private String getKeycloakClientToken() throws InterruptedException, FailedCommunicationException, IOException {
        String url = keycloakUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";

        List<Map.Entry<String, String>> headers = new ArrayList<>();
        headers.add(new AbstractMap.SimpleEntry<>(CONTENT_TYPE, CONTENT_TYPE_URL_ENCODED_FORM));

        String body = "grant_type=" + URLEncoder.encode(GRANT_TYPE, StandardCharsets.UTF_8) +
                "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8) +
                "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8);

        String response = applicationUtils.postRequest(url, headers, body);

        log.info("Access token: {}", response);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonObject = objectMapper.readValue(response, new TypeReference<>() {
        });

        return jsonObject.get("access_token").toString();
    }

    private String createUserInKeycloak(KeycloakUserDTO userData) throws FailedCommunicationException, IOException, InterruptedException, UserNotFoundException, UserCreationException, UserAlreadyExists {
        String token = getKeycloakClientToken();
        Keycloak keycloak = getKeycloakClient(token);

        UserRepresentation user = toUserRepresentation(userData);

        try (Response response = keycloak.realm(keycloakRealm).users().create(user)) {
            log.info("Response {}", response.getStatus());
            String responseBody = response.readEntity(String.class);

            if (response.getStatus() == 409) {
                throw new UserAlreadyExists("User already exists: " + responseBody);
            } else if (response.getStatus() < 200 || response.getStatus() > 299) {
                throw new UserCreationException("Response status " + response.getStatus() + ", user not created because " + responseBody);
            }
            return getKeycloakIdByUsername(token, user.getUsername());
        }
    }


    private String getKeycloakIdByUsername(String token, String username) throws UserNotFoundException {
        RealmResource realmResource = getKeycloakRealm(token);
        UserResource userResource = getUserResource(realmResource, username);
        String userId = userResource.toRepresentation().getId();
        log.debug(userId);
        return userId;
    }


    private UserRepresentation toUserRepresentation(KeycloakUserDTO userData) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userData.getUsername());
        user.setEmail(userData.getEmail());
        user.setEnabled(userData.getEnabled());
        user.setCredentials(userData.getCredentials());
        return user;
    }

    private RealmResource getKeycloakRealm(String token) {
        return getKeycloakClient(token).realm(keycloakRealm);
    }

    private UserResource getUserResource(RealmResource realmResource, String username) throws UserNotFoundException {
        UsersResource usersResource = realmResource.users();
        List<UserRepresentation> users = usersResource.search(username);
        if (users != null && users.size() == 1) {
            UserRepresentation user = users.get(0);
            return usersResource.get(user.getId());
        } else {
            throw new UserNotFoundException("User " + username + " not found");
        }
    }

    protected Keycloak getKeycloakClient(String token) {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakUrl)
                .realm(keycloakRealm)
                .authorization("Bearer " + token)
                .build();
    }
}
