package es.in2.wallet.user.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"es.in2.wallet.user.registry"})
public class UserRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistryApplication.class, args);
	}

}
