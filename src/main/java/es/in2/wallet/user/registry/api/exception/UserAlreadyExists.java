package es.in2.wallet.user.registry.api.exception;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
