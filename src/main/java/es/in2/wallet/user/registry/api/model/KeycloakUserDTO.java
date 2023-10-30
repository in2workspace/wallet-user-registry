package es.in2.wallet.user.registry.api.model;

import lombok.*;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakUserDTO {
    private String username;
    private String email;
    private String id;
    private List<CredentialRepresentation> credentials;
    private Boolean enabled;
}
