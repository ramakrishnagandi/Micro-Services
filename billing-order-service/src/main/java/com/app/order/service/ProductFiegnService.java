package com.app.order.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.order.dto.Product;
import com.app.order.dto.ProductDto;

@FeignClient("PRODUCT-SERVICE")
public interface ProductFiegnService {

	@GetMapping("product")
    public List<ProductDto> getAllProducts();

	@GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id);

	@PutMapping("product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails);
    
}
