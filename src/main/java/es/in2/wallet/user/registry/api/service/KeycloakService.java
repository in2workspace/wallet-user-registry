package es.in2.wallet.user.registry.api.service;

import es.in2.wallet.user.registry.api.exception.*;
import es.in2.wallet.user.registry.api.domain.UserRequest;

public interface KeycloakService {
    String registerUserInKeycloak(UserRequest userRequest) throws UserKeycloakRegistryException;
}
