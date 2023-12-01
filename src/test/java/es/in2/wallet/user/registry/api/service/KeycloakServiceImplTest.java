package es.in2.wallet.user.registry.api.service;

import es.in2.wallet.user.registry.api.config.properties.KeycloakProperties;
import es.in2.wallet.user.registry.api.domain.UserRequest;
import es.in2.wallet.user.registry.api.service.impl.KeycloakServiceImpl;
import es.in2.wallet.user.registry.api.util.ApplicationUtils;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class KeycloakServiceImplTest {

    @Mock
    private ApplicationUtils applicationUtils;

    private TestableKeycloakService service;

    // Creating mock objects for Keycloak and its related classes
    private final Keycloak mockKeycloak = Mockito.mock(Keycloak.class);
    private final RealmResource mockRealm = Mockito.mock(RealmResource.class);
    private final UsersResource mockUsers = Mockito.mock(UsersResource.class);
    private final Response mockResponse = Mockito.mock(Response.class);
    private final UserResource mockUserResource= Mockito.mock(UserResource.class);

    @BeforeEach
    public void setUp() {
        String keycloakUrl = "https://exmaple.com";
        String keycloakRealm = "exmaple";
        String clientSecret = "1234";
        String clientId = "client";
        KeycloakProperties keycloakProperties = new KeycloakProperties(keycloakUrl,keycloakRealm,clientSecret,clientId);
        // Initializing the service with test data
        service = new TestableKeycloakService(keycloakProperties,applicationUtils);

        // Creating a user representation for the mock responses
        UserRepresentation user = new UserRepresentation();
        user.setId("123");
        user.setUsername("user");
        user.setEmail("user@example.com");

        List<UserRepresentation> userList = new ArrayList<>();
        userList.add(user);

        // Clearing previous invocations and setting up mock behaviors
        Mockito.clearInvocations(applicationUtils, mockKeycloak, mockRealm, mockUsers, mockResponse, mockUserResource);
        Mockito.when(mockKeycloak.realm(Mockito.anyString())).thenReturn(mockRealm);
        Mockito.when(mockRealm.users()).thenReturn(mockUsers);
        Mockito.when(mockUsers.search("user")).thenReturn(userList);
        Mockito.when(mockUsers.create(Mockito.any())).thenReturn(mockResponse);
        Mockito.when(mockUsers.get("123")).thenReturn(mockUserResource);
        Mockito.when(mockUserResource.toRepresentation()).thenReturn(user);
        Mockito.when(mockResponse.getStatus()).thenReturn(201);
        Mockito.when(mockResponse.readEntity(String.class)).thenReturn("entity response");
    }

    @Test
    void registerUserInKeycloakTest() throws Exception {
        // Setting up mocks and any other necessary setup
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("user");
        userRequest.setEmail("user@example.com");
        userRequest.setPassword("1234");

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJRcXlubjlWcH";
        String response = "{\"access_token\":\"" + token + "\",\"expires_in\":300,\"refresh_expires_in\":1799,\"refresh_token\":\"eyJhbGciOiJIUzI1N\",\"token_type\":\"Bearer\",\"not-before-policy\":0,\"session_state\":\"9381cfbf-446c-4165-9ff8-0fbc7e68ffc9\",\"scope\":\"profile email\"}";

        Mockito.when(applicationUtils.postRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(response);

        // Setting up mocks and any other necessary setup
        service.registerUserInKeycloak(userRequest);

        // Verifying the results and interactions with the mocks
        Mockito.verify(mockUsers).create(Mockito.any());
    }

    // Inner class to make getKeycloakClient method accessible for testing
    private class TestableKeycloakService extends KeycloakServiceImpl {
        public TestableKeycloakService(KeycloakProperties keycloakProperties,ApplicationUtils applicationUtils) {
            super(keycloakProperties,applicationUtils);
        }

        @Override
        protected Keycloak getKeycloakClient(String token) {
            return mockKeycloak;
        }
    }
}


