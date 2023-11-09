package es.in2.wallet.user.registry.api.exception.handler;

import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import es.in2.wallet.user.registry.api.exception.UserCreationException;
import es.in2.wallet.user.registry.api.exception.UserAlreadyExists;
import es.in2.wallet.user.registry.api.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(FailedCommunicationException.class)
    public ResponseEntity<Void> failedCommunicationException(FailedCommunicationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> userNotFoundException(UserNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Void> failedCreatingUserException(UserCreationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<Void> userAlreadyExists(UserAlreadyExists e){
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
