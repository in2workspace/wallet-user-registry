package es.in2.walletuserregistry.facade;

import es.in2.walletuserregistry.domain.UserRegistryRequest;
import reactor.core.publisher.Mono;

public interface UserRegistryFacadeService {
    Mono<Void> createUser(String processId, UserRegistryRequest userRegistryRequest);
}
