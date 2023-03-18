package com.application.ordine.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.ordine.service.OrderService;

import reactor.core.publisher.Mono;

@RestController
public class OrderController {


    private static final String MAX_PRICE = "maxPrice";
	private static final String USER_NAME = "userName";
	private static final String MIN_PRICE = "minPrice";
	private static final String DELIVERED_AT = "deliveredAt";
	private static final String ORDERED_AT = "orderedAt";
	private static final String ORDERS = "/orders";
	
	private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public RouterFunction<ServerResponse> fetchOrdersByCriteria() {
        return RouterFunctions.route()
                .GET(ORDERS, this::findOrdersByCriteria)
                .build();
    }

    private Mono<ServerResponse> findOrdersByCriteria(ServerRequest request) {

    	String userName = request.queryParam(USER_NAME).orElse(null);
    	LocalDateTime orderedAt = request.queryParam(ORDERED_AT)
    	                        .map(LocalDateTime::parse).orElse(null);
    	LocalDateTime deliveredAt = request.queryParam(DELIVERED_AT)
    	                        .map(LocalDateTime::parse).orElse(null);
    	Double minPrice = request.queryParam(MIN_PRICE)
    	                        .map(Double::parseDouble).orElse(null);
    	Double maxPrice = request.queryParam(MAX_PRICE)
    	                        .map(Double::parseDouble).orElse(null);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(orderService.findOrdersByCriteria(userName, orderedAt, deliveredAt, minPrice, maxPrice),
                        List.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
