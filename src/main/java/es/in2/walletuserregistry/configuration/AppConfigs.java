package es.in2.walletuserregistry.configuration;

import es.in2.walletuserregistry.configuration.properties.KeycloakProperties;
import es.in2.walletuserregistry.configuration.properties.OpenApiProperties;
import es.in2.walletuserregistry.configuration.properties.WalletDataProperties;
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
