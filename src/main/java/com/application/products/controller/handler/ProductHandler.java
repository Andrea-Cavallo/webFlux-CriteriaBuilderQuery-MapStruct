package com.application.products.controller.handler;

import static com.application.products.utils.Constants.MAX_PRICE;
import static com.application.products.utils.Constants.MIN_PRICE;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.products.controller.dto.ProductDTO;
import com.application.products.service.ProductService;
import com.application.products.utils.Constants;

import reactor.core.publisher.Mono;

@Component
public class ProductHandler extends CommonHandler {
	private final ProductService productService;
	private Logger logger = LogManager.getLogger(ProductHandler.class);

	@Autowired
	private ProductHandler(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 
	 * Handles a GET request to retrieve a list of products based on search
	 * criteria.
	 * 
	 * @param request the incoming HTTP request
	 * 
	 * @return a Mono that emits the HTTP response to the client
	 * @author Andrea-Cavallo
	 * @link https://medium.com/@andreacavallo
	 */
	public Mono<ServerResponse> handleCriteria(ServerRequest request) {

		String productName = request.queryParam(Constants.PRODUCT_NAME).orElse(null);
		Double minPrice = request.queryParam(MIN_PRICE).map(Double::parseDouble).orElse(null);
		Double maxPrice = request.queryParam(MAX_PRICE).map(Double::parseDouble).orElse(null);
		logger.info("Product Handler: FindProductsByCriteria product Name is  {} ", productName);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productService.findProductsByCriteria(productName, minPrice, maxPrice), List.class)
				.switchIfEmpty(buildError());
	}

	/**
	 * 
	 * Handles a POST request to create a new product.
	 * 
	 * @param request the incoming HTTP request
	 * 
	 * @return a Mono that emits the HTTP response to the client
	 * @author Andrea-Cavallo
	 * @link https://medium.com/@andreacavallo
	 */
	public Mono<ServerResponse> handleCreate(ServerRequest request) {
		return request.body(BodyExtractors.toMono(ProductDTO.class))
				.flatMap(prodDTO -> productService.createProduct(prodDTO).flatMap(productCreated -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(productCreated))));
	}

	/**
	 * 
	 * Handles a GET request to retrieve a product by its ID.
	 * 
	 * @param request the incoming HTTP request
	 * 
	 * @return a Mono that emits the HTTP response to the client
	 * @author Andrea-Cavallo
	 * @link https://medium.com/@andreacavallo
	 */
	public Mono<ServerResponse> handleGetById(ServerRequest request) {
		String productId = request.pathVariable("productId");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productService.findByProductId(productId), ProductDTO.class).switchIfEmpty(buildError());
	}

}
