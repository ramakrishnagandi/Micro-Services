package com.app.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
