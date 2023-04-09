package com.application.products.controller.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.service.OrderService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderHandler extends CommonHandler {

	private final OrderService orderService;

	private Logger logger = LogManager.getLogger(OrderHandler.class);

	/**
	 * 
	 * Handles a POST request to create a new Order.
	 * 
	 * @param request the incoming HTTP request
	 * 
	 * @return a Mono that emits the HTTP response to the client
	 * 
	 * @throws Exception if an error occurs while processing the request
	 * @author Andrea-Cavallo
	 * @link https://medium.com/@andreacavallo
	 */
	public Mono<ServerResponse> handleCreate(ServerRequest request) {
		logger.info("Order Handler: Received a request to create a new Order");
		return request.body(BodyExtractors.toMono(OrderDTO.class))
				.flatMap(orderDTO -> orderService.createOrder(orderDTO).flatMap(orderCreated -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(orderCreated))));
	}

	/**
	 * 
	 * Handles a POST request to transcode an Order.
	 * 
	 * @param request the incoming HTTP request
	 * @return a Mono that emits the HTTP response to the client
	 * @author Andrea-Cavallo
	 * @link https://medium.com/@andreacavallo
	 * 
	 */

	public Mono<ServerResponse> handleTranscode(ServerRequest request) {
		logger.info("Order Handler: Received a request to Transcode a new Order ");
		return request.body(BodyExtractors.toMono(IncomingOrderDTO.class))
				.flatMap(orderToTranscode -> orderService.transcodeOrder(orderToTranscode)
						.flatMap(orderTranscode -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromValue(orderTranscode))));

	}

	/**
	 * 
	 * Handles a DELETE request to delete an Order by ID.
	 * 
	 * @param request the incoming HTTP request
	 * 
	 * @return a Mono that emits the HTTP response to the client
	 * @author Andrea-Cavallo
	 * @link https://medium.com/@andreacavallo
	 * 
	 */
	public Mono<ServerResponse> handleDelete(ServerRequest request) {
		String orderId = request.pathVariable("orderId");
		logger.info("Order Handler: Received a request to delete an Order");

		return orderService.deleteByOrderId(orderId).then(ServerResponse.noContent().build())
				.onErrorResume(Exception.class, this::onGenericError);
	}

}
