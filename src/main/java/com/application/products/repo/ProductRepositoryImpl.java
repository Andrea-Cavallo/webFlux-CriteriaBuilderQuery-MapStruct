package com.application.products.repo;

import com.application.products.documents.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.application.products.utils.Constants.PRICE;
import static com.application.products.utils.Constants.PRODUCT_NAME;

@Repository
public class ProductRepositoryImpl implements CustomRepository<Product> {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public ProductRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<Product> findByCriteria(String productName, Double minPrice, Double maxPrice) {
        Query query = new Query();
        buildProductNameCriteria( productName, query );
        buildPriceRangeCriteria( minPrice, maxPrice, query );

        return reactiveMongoTemplate.find( query, Product.class );
    }

    public Mono<Product> save(Product orderRequest) {
        return reactiveMongoTemplate.save( orderRequest );
    }

    @Override
    public Mono<Product> findByName(String productId) {
        Query query = Query.query( Criteria.where( "productId" ).is( productId ) );
        return reactiveMongoTemplate.findOne( query, Product.class );
    }


    private void buildProductNameCriteria(String productName, Query query) {
        if (productName != null) {
            query.addCriteria( Criteria.where( PRODUCT_NAME ).regex( "^.*" + productName + ".*$", "i" ) );
        }
    }

    private void buildPriceRangeCriteria(Double minPrice, Double maxPrice, Query query) {
        if (minPrice != null || maxPrice != null) {
            Criteria priceRangeCriteria = new Criteria();
            if (minPrice != null) {
                priceRangeCriteria.and( PRICE ).gte( minPrice );
            }
            if (maxPrice != null) {
                priceRangeCriteria.and( PRICE ).lte( maxPrice );
            }
            query.addCriteria( priceRangeCriteria );
        }
    }


}
