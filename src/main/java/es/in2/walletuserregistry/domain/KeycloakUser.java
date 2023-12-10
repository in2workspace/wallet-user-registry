package es.in2.walletuserregistry.domain;

import lombok.Builder;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.List;

@Builder
public record KeycloakUser(String username, String email, String id, List<CredentialRepresentation> credentials,
                           Boolean enabled) {
}
