package com.application.ordine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.ordine.controller.handler.OrderHandler;

@RestController
public class OrderController {

	private static final String ORDERS = "/orders";

	private final OrderHandler orderHandler;

	@Autowired
	public OrderController(OrderHandler orderHandler) {
		this.orderHandler = orderHandler;
	}

	public RouterFunction<ServerResponse> fetchOrdersByCriteria() {
		return RouterFunctions.route().GET(ORDERS, orderHandler::findOrdersByCriteria).build();
	}

}
