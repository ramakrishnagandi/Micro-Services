package com.app.order.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.app.order.dto.OrderItemDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	@Column(nullable = false)
	private Long productId;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private BigDecimal pricePerUnit;

	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	public OrderItem() {
		
	}
	

	public OrderItem(Long productId, int quantity, BigDecimal pricePerUnit, BigDecimal totalPrice) {
		this.productId = productId;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		this.totalPrice = totalPrice;
	}

	public static List<OrderItem> create(List<OrderItemDto> orderItems) {
		List<OrderItem> items =  new ArrayList<>();
		for(OrderItemDto dto: orderItems) {
			items.add(new OrderItem(dto.getProductId(), dto.getQuantity(), dto.getPricePerUnit()
					,dto.getTotalPrice()));
		}
		return items;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	
	
}
