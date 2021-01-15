package com.csk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@SpringBootApplication
public class CskGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CskGatewayApplication.class, args);
	}

}
