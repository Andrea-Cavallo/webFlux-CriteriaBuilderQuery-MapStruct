package com.application.products.service;

import java.util.List;

import com.application.products.controller.dto.ProductDTO;

import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<List<ProductDTO>> findProductsByCriteria(String productName, Double minPrice, Double maxPrice);

	Mono<ProductDTO> createProduct(ProductDTO productRequest);

	Mono<ProductDTO> findByProductId(String productId);

	Mono<Void> deleteByProductId(String productId);
	
	
	
	

}