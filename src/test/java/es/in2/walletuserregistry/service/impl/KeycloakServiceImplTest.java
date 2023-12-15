package es.in2.walletuserregistry.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.in2.walletuserregistry.configuration.properties.KeycloakProperties;
import es.in2.walletuserregistry.domain.UserRegistryRequest;
import es.in2.walletuserregistry.utils.HttpUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static es.in2.walletuserregistry.utils.HttpUtils.postRequestWithResponse;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class KeycloakServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private KeycloakProperties keycloakProperties;

    @InjectMocks
    private KeycloakServiceImpl keycloakService;

    @Test
    void testUserRegistry() {
        // Arrange
        UserRegistryRequest mockUserRegistryRequest = UserRegistryRequest.builder()
                .username("j.doe")
                .password("1234")
                .email("john.doe@example.com")
                .build();
        // Mock
        when(keycloakProperties.url()).thenReturn("https://example.com");
        when(keycloakProperties.realm()).thenReturn("example");
        when(keycloakProperties.clientSecret()).thenReturn("1234");
        when(keycloakProperties.clientId()).thenReturn("client");
        // Mock the postRequest method
        try (MockedStatic<HttpUtils> httpUtilsMockedStatic = Mockito.mockStatic(HttpUtils.class)) {
            when(postRequestWithResponse(anyString(), anyString(), anyList(), anyString()))
                    .thenReturn(Mono.empty());
            // Act
            Mono<String> result = keycloakService.registerUserInKeycloak("processId", mockUserRegistryRequest);
            // Assert
            result.as(StepVerifier::create).verifyComplete();
        }
    }

}


