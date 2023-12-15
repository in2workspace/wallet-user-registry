package es.in2.walletuserregistry.facade.impl;

import es.in2.walletuserregistry.domain.UserRegistryRequest;
import es.in2.walletuserregistry.facade.UserRegistryFacadeService;
import es.in2.walletuserregistry.service.KeycloakService;
import es.in2.walletuserregistry.service.WalletDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistryFacadeServiceImpl implements UserRegistryFacadeService {

    private final KeycloakService keycloakService;
    private final WalletDataService walletDataService;

    @Override
    public Mono<Void> createUser(String processId, UserRegistryRequest userRegistryRequest) {
        // register User in Keycloak
        return keycloakService.registerUserInKeycloak(processId, userRegistryRequest)
                // save User in Wallet Data
                .flatMap(userId -> walletDataService.saveUser(processId, userRegistryRequest, userId));
    }
}
