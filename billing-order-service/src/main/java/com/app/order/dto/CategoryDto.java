package com.app.order.dto;

import lombok.Data;

@Data
public class CategoryDto {
    
	private Long categoryId;
    private String categoryName;
    
    public CategoryDto(Long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
}
