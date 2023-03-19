package com.application.ordine.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.ordine.controller.dto.OrderDTO;
import com.application.ordine.controller.exception.OrderNotFoundException;
import com.application.ordine.mapper.OrderMapper;
import com.application.ordine.repo.OrderRepositoryCustom;

import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	private static final String LOG_INFO_SERVICE = "calling the criteria builder with parameters: userName={}, orderedAt={}, deliveredAt={}, minPrice={}, maxPrice={}";
	private static final String ORDER_NOT_FOUND = "No orders found matching the given criteria, please provide other request";

	private final OrderMapper orderMapper;
	private final OrderRepositoryCustom orderRepositoryCustom;

	@Autowired
	public OrderServiceImpl(OrderMapper orderMapper, OrderRepositoryCustom orderRepositoryCustom) {
		this.orderMapper = orderMapper;
		this.orderRepositoryCustom = orderRepositoryCustom;
	}

	@Override
	public Mono<List<OrderDTO>> findOrdersByCriteria(String userName, LocalDateTime orderedAt,
			LocalDateTime deliveredAt, Double minPrice, Double maxPrice) {
		LOGGER.info(LOG_INFO_SERVICE, userName, orderedAt, deliveredAt, minPrice, maxPrice);
		return orderRepositoryCustom.findOrdersByCriteria(userName, orderedAt, deliveredAt, minPrice, maxPrice)
				.map(orderMapper::toDTO).collectList()
				.switchIfEmpty(Mono.error(new OrderNotFoundException(ORDER_NOT_FOUND)));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Mono<OrderDTO> createOrder(OrderDTO orderRequest) {
		return orderRepositoryCustom.save(orderMapper.toEntity(orderRequest)).map(orderMapper::toDTO);

	}

	/*
	 * Propagation.REQUIRED: Questo tipo di propagazione è il comportamento
	 * predefinito quando non si specifica alcuna propagazione nella maggior parte
	 * dei sistemi di gestione delle transazioni. Propagation.REQUIRED indica che il
	 * metodo corrente deve essere eseguito all'interno di una transazione.
	 * 
	 * Quando si utilizza Propagation.REQUIRED:
	 * 
	 * Se il metodo corrente è chiamato all'interno di un contesto senza transazione
	 * attiva, ne verrà creata una nuova. Se il metodo corrente è chiamato
	 * all'interno di un contesto con una transazione attiva, il metodo verrà
	 * eseguito all'interno di quella stessa transazione. Quando usarlo:
	 * 
	 * Propagation.REQUIRED è adatto quando si desidera che un metodo sia sempre
	 * eseguito all'interno di una transazione, sia che esista già una transazione
	 * attiva o meno. È utile quando si ha un insieme di operazioni che devono
	 * essere eseguite insieme come un'unità di lavoro atomica (tutto o niente). È
	 * la scelta predefinita e più comune per la maggior parte delle situazioni in
	 * cui è necessario un controllo transazionale.
	 */

}
