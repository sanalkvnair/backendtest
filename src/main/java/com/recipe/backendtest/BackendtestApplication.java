package com.recipe.backendtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"com.recipe"})
@EntityScan(basePackages = {"com.recipe.entities"})
@EnableJpaRepositories(basePackages = {"com.recipe"})
public class BackendtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendtestApplication.class, args);
	}
}
