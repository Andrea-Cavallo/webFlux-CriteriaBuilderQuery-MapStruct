package com.application.products.repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomRepository<T> {

    Flux<T> findByCriteria(String documentName, Double minPrice, Double maxPrice);

    Mono<T> save(T document);

    Mono<T> findByName(String documentName);
}