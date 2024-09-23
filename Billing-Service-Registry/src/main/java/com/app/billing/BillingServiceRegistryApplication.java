package com.app.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BillingServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceRegistryApplication.class, args);
	}

}
