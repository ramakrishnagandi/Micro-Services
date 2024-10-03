package com.app.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.order.dto.OrderCustomerResponseDTO;
import com.app.order.dto.OrderDto;
import com.app.order.dto.OrderFilterDTO;
import com.app.order.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("create")
	public ResponseEntity<String> createOrder(@RequestBody OrderDto order) {
		return orderService.createOrder(order);
	}
	
	@GetMapping("get/orders")
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@GetMapping("get/orders/{orderId}")
	public ResponseEntity<OrderDto> getOrders(@PathVariable Long orderId) {
		return orderService.getOrders(orderId);
	}
	
	@PostMapping("/filter")
    public Page<OrderCustomerResponseDTO> filterOrders(@RequestBody OrderFilterDTO filter,
                                                       @RequestParam int page,
                                                       @RequestParam int size) {
        return orderService.getOrdersWithCustomerDetails(filter, page, size);
    }

}
