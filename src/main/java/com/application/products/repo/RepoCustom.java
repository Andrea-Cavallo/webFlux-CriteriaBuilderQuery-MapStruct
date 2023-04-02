package com.application.products.repo;

import com.application.products.documents.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepoCustom {

	Flux<Product> findProductsByCriteria(String productName, Double minPrice, Double maxPrice);

	public Mono<Product> save(Product productRequest);

	public Mono<Product> findProductByName(String productName);
}