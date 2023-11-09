package es.in2.wallet.user.registry.api.service;

import es.in2.wallet.user.registry.api.exception.*;
import es.in2.wallet.user.registry.api.model.KeycloakUserDTO;
import es.in2.wallet.user.registry.api.model.UserRequest;

import java.io.IOException;

public interface KeycloakService {
    String registerUserInKeycloak(UserRequest userRequest) throws UserKeycloakRegistryException;
}
