package com.select.choice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ConfigurationPropertiesScan(basePackages = {"com.select.*"})
@SpringBootApplication
@EnableJpaRepositories
public class ChoiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChoiceApplication.class, args);
	}

}