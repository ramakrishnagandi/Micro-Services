package com.app.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.customer.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	CustomerEntity findByMobileNo(String mobileNumber);

}
