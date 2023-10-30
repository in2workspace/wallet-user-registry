package es.in2.wallet.user.registry.api.service;

import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import es.in2.wallet.user.registry.api.exception.FailedCreatingUserException;
import es.in2.wallet.user.registry.api.exception.UserAlreadyExists;
import es.in2.wallet.user.registry.api.exception.UserNotFoundException;
import es.in2.wallet.user.registry.api.model.KeycloakUserDTO;
import es.in2.wallet.user.registry.api.model.UserRequest;

import java.io.IOException;

public interface KeycloakService {
    void registerUserInKeycloak(UserRequest userRequest) throws FailedCommunicationException, IOException, InterruptedException, UserNotFoundException, FailedCreatingUserException, UserAlreadyExists;
    String createUserInKeycloak(KeycloakUserDTO userData) throws FailedCommunicationException, IOException, InterruptedException, UserNotFoundException, FailedCreatingUserException, UserAlreadyExists;
}
