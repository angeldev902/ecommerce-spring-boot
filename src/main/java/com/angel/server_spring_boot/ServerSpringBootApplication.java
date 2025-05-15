package com.angel.server_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.angel.server_spring_boot.config.DollarApi;
import com.angel.server_spring_boot.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, DollarApi.class})
public class ServerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSpringBootApplication.class, args);
	}

}
