package es.in2.walletuserregistry.controller;

import es.in2.walletuserregistry.domain.UserRegistryRequest;
import es.in2.walletuserregistry.facade.UserRegistryFacadeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRegistryFacadeService userRegistryFacadeService;

    @InjectMocks
    private UserController userController;

    @Test
    void testRegisterUser() {
        // Arrange
        String userRegistryRequest = """
                {
                  "username": "j.doe",
                  "email": "john.doe@example.com",
                  "password": "1234"
                }
                """;
        // Mock
        when(userRegistryFacadeService.createUser(anyString(), any(UserRegistryRequest.class)))
                .thenReturn(Mono.empty());
        // Act & Assert
        WebTestClient
                .bindToController(userController)
                .build()
                .post()
                .uri("/api/v2/users")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .bodyValue(userRegistryRequest)
                .exchange()
                .expectStatus().isCreated();
    }

}


