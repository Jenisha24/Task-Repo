package com.restaurant;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.restaurant.repository")
@EntityScan(basePackages = "com.restaurant.entity")
@ComponentScan(basePackages = "com.restaurant.service")
@ComponentScan(basePackages = "com.restaurant.controller")
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
