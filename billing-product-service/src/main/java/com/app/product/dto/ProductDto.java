package com.app.product.dto;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class ProductDto {
	private Long productId;
    private String productName;
    private BigDecimal  price;
    private BigDecimal  weight;
    private int quantity;
    private String unit;
    private Long categoryId;
    private String categoryName;
    
	public ProductDto(Long productId, String productName, BigDecimal price, BigDecimal weight, 
			int quantity, String unit,
			Long categoryId, String categoryName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.weight = weight;
		this.quantity = quantity;
		this.unit = unit;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
}
