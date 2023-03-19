package com.application.ordine.repo;

import java.time.LocalDateTime;

import com.application.ordine.documents.Order;

import reactor.core.publisher.Flux;

public interface OrderRepositoryCustom {

	Flux<Order> findOrdersByCriteria(String userName, LocalDateTime orderedAt, LocalDateTime deliveredAt,
			Double minPrice, Double maxPrice);

}