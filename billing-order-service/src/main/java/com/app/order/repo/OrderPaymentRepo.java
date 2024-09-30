package com.app.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.order.entity.OrderPayment;

public interface OrderPaymentRepo extends JpaRepository<OrderPayment, Long>{

	//List<OrderPayment> findOrderPaymentByOrderId(Long orderId);

}
