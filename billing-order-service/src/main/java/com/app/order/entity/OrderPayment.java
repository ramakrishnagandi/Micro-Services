package com.app.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.app.order.dto.OrderPaymentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderPayment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
 
    @Column(nullable = false)
    private String paymentMethod;
 
    @Column(nullable = false)
    private String paymentStatus;
 
    @Column(nullable = false)
    private BigDecimal amount;
 
    @Column(nullable = false)
    private LocalDateTime paymentDate;
    
    public OrderPayment() {
    	
    }

	public OrderPayment(String paymentMethod, String paymentStatus, BigDecimal amount,
			LocalDateTime paymentDate) {
		super();
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	public static List<OrderPayment> create(List<OrderPaymentDto> orderPayments) {
		List<OrderPayment> payments = new ArrayList<>();
		for(OrderPaymentDto dto:orderPayments) {
			payments.add(new OrderPayment(dto.getPaymentMethod(),
					dto.getPaymentStatus(),
					dto.getAmount(),
					LocalDateTime.now() ));
		}
		return payments;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
}
