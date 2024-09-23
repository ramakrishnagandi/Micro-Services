package com.app.billing.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.billing.entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long>  {

}
