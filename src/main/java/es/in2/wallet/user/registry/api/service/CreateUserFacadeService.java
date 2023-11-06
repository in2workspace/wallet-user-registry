package es.in2.wallet.user.registry.api.service;

import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import es.in2.wallet.user.registry.api.exception.FailedCreatingUserException;
import es.in2.wallet.user.registry.api.exception.UserAlreadyExists;
import es.in2.wallet.user.registry.api.exception.UserNotFoundException;
import es.in2.wallet.user.registry.api.model.UserRequest;

import java.io.IOException;

public interface CreateUserFacadeService {
    void createUser(UserRequest userRequest) throws UserNotFoundException, UserAlreadyExists, FailedCommunicationException, IOException, InterruptedException, FailedCreatingUserException;
}
