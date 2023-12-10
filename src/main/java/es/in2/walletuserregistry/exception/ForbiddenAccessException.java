package es.in2.walletuserregistry.exception;

public class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException(String message) {
        super(message);
    }
    public ForbiddenAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
