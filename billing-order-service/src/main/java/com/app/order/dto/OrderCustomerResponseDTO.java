package com.app.order.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.app.order.entity.OrderEntity;

public class OrderCustomerResponseDTO {
	
	private Long orderId;
    private String productName;
    private Date orderDate;
    private String orderStatus;
    private String paymentStatus;
    private BigDecimal totalAmount;
    private String customerName;
    private String mobileNo;
    private Integer totalItems;
    
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public OrderCustomerResponseDTO(OrderEntity order, CustomerDto customer) {
        this.orderId = order.getOrderId();
        this.productName = null;
        this.orderDate = order.getCreatedAt();
        this.orderStatus = order.getOrderStatus();
        this.totalAmount = order.getTotalAmount();
        this.customerName = customer.getFirstName() + " " + customer.getLastName();
        this.mobileNo = customer.getMobileNo();
        this.totalItems = order.getTotalItems();
    }
	public Integer getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

}
