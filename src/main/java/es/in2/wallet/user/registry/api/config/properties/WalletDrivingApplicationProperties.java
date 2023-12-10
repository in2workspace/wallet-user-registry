package es.in2.wallet.user.registry.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wallet-wda")
public record WalletDrivingApplicationProperties(String url) {
}
