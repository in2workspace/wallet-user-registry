package es.in2.walletuserregistry.exception.handler;

import es.in2.walletuserregistry.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ApiError> handleEntityNotFoundException(NoSuchElementException ex,
                                                                  ServerHttpRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = String.valueOf(request.getPath());

        ApiError apiError = new ApiError("EntityNotFoundException", ex.getMessage(), status,
                path);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<ApiError> handleEntityAlreadyExistException(EntityAlreadyExistException ex,
                                                                      ServerHttpRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("EntityAlreadyExistException", ex.getMessage(),
                status, path);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiError> handleEntityUnauthorizedAccessException(UnauthorizedAccessException ex,
                                                                            ServerHttpRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("EntityUnauthorizedAccessException",
                ex.getMessage(), status, path);

        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiError> handleEntityForbiddenAccessException(ForbiddenAccessException ex,
                                                                         ServerHttpRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("EntityForbiddenAccessException", ex.getMessage(),
                status, path);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ApiError> handleEntityRuntimeException(RuntimeException ex,
                                                                 ServerHttpRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("EntityRuntimeException", ex.getMessage(), status,
                path);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(FailedCommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ApiError> handleFailedCommunicationException(FailedCommunicationException ex
            , ServerHttpRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("FailedCommunicationException", ex.getMessage(),
                status, path);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex,
                                                                ServerHttpRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("UserNotFoundException", ex.getMessage(), status,
                path);

        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(UserCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiError> handleFailedCreatingUserException(UserCreationException ex,
                                                                      ServerHttpRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("FailedCreatingUserException", ex.getMessage(),
                status, path);

        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExists ex,
                                                            ServerHttpRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String path = String.valueOf(request.getPath());
        ApiError apiError = new ApiError("UserAlreadyExists", ex.getMessage(), status, path);

        return new ResponseEntity<>(apiError, status);
    }
}