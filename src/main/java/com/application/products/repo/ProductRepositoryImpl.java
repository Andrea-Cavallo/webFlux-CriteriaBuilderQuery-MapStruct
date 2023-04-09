package com.application.products.repo;

import static com.application.products.utils.Constants.PRICE;
import static com.application.products.utils.Constants.PRODUCT_NAME;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.application.products.models.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryImpl implements CustomRepository<Product> {
	private Logger logger = LogManager.getLogger(ProductRepositoryImpl.class);

	private final ReactiveMongoTemplate reactiveMongoTemplate;

	@Autowired
	public ProductRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
		this.reactiveMongoTemplate = reactiveMongoTemplate;
	}

	/**
	 * 
	 * Finds products matching the given criteria.
	 * 
	 * @param productName the name of the product to search for, or null if not
	 *                    searching by product name
	 * 
	 * @param minPrice    the minimum price of the products to search for, or null
	 *                    if not searching by price
	 * 
	 * @param maxPrice    the maximum price of the products to search for, or null
	 *                    if not searching by price
	 * 
	 * @return a Flux of Product objects matching the given criteria
	 */
	@Override
	public Flux<Product> findByCriteria(String productName, Double minPrice, Double maxPrice) {
		// Create a new query object and add criteria for the product name and price
		// range
		Query query = new Query();
		buildProductNameCriteria(productName, query);
		buildPriceRangeCriteria(minPrice, maxPrice, query);
		// Find products in the database matching the query criteria and return a Flux
		// of Product objects
		return reactiveMongoTemplate.find(query, Product.class);
	}

	@Override
	public Mono<Product> save(Product orderRequest) {
		return reactiveMongoTemplate.save(orderRequest);
	}

	@Override
	public Mono<Product> findByName(String productId) {
		Query query = Query.query(Criteria.where("productId").is(productId));
		return reactiveMongoTemplate.findOne(query, Product.class);
	}

	private void buildProductNameCriteria(String productName, Query query) {
		if (productName != null) {
			query.addCriteria(Criteria.where(PRODUCT_NAME).regex("^.*" + productName + ".*$", "i"));
		}
	}

	private void buildPriceRangeCriteria(Double minPrice, Double maxPrice, Query query) {
		if (minPrice != null || maxPrice != null) {
			List<Criteria> criteriaList = new ArrayList<>();

			if (minPrice != null) {
				criteriaList.add(Criteria.where(PRICE).gte(minPrice));
			}
			if (maxPrice != null) {
				criteriaList.add(Criteria.where(PRICE).lte(maxPrice));
			}

			Criteria priceRangeCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
			query.addCriteria(priceRangeCriteria);
		}
	}

	public Mono<Void> deleteById(String productId) {
		Query query = Query.query(Criteria.where("productId").is(productId));
		return reactiveMongoTemplate.remove(query, Product.class).doOnSuccess(deleteResult -> {
			logger.info("Deleted product with ID {}", productId);
		}).then();
	}

}
