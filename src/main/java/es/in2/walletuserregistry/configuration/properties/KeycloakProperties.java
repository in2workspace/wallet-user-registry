package es.in2.walletuserregistry.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Optional;

/**
 * KeycloakProperties
 *
 * @param url - keycloak url
 * @param realm - keycloak realm
 * @param clientSecret - keycloak client secret
 * @param clientId - keycloak client id
 */
@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(String url, String realm, String clientSecret, String clientId) {

    @ConstructorBinding
    public KeycloakProperties(String url, String realm, String clientSecret, String clientId) {
        this.url = Optional.ofNullable(url).orElse("http://localhost:8084");
        this.realm = Optional.ofNullable(realm).orElse("WalletIdP");
        this.clientSecret = Optional.ofNullable(clientSecret).orElse("fV51P8jFBo8VnFKMMuP3imw3H3i5mNck");
        this.clientId = Optional.ofNullable(clientId).orElse("user-registry-client");
    }

}
