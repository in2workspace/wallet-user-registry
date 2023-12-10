package es.in2.walletuserregistry.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Optional;

@ConfigurationProperties(prefix = "wallet-wda")
public record WalletDrivingApplicationProperties(String url) {

    @ConstructorBinding
    public WalletDrivingApplicationProperties(String url) {
        this.url = Optional.ofNullable(url).orElse("http://localhost:4200");
    }

}
