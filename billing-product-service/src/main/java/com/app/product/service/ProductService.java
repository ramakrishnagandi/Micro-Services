package com.app.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.product.dto.ProductDto;
import com.app.product.entity.Category;
import com.app.product.entity.Product;
import com.app.product.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<List<ProductDto>>  getAllProducts() {
		List<Product> products = productRepository.findAll();

		List<ProductDto> dtoList = products.stream()
				.map(product -> new ProductDto(product.getProductId(), product.getProductName(), product.getPrice(),
						product.getWeight(), product.getQuantity(), product.getUnit(),
						product.getCategory().getCategoryId(), product.getCategory().getCategoryName()))
				.collect(Collectors.toList());

		return new ResponseEntity<List<ProductDto>>(dtoList, HttpStatus.OK);
	}

	public ResponseEntity<ProductDto> getProductById(Long id) {
		Product product = productRepository.findById(id).get();

		ProductDto dto = new ProductDto(product.getProductId(), product.getProductName(), product.getPrice(),
				product.getWeight(), product.getQuantity(), product.getUnit(), product.getCategory().getCategoryId(),
				product.getCategory().getCategoryName());

		return new ResponseEntity<ProductDto>(dto, HttpStatus.OK);
	}

	public ResponseEntity<ProductDto> saveProduct(Long id, ProductDto dto) {
		Product product = new Product().create(dto);
		if(id != null) {
			ProductDto p =  this.getProductById(id).getBody();
			product.setProductId(id);
			Category c = new Category(p.getCategoryId(), p.getCategoryName());
			product.setCategory(c);
		}
		product = productRepository.save(product);
		return new ResponseEntity<ProductDto>(dto, HttpStatus.OK);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
