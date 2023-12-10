package es.in2.walletuserregistry.exception;

public class UserCreationException extends Exception {
    public UserCreationException(String message) {
        super(message);
    }
    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
