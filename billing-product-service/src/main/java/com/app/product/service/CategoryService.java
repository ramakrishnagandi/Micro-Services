package com.app.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.product.dto.CategoryDto;
import com.app.product.entity.Category;
import com.app.product.repo.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<List<CategoryDto>> getAllCategories() {
    	List<Category> categories = categoryRepository.findAll();
    	List<CategoryDto> dtoList = new ArrayList<>();
    	
    	for(Category entity : categories) {
    		CategoryDto dto = new CategoryDto(entity.getCategoryId(), entity.getCategoryName());
    		dtoList.add(dto);
    	}
    	
        return new ResponseEntity<List<CategoryDto>>(dtoList, HttpStatus.OK);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
