package com.app.order.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.order.entity.OrderEntity;
import com.app.order.entity.OrderItem;
import com.app.order.entity.OrderPayment;

public class OrderDto {
	private Long customerId;
	private String orderStatus;
	private BigDecimal totalAmount;
	private Integer totalItems;
	private Date createdAt;
	private Date updatedAt;
	private String createdUsername;
	private CustomerDto customer;
	private List<OrderItemDto> orderItems;
	private List<OrderPaymentDto> orderPayments;
	
	
	
	public static OrderDto create(OrderEntity orderEntity, CustomerDto customer) {
		OrderDto dto = new OrderDto();
		
		dto.setCustomerId(orderEntity.getCustomerId());
		dto.setOrderStatus(orderEntity.getOrderStatus());
		dto.setTotalAmount(orderEntity.getTotalAmount());
		dto.setTotalItems(orderEntity.getTotalItems());
		dto.setCustomer(customer);
		
		List<OrderItemDto> itemsDto = new ArrayList<>();
		
		for(OrderItem item:orderEntity.getItems()) {
			OrderItemDto itemDto = new OrderItemDto();
			itemDto.setPricePerUnit(item.getPricePerUnit());
			itemDto.setProductId(item.getProductId());
			itemDto.setQuantity(item.getQuantity());
			itemDto.setTotalPrice(item.getTotalPrice());
			itemsDto.add(itemDto);
		}
		
		List<OrderPaymentDto> paymentsDto = new ArrayList<>();
		for (OrderPayment payment : orderEntity.getPayments()) {
			OrderPaymentDto pDto = new OrderPaymentDto();

			pDto.setPaymentMethod(payment.getPaymentMethod());
			pDto.setPaymentStatus(payment.getPaymentStatus());
			pDto.setAmount(payment.getAmount());
			pDto.setPaymentDate(payment.getPaymentDate());
			paymentsDto.add(pDto);
		}

		dto.setOrderPayments(paymentsDto);
		dto.setOrderItems(itemsDto);
		
		return dto;
	}



	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	public BigDecimal getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	public CustomerDto getCustomer() {
		return customer;
	}



	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}



	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}



	public List<OrderPaymentDto> getOrderPayments() {
		return orderPayments;
	}



	public void setOrderPayments(List<OrderPaymentDto> orderPayments) {
		this.orderPayments = orderPayments;
	}



	public Integer getTotalItems() {
		return totalItems;
	}



	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}



	public String getCreatedUsername() {
		return createdUsername;
	}



	public void setCreatedUsername(String createdUsername) {
		this.createdUsername = createdUsername;
	}

}
