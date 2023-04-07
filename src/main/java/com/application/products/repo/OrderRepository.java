package com.application.products.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.application.products.documents.Order;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}