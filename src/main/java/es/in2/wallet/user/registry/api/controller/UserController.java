package es.in2.wallet.user.registry.api.controller;

import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import es.in2.wallet.user.registry.api.exception.FailedCreatingUserException;
import es.in2.wallet.user.registry.api.exception.UserAlreadyExists;
import es.in2.wallet.user.registry.api.exception.UserNotFoundException;
import es.in2.wallet.user.registry.api.model.UserRequest;
import es.in2.wallet.user.registry.api.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final KeycloakService keycloakService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserRequest userRequest) throws UserNotFoundException, FailedCommunicationException, IOException, InterruptedException, FailedCreatingUserException, UserAlreadyExists {
        log.debug("UserController.registerUser()");
        log.debug(userRequest.getUsername());
        keycloakService.registerUserInKeycloak(userRequest);

    }
}
