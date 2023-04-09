package com.application.products.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.application.products.documents.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class OrderRepositoryImpl implements CustomRepository<Order> {

	private final ReactiveMongoTemplate reactiveMongoTemplate;

	@Autowired
	public OrderRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
		this.reactiveMongoTemplate = reactiveMongoTemplate;
	}

	@Override
	public Flux<Order> findByCriteria(String documentName, Double minPrice, Double maxPrice) {
		return null;
	}

	@Override
	public Mono<Order> save(Order document) {
		return reactiveMongoTemplate.save(document);
	}

	@Override
	public Mono<Order> findByName(String documentName) {
		Query query = Query.query(Criteria.where("name").is(documentName));
		return reactiveMongoTemplate.findOne(query, Order.class);
	}

	@Override
	public Mono<Void> deleteById(String orderId) {
		Query query = Query.query(Criteria.where("orderId").is(orderId));
		return reactiveMongoTemplate.remove(query, Order.class).then();
	}
}