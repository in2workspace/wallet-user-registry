package es.in2.walletuserregistry.exception.handler;

import es.in2.walletuserregistry.domain.GlobalErrorMessage;
import es.in2.walletuserregistry.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleEntityNotFoundException(NoSuchElementException ex,
                                                                  ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("EntityNotFoundException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleEntityAlreadyExistException(EntityAlreadyExistException ex,
                                                                      ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("EntityAlreadyExistException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleEntityUnauthorizedAccessException(UnauthorizedAccessException ex,
                                                                            ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("EntityUnauthorizedAccessException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleEntityForbiddenAccessException(ForbiddenAccessException ex,
                                                                         ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("EntityForbiddenAccessException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleEntityRuntimeException(RuntimeException ex,
                                                                 ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("EntityRuntimeException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(FailedCommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleFailedCommunicationException(FailedCommunicationException ex,
                                                                       ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("FailedCommunicationException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleUserNotFoundException(UserNotFoundException ex,
                                                                ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("UserNotFoundException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(UserCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleFailedCreatingUserException(UserCreationException ex,
                                                                      ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("FailedCreatingUserException").message(ex.getMessage()).path(path).build());
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Mono<GlobalErrorMessage> handleUserAlreadyExists(UserAlreadyExists ex, ServerHttpRequest request) {
        String path = String.valueOf(request.getPath());
        return Mono.just(GlobalErrorMessage.builder().title("UserAlreadyExists").message(ex.getMessage()).path(path).build());
    }
}