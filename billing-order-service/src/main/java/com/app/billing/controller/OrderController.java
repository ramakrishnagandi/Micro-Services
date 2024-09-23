package com.app.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.billing.dto.OrderDto;
import com.app.billing.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("create")
	public ResponseEntity<String> createOrder(@RequestBody OrderDto order) {
		return orderService.createOrder(order);
	}
	
	@GetMapping("get/orders/{orderId}")
	public ResponseEntity<OrderDto> getOrders(@PathVariable Long orderId) {
		return orderService.getOrders(orderId);
	}

}
