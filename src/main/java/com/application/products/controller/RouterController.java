package com.application.products.controller;

import static com.application.products.utils.Constants.ORDERS;
import static com.application.products.utils.Constants.ORDERS_ORDER_ID;
import static com.application.products.utils.Constants.PRODUCTS;
import static com.application.products.utils.Constants.PRODUCTS_PRODUCT_ID;
import static com.application.products.utils.Constants.TRANSCODING;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.products.controller.handler.OrderHandler;
import com.application.products.controller.handler.ProductHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RouterController {

	private final ProductHandler productHandler;
	private final OrderHandler orderHandler;

	/**
	 * 
	 * Configures the RouterFunction for handling HTTP requests.
	 * 
	 * @return a RouterFunction that maps incoming HTTP requests to appropriate
	 *         handlers
	 */
	@Bean
	public RouterFunction<ServerResponse> routes() {
		// Define the routes for handling HTTP requests

		return RouterFunctions.route()
				
				//products
				.POST(PRODUCTS, productHandler::handleCreate)
				.GET(PRODUCTS, productHandler::handleFetchProductsByNameOrByPrice)
				.GET(PRODUCTS_PRODUCT_ID, productHandler::handleGetById)
				.DELETE(PRODUCTS_PRODUCT_ID, productHandler::handleDelete)
				
				//orders		
				.POST(TRANSCODING, orderHandler::handleTranscode)
				.POST(ORDERS, orderHandler::handleCreate)
				.DELETE(ORDERS_ORDER_ID,orderHandler::handleDelete)			
				.build();
	}
	
	//TODO: update needs to be implemented - find by OrderId to 
	//TODO: add CircuitBreaker 
	//TODO: add more junits test case
	//TODO: add a validation of the incoming requests
	

}
