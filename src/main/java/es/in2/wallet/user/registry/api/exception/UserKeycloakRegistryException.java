package es.in2.wallet.user.registry.api.exception;

public class UserKeycloakRegistryException extends Exception {
    public UserKeycloakRegistryException(String message) {
        super(message);
    }

    public UserKeycloakRegistryException(String message, Throwable cause) {
        super(message, cause);
    }
}
