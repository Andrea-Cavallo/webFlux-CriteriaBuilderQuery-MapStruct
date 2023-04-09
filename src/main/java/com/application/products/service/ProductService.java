package com.application.products.service;

import com.application.products.controller.dto.ProductDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Mono<List<ProductDTO>> findProductsByCriteria(String productName, Double minPrice, Double maxPrice);

    Mono<ProductDTO> createProduct(ProductDTO productRequest);

    Mono<ProductDTO> findByProductId(String productId);


}