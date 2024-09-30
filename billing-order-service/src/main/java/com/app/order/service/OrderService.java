package com.app.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.order.dto.Category;
import com.app.order.dto.CustomerDto;
import com.app.order.dto.OrderCustomerResponseDTO;
import com.app.order.dto.OrderDto;
import com.app.order.dto.OrderFilterDTO;
import com.app.order.dto.OrderItemDto;
import com.app.order.dto.Product;
import com.app.order.entity.OrderEntity;
import com.app.order.repo.OrderRepo;
import com.app.order.utils.OrdersSpecification;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	CustomerFeignService customerFeignService;
	
	@Autowired
	ProductFiegnService productFiegnService;
	
	@Transactional
	public ResponseEntity<String> createOrder(OrderDto order) {
		OrderEntity orderEntity = OrderEntity.createOrder(order);
		try {
			orderEntity = orderRepo.save(orderEntity);
			
			for(OrderItemDto orderItemDto: order.getOrderItems()) {
				int quantity = 0;
				Product product = productFiegnService.getProductById(orderItemDto.getProductId()).getBody();
				quantity = product.getQuantity()-orderItemDto.getQuantity();
				product.setQuantity(quantity);
				productFiegnService.updateProduct(product.getProductId(), product);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Order Created Successfully", HttpStatus.CREATED);

	}

	@SuppressWarnings("unchecked")
	public <T> ResponseEntity<T> getOrders(Long orderId) {
		OrderDto orderDto = null;
		Optional<OrderEntity> opOrders = orderRepo.findById(orderId);
		if (opOrders.isPresent()) {
			orderDto = this.getOrderDto(opOrders.get(), orderDto);
		} else {
			return (ResponseEntity<T>) new ResponseEntity<>("No order found", HttpStatusCode.valueOf(500));
		}

		return (ResponseEntity<T>) new ResponseEntity<>(orderDto, HttpStatus.OK);
	}

	private OrderDto getOrderDto(OrderEntity orderEntity, OrderDto orderDto) {
		CustomerDto cutomerDto = customerFeignService.getCustomer(orderEntity.getCustomerId()).getBody();
		orderDto = OrderDto.create(orderEntity, cutomerDto);
		return orderDto;
	}

	public ResponseEntity<List<OrderDto>> getAllOrders() {
		List<OrderEntity> orders = orderRepo.findAll();
		List<OrderDto> ordersDto = new ArrayList<>();
		
		if(orders != null && !orders.isEmpty()) {
			for(OrderEntity entity:orders) {
				ordersDto.add(this.getOrderDto(entity, new OrderDto()));
			}
		}
		
		return new ResponseEntity<List<OrderDto>>(ordersDto, HttpStatus.OK);
	}
	
	public ResponseEntity<List<OrderCustomerResponseDTO>> getOrdersWithCustomerDetails(OrderFilterDTO filter, int page, int size) {
		CustomerDto cutomerDto = null;
		if(StringUtils.hasLength(filter.getMobileNumber())) {
			cutomerDto = customerFeignService.getCustomerByMobileNumber(filter.getMobileNumber()).getBody();
			filter.setCustomerId(cutomerDto.getCustomerId());
		}
		
		Specification<OrderEntity> specification = OrdersSpecification.getOrdersByFilter(filter);
		
		Page<OrderEntity> ordersPage = orderRepo.findAll(specification, PageRequest.of(page, size));
		
		List<OrderCustomerResponseDTO> result = ordersPage.map(order -> {
			CustomerDto cutomerDto2 = customerFeignService.getCustomer(order.getCustomerId()).getBody();
            return new OrderCustomerResponseDTO(order, cutomerDto2);
        }).getContent();
		
		return new ResponseEntity<List<OrderCustomerResponseDTO>>(result, HttpStatus.OK);
		
	}
}
