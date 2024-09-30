package com.app.order.dto;

import java.math.BigDecimal;


public class Product {
    private Long productId;
    private String productName;
    private BigDecimal  price;
    private BigDecimal  weight;
    private int quantity;
    private String unit;
    private Category category;

    // Constructors, Getters, Setters, and other methods

    public Product() {}

    public Product(String productName, BigDecimal price, BigDecimal weight, int quantity, String unit, Category category) {
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

	public Product create(ProductDto productDto) {
		Category category = new Category();
		category.setCategoryId(productDto.getCategoryId());
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
