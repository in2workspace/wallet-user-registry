package es.in2.walletuserregistry.exception;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String message) {
        super(message);
    }
    public UserAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
