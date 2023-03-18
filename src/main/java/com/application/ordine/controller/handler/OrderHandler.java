package com.application.ordine.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.ordine.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderHandler {

	private OrderService orderService;

	@Autowired
	private OrderHandler(OrderService orderService) {
		this.orderService = orderService;
	}



	


	public enum RequestType {
		GET_ORDER;}

}
