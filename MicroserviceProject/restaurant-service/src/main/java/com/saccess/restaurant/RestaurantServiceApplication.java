package com.saccess.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RestaurantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}

}
