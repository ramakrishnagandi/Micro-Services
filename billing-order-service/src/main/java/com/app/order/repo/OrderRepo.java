package com.app.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.app.order.entity.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity>  {

}
