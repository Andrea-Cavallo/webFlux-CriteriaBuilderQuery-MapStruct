package com.application.ordine.repo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.application.ordine.documents.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

	private static final String USER_NAME = "userName";
	private static final String PRICE = "price";
	private static final String DELIVERED_AT = "deliveredAt";
	private static final String ORDERED_AT = "orderedAt";
	private final ReactiveMongoTemplate reactiveMongoTemplate;

	@Autowired
	public OrderRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
		this.reactiveMongoTemplate = reactiveMongoTemplate;
	}

	@Override
	public Flux<Order> findOrdersByCriteria(String userName, LocalDateTime orderedAt, LocalDateTime deliveredAt,
			Double minPrice, Double maxPrice) {
		Query query = new Query();
		buildUserCriteria(userName, query);
		buildDateRangeCriteria(orderedAt, deliveredAt, query);
		buildPriceRangeCriteria(minPrice, maxPrice, query);
		query.with(Sort.by(Sort.Direction.DESC, ORDERED_AT)); 

		return reactiveMongoTemplate.find(query, Order.class);
	}
	
	
	public Mono<Order> save(Order orderRequest) {
		return reactiveMongoTemplate.save(orderRequest);
	}

	private void buildUserCriteria(String userName, Query query) {
		if (userName != null) {
			query.addCriteria(Criteria.where(USER_NAME).is(userName));
		}
	}

	private void buildDateRangeCriteria(LocalDateTime orderedAt, LocalDateTime deliveredAt, Query query) {
		if (orderedAt != null || deliveredAt != null) {
			Criteria dateRangeCriteria = new Criteria();
			if (orderedAt != null) {
				dateRangeCriteria.and(ORDERED_AT).gte(orderedAt);
			}
			if (deliveredAt != null) {
				dateRangeCriteria.and(DELIVERED_AT).lte(deliveredAt);
			}
			query.addCriteria(dateRangeCriteria);
		}
	}

	private void buildPriceRangeCriteria(Double minPrice, Double maxPrice, Query query) {
		if (minPrice != null || maxPrice != null) {
			Criteria priceRangeCriteria = new Criteria();
			if (minPrice != null) {
				priceRangeCriteria.and(PRICE).gte(minPrice);
			}
			if (maxPrice != null) {
				priceRangeCriteria.and(PRICE).lte(maxPrice);
			}
			query.addCriteria(priceRangeCriteria);
		}
	}

}
