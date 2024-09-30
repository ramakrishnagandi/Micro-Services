package com.app.product.entity;

import java.math.BigDecimal;

import com.app.product.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal  price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal  weight;

    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false)
    private String unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    // Constructors, Getters, Setters, and other methods

    public Product(String productName, BigDecimal price, BigDecimal weight, int quantity, String unit, Category category) {
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
    }

	public Product create(ProductDto productDto) {
		Category category = new Category(productDto.getCategoryId(), productDto.getCategoryName());
		Product product= new Product(
				productDto.getProductName(),
				productDto.getPrice(),
				productDto.getWeight(),
				productDto.getQuantity(),
				productDto.getUnit(),
				category);
		
		return product;
		
	}
}
