package es.in2.wallet.user.registry.api.controller;

import es.in2.wallet.user.registry.api.exception.UserCreationException;
import es.in2.wallet.user.registry.api.domain.UserRequest;
import es.in2.wallet.user.registry.api.service.CreateUserFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final CreateUserFacadeService createUserFacadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserRequest userRequest) throws  UserCreationException{
        log.debug("UserController.registerUser()");
        log.debug(userRequest.getUsername());
        createUserFacadeService.createUser(userRequest);

    }
}
