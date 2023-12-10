package es.in2.walletuserregistry.service;

import es.in2.walletuserregistry.domain.UserRegistryRequest;
import reactor.core.publisher.Mono;

public interface WalletDataService {

    Mono<Void> saveUser(String processId, UserRegistryRequest userRegistryRequest, String userId);

}
