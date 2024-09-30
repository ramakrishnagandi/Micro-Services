package com.app.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.app.order.dto.OrderDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class OrderEntity {
	
	@Id
    @GeneratedValue
    private Long orderId;
 
    @Column(nullable = false)
    private Long customerId;
 
    @Column(nullable = false)
    private String orderStatus;
 
    @Column(nullable = false)
    private BigDecimal totalAmount;
    
    private Integer totalItems;
 
    @Column(nullable = false)
    private Date createdAt;
 
    @Column(nullable = false)
    private Date updatedAt;
    
    @Column(nullable = false)
    private String createdUsername;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="orderId", referencedColumnName = "orderId")
    private List<OrderItem> items;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="orderId", referencedColumnName = "orderId")
    private List<OrderPayment> payments;
    
    public OrderEntity() {
    	
    }
    
 
    public OrderEntity(Long customerId, String orderStatus, BigDecimal totalAmount, Integer totalItems, 
    		String createdUsername, Date createdAt, Date updatedAt
    		, List<OrderItem> items, List<OrderPayment> payments) {
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.totalItems = totalItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdUsername = createdUsername;
        this.items = items;
        this.payments = payments;
    }

	public static OrderEntity createOrder(OrderDto order) {
		List<OrderItem> items = OrderItem.create(order.getOrderItems());
		List<OrderPayment> payments = null;
		if(order.getOrderPayments() != null && !order.getOrderPayments().isEmpty()) {
			payments = OrderPayment.create(order.getOrderPayments());
		}
		
		
		return new OrderEntity(order.getCustomerId(), order.getOrderStatus(), order.getTotalAmount(), order.getTotalItems(), order.getCreatedUsername()
				, new Date(), new Date(), items, payments);
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public List<OrderPayment> getPayments() {
		return payments;
	}

	public void setPayments(List<OrderPayment> payments) {
		this.payments = payments;
	}


	public Integer getTotalItems() {
		return totalItems;
	}


	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}
}
