package com.app.billing.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.billing.entity.OrderItem;
import com.app.billing.entity.Orders;

public interface OrderItemsRepo extends JpaRepository<OrderItem, Long> {

	//List<OrderItem> findOrderItemByOrderId(Long orderId);

}
