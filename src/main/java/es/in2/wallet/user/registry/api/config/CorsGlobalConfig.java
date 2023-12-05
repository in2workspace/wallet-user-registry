package es.in2.wallet.user.registry.api.config;

import es.in2.wallet.user.registry.api.config.properties.WalletDrivingApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static es.in2.wallet.user.registry.api.util.ApiUtils.GLOBAL_ENDPOINTS_API;

@Configuration
@RequiredArgsConstructor
public class CorsGlobalConfig {
    private final WalletDrivingApplicationProperties walletDrivingApplicationProperties;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(GLOBAL_ENDPOINTS_API).allowedOrigins(walletDrivingApplicationProperties.url());
            }
        };
    }
}
