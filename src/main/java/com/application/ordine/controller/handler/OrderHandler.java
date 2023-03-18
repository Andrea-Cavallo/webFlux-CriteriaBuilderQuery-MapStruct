package com.application.ordine.controller.handler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.ordine.service.OrderService;

import reactor.core.publisher.Mono;

@Component
public class OrderHandler {
	private static final String MAX_PRICE = "maxPrice";
	private static final String USER_NAME = "userName";
	private static final String MIN_PRICE = "minPrice";
	private static final String DELIVERED_AT = "deliveredAt";
	private static final String ORDERED_AT = "orderedAt";
	private OrderService orderService;

	@Autowired
	private OrderHandler(OrderService orderService) {
		this.orderService = orderService;
	}

	public Mono<ServerResponse> findOrdersByCriteria(ServerRequest request) {

		String userName = request.queryParam(USER_NAME).orElse(null);
		LocalDateTime orderedAt = request.queryParam(ORDERED_AT).map(LocalDateTime::parse).orElse(null);
		LocalDateTime deliveredAt = request.queryParam(DELIVERED_AT).map(LocalDateTime::parse).orElse(null);
		Double minPrice = request.queryParam(MIN_PRICE).map(Double::parseDouble).orElse(null);
		Double maxPrice = request.queryParam(MAX_PRICE).map(Double::parseDouble).orElse(null);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(orderService.findOrdersByCriteria(userName, orderedAt, deliveredAt, minPrice, maxPrice),
						List.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}

}
