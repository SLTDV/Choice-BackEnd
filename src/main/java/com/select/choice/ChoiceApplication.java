package com.select.choice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = {"com.select.*"})
@SpringBootApplication
public class ChoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoiceApplication.class, args);
	}

}