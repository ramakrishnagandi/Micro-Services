package com.app.billing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.billing.dto.CustomerDto;
import com.app.billing.dto.OrderDto;
import com.app.billing.entity.Orders;
import com.app.billing.repo.OrderRepo;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	CustomerFeignService customerFeignService;
	
	
	public ResponseEntity<String> createOrder(OrderDto order) {
		Orders orders = Orders.createOrder(order);
		orders = orderRepo.save(orders);
		return new ResponseEntity<>("Order Created Successfully", HttpStatus.CREATED);

	}

	@SuppressWarnings("unchecked")
	public <T> ResponseEntity<T> getOrders(Long orderId) {
		OrderDto orderDto = null;
		Orders orders = null;
		Optional<Orders> opOrders = orderRepo.findById(orderId);
		if (opOrders.isPresent()) {
			orders = opOrders.get();
			CustomerDto cutomerDto = customerFeignService.getCustomer(orders.getCustomerId()).getBody();
			orderDto = OrderDto.create(orders, cutomerDto);
		} else {
			return (ResponseEntity<T>) new ResponseEntity<>("No order found", HttpStatusCode.valueOf(500));
		}

		return (ResponseEntity<T>) new ResponseEntity<>(orderDto, HttpStatus.OK);
	}
}
