package com.application.ordine.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.ordine.documents.Order;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {

	MongoTemplate getMongoTemplate();

	default List<Order> findOrdersByCriteria(String userName, LocalDateTime orderedAt, LocalDateTime deliveredAt,
	        Double minPrice, Double maxPrice) {
	    Query query = new Query();
	    buildUserCriteria(userName, query);
	    buildDateRangeCriteria(orderedAt, deliveredAt, query);
	    buildPriceRangeCriteria(minPrice, maxPrice, query);

	    return getMongoTemplate().find(query, Order.class);
	}

	private void buildUserCriteria(String userName, Query query) {
	    if (userName != null) {
	        query.addCriteria(Criteria.where("userName").is(userName));
	    }
	}

	private void buildDateRangeCriteria(LocalDateTime orderedAt, LocalDateTime deliveredAt, Query query) {
	    if (orderedAt != null || deliveredAt != null) {
	        Criteria dateRangeCriteria = new Criteria();
	        if (orderedAt != null) {
	            dateRangeCriteria.and("orderedAt").gte(orderedAt);
	        }
	        if (deliveredAt != null) {
	            dateRangeCriteria.and("deliveredAt").lte(deliveredAt);
	        }
	        query.addCriteria(dateRangeCriteria);
	    }
	}

	private void buildPriceRangeCriteria(Double minPrice, Double maxPrice, Query query) {
	    if (minPrice != null || maxPrice != null) {
	        Criteria priceRangeCriteria = new Criteria();
	        if (minPrice != null) {
	            priceRangeCriteria.and("price").gte(minPrice);
	        }
	        if (maxPrice != null) {
	            priceRangeCriteria.and("price").lte(maxPrice);
	        }
	        query.addCriteria(priceRangeCriteria);
	    }
	}

}
