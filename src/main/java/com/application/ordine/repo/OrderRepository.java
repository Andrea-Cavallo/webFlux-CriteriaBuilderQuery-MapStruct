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
		Criteria criteria = new Criteria();

		if (userName != null) {
			criteria.and("userName").is(userName);
		}

		if (orderedAt != null && deliveredAt != null) {
			criteria.andOperator(Criteria.where("orderedAt").gte(orderedAt),
					Criteria.where("deliveredAt").lte(deliveredAt));
		} else if (orderedAt != null) {
			criteria.and("orderedAt").gte(orderedAt);
		} else if (deliveredAt != null) {
			criteria.and("deliveredAt").lte(deliveredAt);
		}

		if (minPrice != null || maxPrice != null) {
			Criteria priceCriteria = new Criteria();
			if (minPrice != null) {
				priceCriteria.gte(minPrice);
			}
			if (maxPrice != null) {
				priceCriteria.lte(maxPrice);
			}
			criteria.and("price").is(priceCriteria);
		}

		Query query = new Query(criteria);
		if (userName != null) {
			query.addCriteria(Criteria.where("userName").is(userName));
		}
		if (orderedAt != null && deliveredAt != null) {
			query.addCriteria(Criteria.where("orderedAt").gte(orderedAt).lte(deliveredAt).and("deliveredAt")
					.lte(deliveredAt).gte(orderedAt));
		} else if (orderedAt != null) {
			query.addCriteria(Criteria.where("orderedAt").gte(orderedAt));
		} else if (deliveredAt != null) {
			query.addCriteria(Criteria.where("deliveredAt").lte(deliveredAt));
		}
		if (minPrice != null || maxPrice != null) {
			Criteria priceCriteria = new Criteria();
			if (minPrice != null) {
				priceCriteria.gte(minPrice);
			}
			if (maxPrice != null) {
				priceCriteria.lte(maxPrice);
			}
			query.addCriteria(Criteria.where("price").is(priceCriteria));
		}

		return getMongoTemplate().find(query, Order.class);
	}
}
