package com.application.products.controller.handler;

import static com.application.products.utils.Constants.MAX_PRICE;
import static com.application.products.utils.Constants.MIN_PRICE;
import static com.application.products.utils.Constants.PRODUCTNAME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.controller.dto.ProductDTO;
import com.application.products.service.ProductService;

import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

	private final ProductService productService;

	@Autowired
	private ProductHandler(ProductService productService) {
		this.productService = productService;
	}

	public Mono<ServerResponse> findProductsByCriteria(ServerRequest request) {
		String userName = request.queryParam(PRODUCTNAME).orElse(null);
		Double minPrice = request.queryParam(MIN_PRICE).map(Double::parseDouble).orElse(null);
		Double maxPrice = request.queryParam(MAX_PRICE).map(Double::parseDouble).orElse(null);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productService.findProductsByCriteria(userName, minPrice, maxPrice), List.class)
				.switchIfEmpty(buildError());
	}

	public Mono<ServerResponse> createProduct(ServerRequest request) {
		return request.body(BodyExtractors.toMono(ProductDTO.class))
				.flatMap(prodDTO -> productService.createProduct(prodDTO).flatMap(productCreated -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(productCreated))));
	}

	public Mono<ServerResponse> createOrder(ServerRequest request) {
		return request.body(BodyExtractors.toMono(OrderDTO.class))
				.flatMap(orderDTO -> productService.createOrder(orderDTO).flatMap(orderCreated -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(orderCreated))));
	}
	
	public Mono<ServerResponse> transcode(ServerRequest request) {
		
		return request.body(BodyExtractors.toMono(IncomingOrderDTO.class))
				.flatMap(orderToTranscode -> productService.transcodeOrder(orderToTranscode)
				.flatMap(orderTranscoded -> ServerResponse.ok() 
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(orderTranscoded))));
		
	}
	
	

	public Mono<ServerResponse> buildError() {
		return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue("An error occurred!"));
	}

}
