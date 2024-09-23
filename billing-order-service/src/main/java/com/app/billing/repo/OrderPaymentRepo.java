package com.app.billing.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.billing.entity.OrderPayment;

public interface OrderPaymentRepo extends JpaRepository<OrderPayment, Long>{

	//List<OrderPayment> findOrderPaymentByOrderId(Long orderId);

}
