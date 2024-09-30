package com.app.order.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.order.entity.OrderEntity;
import com.app.order.entity.OrderItem;

public interface OrderItemsRepo extends JpaRepository<OrderItem, Long> {

	//List<OrderItem> findOrderItemByOrderId(Long orderId);

}
