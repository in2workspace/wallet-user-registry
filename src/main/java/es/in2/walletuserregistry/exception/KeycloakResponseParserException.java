package es.in2.walletuserregistry.exception;

public class KeycloakResponseParserException extends RuntimeException {
    public KeycloakResponseParserException(String message) {
        super(message);
    }
    public KeycloakResponseParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
