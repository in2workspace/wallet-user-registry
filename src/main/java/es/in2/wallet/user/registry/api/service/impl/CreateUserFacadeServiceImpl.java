package es.in2.wallet.user.registry.api.service.impl;

import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import es.in2.wallet.user.registry.api.exception.FailedCreatingUserException;
import es.in2.wallet.user.registry.api.exception.UserAlreadyExists;
import es.in2.wallet.user.registry.api.exception.UserNotFoundException;
import es.in2.wallet.user.registry.api.model.UserRequest;
import es.in2.wallet.user.registry.api.service.CreateUserFacadeService;
import es.in2.wallet.user.registry.api.service.KeycloakService;
import es.in2.wallet.user.registry.api.service.WalletDataCommunicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserFacadeServiceImpl implements CreateUserFacadeService {
    private final KeycloakService keycloakService;
    private final WalletDataCommunicationService walletDataCommunicationService;

    @Override
    public void createUser(UserRequest userRequest) throws UserNotFoundException, UserAlreadyExists, FailedCommunicationException, IOException, InterruptedException, FailedCreatingUserException {
        String userId = keycloakService.registerUserInKeycloak(userRequest);
        walletDataCommunicationService.saveUserDataOnWalletData(userRequest,userId);

    }
}
