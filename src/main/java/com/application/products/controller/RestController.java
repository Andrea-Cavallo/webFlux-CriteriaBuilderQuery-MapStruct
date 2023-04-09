package com.application.products.controller;

import static com.application.products.utils.Constants.ORDERS;
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
public class RestController {

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
				.GET(PRODUCTS, productHandler::handleCriteria)
				.GET(PRODUCTS_PRODUCT_ID, productHandler::handleGetById)
				.POST(PRODUCTS, productHandler::handleCreate)
				.POST(ORDERS, orderHandler::handleCreate)
				.POST(TRANSCODING, orderHandler::handleTranscode)
				.build();
	}

}
