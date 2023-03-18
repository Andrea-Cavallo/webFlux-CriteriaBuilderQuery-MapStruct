package com.application.ordine.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.ordine.controller.dto.OrderDTO;
import com.application.ordine.controller.exception.OrderNotFoundException;
import com.application.ordine.mapper.OrderMapper;
import com.application.ordine.repo.OrderRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	private static final String LOG_INFO_SERVICE = "calling the criteria builder with parameters: userName={}, orderedAt={}, deliveredAt={}, minPrice={}, maxPrice={}";
	private static final String ORDER_NOT_FOUND = "No orders found matching the given criteria, please provide other request";

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
	}

	@Override
	public Mono<List<OrderDTO>> findOrdersByCriteria(String userName, LocalDateTime orderedAt,
			LocalDateTime deliveredAt, Double minPrice, Double maxPrice) {
		log.info(LOG_INFO_SERVICE, userName, orderedAt, deliveredAt, minPrice, maxPrice);
		return Flux
				.fromIterable(
						orderRepository.findOrdersByCriteria(userName, orderedAt, deliveredAt, minPrice, maxPrice))
				.map(orderMapper::toDTO).collect(Collectors.toList())
				.switchIfEmpty(Mono.error(new OrderNotFoundException(ORDER_NOT_FOUND))).log();
	}

}
