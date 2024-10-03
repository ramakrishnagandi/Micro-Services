package com.app.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.customer.dto.CustomerDto;
import com.app.customer.entity.CustomerEntity;
import com.app.customer.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {
		CustomerEntity c = customerRepo.findByMobileNo(customerDto.getMobileNo());
		
		if (c != null) {
			customerDto = new CustomerDto();
			customerDto.setError("Duplicate entry for EMAIL or Mobile");
			return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.ALREADY_REPORTED);
		}
		
		CustomerEntity entity = new CustomerEntity(customerDto.getFirstName(), customerDto.getLastName(),
				customerDto.getEmail(), customerDto.getMobileNo(), customerDto.getAddressLine(), customerDto.getCity(),
				customerDto.getState(), customerDto.getCountry(), customerDto.getPostelCode(), new Date(), new Date());
		entity = customerRepo.save(entity);
		
		customerDto = new CustomerDto(entity.getCustomerId(), entity.getFirstName(), entity.getLastName(),
				entity.getEmail(), entity.getMobileNo(), entity.getAddressLine(), entity.getCity(), entity.getState(),
				entity.getCountry(), entity.getPostelCode(), null);

		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.CREATED);

	}

	public ResponseEntity<String> deleteCustomer(Long id) {
		customerRepo.deleteById(id);
		return new ResponseEntity<>("Succesfully deleted the Customer", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<CustomerDto> getCustomer(Long id) {
		Optional<CustomerEntity> customerEntity = customerRepo.findById(id);
		if (customerEntity.isPresent()) {
			CustomerEntity entity = customerEntity.get();
			CustomerDto dto = new CustomerDto(entity.getCustomerId(), entity.getFirstName(), entity.getLastName(),
					entity.getEmail(), entity.getMobileNo(), entity.getAddressLine(), entity.getCity(),
					entity.getState(), entity.getCountry(), entity.getPostelCode(), null);
			return new ResponseEntity<CustomerDto>(dto, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<List<CustomerDto>> getCustomers() {
		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		List<CustomerEntity> customers = customerRepo.findAll();

		for (CustomerEntity entity : customers) {
			CustomerDto dto = new CustomerDto(entity.getCustomerId(), entity.getFirstName(), entity.getLastName(),
					entity.getEmail(), entity.getMobileNo(), entity.getAddressLine(), entity.getCity(),
					entity.getState(), entity.getCountry(), entity.getPostelCode(), null);
			customersDto.add(dto);
		}

		return new ResponseEntity<List<CustomerDto>>(customersDto, HttpStatusCode.valueOf(200));
	}

	public ResponseEntity<CustomerDto> getCustomerByMobileNumber(String mobileNumber) {
		CustomerEntity entity = customerRepo.findByMobileNo(mobileNumber);
		CustomerDto dto = new CustomerDto(entity.getCustomerId(), entity.getFirstName(), entity.getLastName(),
				entity.getEmail(), entity.getMobileNo(), entity.getAddressLine(), entity.getCity(), entity.getState(),
				entity.getCountry(), entity.getPostelCode(), null);
		return new ResponseEntity<CustomerDto>(dto, HttpStatus.OK);
	}

}
