package com.app.billing.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.billing.dto.CustomerDto;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerFeignService {
	
	@GetMapping("customer/get/customer/{id}") 
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id);

}
