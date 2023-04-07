package com.application.products.controller;

import static com.application.products.utils.Constants.PRODUCTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.products.controller.handler.ProductHandler;

@Configuration
public class ProductController {

	public final ProductHandler orderHandler;

	@Autowired
	public ProductController(ProductHandler orderHandler) {
		this.orderHandler = orderHandler;
	}

	@Bean
	public RouterFunction<ServerResponse> productRoutes() {
		return RouterFunctions.route()

				.GET(PRODUCTS, orderHandler::findProductsByCriteria)


				.POST(PRODUCTS, orderHandler::createProduct)
				.POST("orders", orderHandler::createOrder)
				.POST("transcoding", orderHandler::transcode)

				.POST(PRODUCTS, orderHandler::createProduct).POST("orders", orderHandler::createOrder)

				.build();
	}

}
