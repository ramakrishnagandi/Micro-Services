package com.app.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
