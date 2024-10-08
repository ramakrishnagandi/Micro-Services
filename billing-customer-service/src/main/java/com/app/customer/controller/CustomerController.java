package com.app.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.customer.dto.CustomerDto;
import com.app.customer.entity.CustomerEntity;
import com.app.customer.service.CustomerService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("create")
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		return customerService.createCustomer(customerDto);		
	}
	
	@GetMapping("get/customer/{id}") 
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
		return customerService.getCustomer(id);
	}
	
	@GetMapping("get/customers") 
	public ResponseEntity<List<CustomerDto>> getCustomers() {
		return customerService.getCustomers();
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		return customerService.deleteCustomer(id);
	}
	
	 @GetMapping("search-by-mobile/{mobile}")
	 public ResponseEntity<CustomerDto> getCustomerByMobileNumber(@PathVariable String mobile){
		 return customerService.getCustomerByMobileNumber(mobile);
	 }

}
