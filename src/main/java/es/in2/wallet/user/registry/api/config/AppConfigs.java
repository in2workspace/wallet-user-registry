package es.in2.wallet.user.registry.api.config;

import es.in2.wallet.user.registry.api.config.properties.KeycloakProperties;
import es.in2.wallet.user.registry.api.config.properties.OpenApiProperties;
import es.in2.wallet.user.registry.api.config.properties.WalletDataProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfigs {

    private final OpenApiProperties openApiProperties;
    private final WalletDataProperties walletDataProperties;
    private final KeycloakProperties keycloakProperties;

    @PostConstruct
    void init() {
        String prefixMessage = " > {}";
        log.info("Configurations uploaded: ");
        log.info(prefixMessage, openApiProperties.server());
        log.info(prefixMessage, openApiProperties.info());
        log.info(prefixMessage, keycloakProperties.url());
        log.info(prefixMessage, keycloakProperties.realm());
        log.info(prefixMessage, walletDataProperties);
    }

}
