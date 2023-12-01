package es.in2.wallet.user.registry.api.service;

import es.in2.wallet.user.registry.api.exception.UserCreationException;
import es.in2.wallet.user.registry.api.domain.UserRequest;

public interface CreateUserFacadeService {
    void createUser(UserRequest userRequest) throws UserCreationException;
}
