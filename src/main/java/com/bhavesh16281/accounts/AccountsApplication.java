package com.bhavesh16281.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @io.swagger.v3.oas.annotations.info.Info(
		title = "Accounts API",
		version = "1.0",
		description = "API for managing accounts",
		contact = @io.swagger.v3.oas.annotations.info.Contact(
			name = "Bhavesh",
			email = "bhavesh16281@example.com"
		),
		license = @io.swagger.v3.oas.annotations.info.License(	
		name = "Apache License Version 2.0",
		url = "https://www.apache.org/licenses/LICENSE-2.0"
		)
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AccountsApplication.class, args);
	}

}
