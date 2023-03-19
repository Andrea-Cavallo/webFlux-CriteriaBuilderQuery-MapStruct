package com.application.ordine.service;

import java.time.LocalDateTime;
import java.util.List;

import com.application.ordine.controller.dto.OrderDTO;

import reactor.core.publisher.Mono;

public interface OrderService {

	Mono<List<OrderDTO>> findOrdersByCriteria(String userName, LocalDateTime orderedAt, LocalDateTime deliveredAt,
			Double minPrice, Double maxPrice);

	Mono<OrderDTO> createOrder(OrderDTO orderRequest);

}
