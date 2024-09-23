package com.app.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BillingOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingOrderServiceApplication.class, args);
	}

}
