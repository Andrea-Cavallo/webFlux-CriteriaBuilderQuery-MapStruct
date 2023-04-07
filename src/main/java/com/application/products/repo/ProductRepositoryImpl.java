package com.application.products.repo;

import static com.application.products.utils.Constants.PRICE;
import static com.application.products.utils.Constants.PRODUCT_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.application.products.documents.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryImpl implements RepoCustom {

	private final ReactiveMongoTemplate reactiveMongoTemplate;

	@Autowired
	public ProductRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
		this.reactiveMongoTemplate = reactiveMongoTemplate;
	}

	@Override
	public Flux<Product> findProductsByCriteria(String productName, Double minPrice, Double maxPrice) {
		Query query = new Query();
		buildProductNameCriteria(productName, query);
		buildPriceRangeCriteria(minPrice, maxPrice, query);

		return reactiveMongoTemplate.find(query, Product.class);
	}

	public Mono<Product> save(Product orderRequest) {
		return reactiveMongoTemplate.save(orderRequest);
	}

	private void buildProductNameCriteria(String productName, Query query) {
		if (productName != null) {
			query.addCriteria(Criteria.where(PRODUCT_NAME).regex("^.*" + productName + ".*$", "i"));
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

	@Override
	public Mono<Product> findProductByName(String productName) {
		Query query = Query.query(Criteria.where("name").is(productName));
		return reactiveMongoTemplate.findOne(query, Product.class);
	}

}
