package com.application.ordine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.ordine.controller.handler.OrderHandler;

@Configuration
public class OrderController {

	private static final String ORDERS = "/orders";

	private final OrderHandler orderHandler;

	@Autowired
	public OrderController(OrderHandler orderHandler) {
		this.orderHandler = orderHandler;
	}

	@Bean
	public RouterFunction<ServerResponse> orderRoutes() {
		return RouterFunctions.route().GET(ORDERS, orderHandler::findOrdersByCriteria)
				.POST(ORDERS, orderHandler::createOrder).build();
	}

	/**
	 * 
	 * Copy this cURl to try the post, please make sure you have you're
	 * MongoDbCompass up and running 
	 * 
	 * curl --location --request POST
	 * 'http://localhost:8080/orders' \ --header 'Content-Type: application/json' \
	 * --data-raw '{ "transactionId": "123456", "userName": "Andrea Cavallo",
	 * "orderedAt": "2023-03-19 10:30:00", "deliveredAt": "2023-03-21 14:00:00",
	 * "price": 10.0, "quantity": 2, "isInStock": true }'
	 * 
	 */
}
