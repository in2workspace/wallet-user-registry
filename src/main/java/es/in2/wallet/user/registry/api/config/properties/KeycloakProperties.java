package es.in2.wallet.user.registry.api.config.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(String url, String realm, String clientSecret,
                                       String clientId) {
}
