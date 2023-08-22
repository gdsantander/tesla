package com.gsantander.tesla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.gsantander.tesla.repositories"},
		enableDefaultTransactions = false)
public class TeslaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeslaApplication.class, args);
	}

	@Bean
	public OpenAPI openApi() {
		Info info = new Info().description("Learning Spring Boot 3").title("TESLA").version("v1");
		return new OpenAPI().info(info);
	}

}

