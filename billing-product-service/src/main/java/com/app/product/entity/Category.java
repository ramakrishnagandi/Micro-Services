package com.app.product.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, length = 100)
    private String categoryName;

    @OneToMany(mappedBy = "category",  orphanRemoval = true)
    private List<Product> products;
    
    public Category(Long categoryId, String categoryName) {
    	this.categoryId=categoryId;
    	this.categoryName=categoryName;
	}

    
}
