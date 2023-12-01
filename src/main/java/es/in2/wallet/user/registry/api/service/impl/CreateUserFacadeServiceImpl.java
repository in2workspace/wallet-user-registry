package es.in2.wallet.user.registry.api.service.impl;

import es.in2.wallet.user.registry.api.exception.UserCreationException;
import es.in2.wallet.user.registry.api.domain.UserRequest;
import es.in2.wallet.user.registry.api.service.CreateUserFacadeService;
import es.in2.wallet.user.registry.api.service.KeycloakService;
import es.in2.wallet.user.registry.api.service.WalletDataCommunicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserFacadeServiceImpl implements CreateUserFacadeService {

    private final KeycloakService keycloakService;
    private final WalletDataCommunicationService walletDataCommunicationService;

    @Override
    public void createUser(UserRequest userRequest) throws UserCreationException {
        try {
            String userId = keycloakService.registerUserInKeycloak(userRequest);
            walletDataCommunicationService.saveUserDataOnWalletData(userRequest, userId);
        } catch (Exception e) {
            throw new UserCreationException(e.getMessage(), e.getCause());
        }
    }
}
