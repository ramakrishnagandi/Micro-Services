package com.app.customer.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.customer.dto.CustomerDto;
import com.app.customer.entity.CustomerEntity;
import com.app.customer.repo.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;

	public ResponseEntity<CustomerEntity> createCustomer(CustomerDto customerDto) {
		CustomerEntity customerEntity = new CustomerEntity(customerDto.getFirstName()
				,customerDto.getLastName(), customerDto.getEmail(), customerDto.getMobileNo()
				,customerDto.getAddressLine(), customerDto.getCity(), customerDto.getState()
				, customerDto.getCountry(), customerDto.getPostelCode(), new Date(), new Date());
		customerEntity = customerRepo.save(customerEntity);
		return new ResponseEntity<>(customerEntity, HttpStatus.CREATED);
		
	}

	public ResponseEntity<String> deleteCustomer(Long id) {
		customerRepo.deleteById(id);
		return new ResponseEntity<>("Succesfully deleted the Customer", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<CustomerDto> getCustomer(Long id) {
		Optional<CustomerEntity> customerEntity = customerRepo.findById(id);
		if(customerEntity.isPresent()) {
			CustomerEntity entity = customerEntity.get();
			CustomerDto dto = new CustomerDto(entity.getCustomerId(), entity.getFirstName(), entity.getLastName(), 
					entity.getEmail(), entity.getMobileNo(),
					entity.getAddressLine(), entity.getCity(), 
					entity.getState(), entity.getCountry(), entity.getPostelCode());
			return new ResponseEntity<CustomerDto>(dto, HttpStatus.OK);
		}
		return null;
	}

}
