package com.application.ordine.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.application.ordine.documents.Order;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, ObjectId> {

}
