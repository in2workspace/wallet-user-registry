package es.in2.walletuserregistry.service;

import es.in2.walletuserregistry.domain.UserRegistryRequest;
import reactor.core.publisher.Mono;

public interface KeycloakService {

    Mono<String> registerUserInKeycloak(String processId, UserRegistryRequest userRegistryRequest);

}
