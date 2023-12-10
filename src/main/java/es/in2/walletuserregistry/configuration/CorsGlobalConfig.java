package es.in2.walletuserregistry.configuration;

import es.in2.walletuserregistry.configuration.properties.WalletDrivingApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import static es.in2.walletuserregistry.utils.HttpUtils.GLOBAL_ENDPOINTS_API;

@Configuration
@RequiredArgsConstructor
public class CorsGlobalConfig {

    private final WalletDrivingApplicationProperties walletDrivingApplicationProperties;

    @Bean
    public WebFluxConfigurer corsConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(GLOBAL_ENDPOINTS_API)
                        .allowedOrigins(walletDrivingApplicationProperties.url());
            }
        };
    }

}
