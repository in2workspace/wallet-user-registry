package es.in2.wallet.user.registry.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Optional;

/**
 * Configuration intended to be used by the OpenAPI documentation.
 *
 * @param server - server information
 * @param info   - organization information
 */
@ConfigurationProperties(prefix = "openapi")
public record OpenApiProperties(@NestedConfigurationProperty OpenApiServerProperties server,
                                @NestedConfigurationProperty OpenApiInfoProperties info) {

    @ConstructorBinding
    public OpenApiProperties(OpenApiServerProperties server, OpenApiInfoProperties info) {
        this.server = Optional.ofNullable(server).orElse(new OpenApiServerProperties(null, null));
        this.info = Optional.ofNullable(info).orElse(new OpenApiInfoProperties(null, null, null, null, null, null));
    }

}
