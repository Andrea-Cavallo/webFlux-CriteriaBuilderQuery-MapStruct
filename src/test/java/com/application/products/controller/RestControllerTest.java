package com.application.products.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.controller.handler.OrderHandler;
import com.application.products.controller.handler.ProductHandler;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(RestController.class)
class RestControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductHandler productHandler;

	@MockBean
	private OrderHandler orderHandler;

	@Test
	void testCreateOrder() {
		OrderDTO orderDTO = new OrderDTO();
		OrderDTO expectedResponse = new OrderDTO();
		when(orderHandler.handleCreate(any(ServerRequest.class)))
				.thenReturn(Mono.just(orderDTO).flatMap(order -> ServerResponse.ok().bodyValue(order)));

		webTestClient.post().uri("/orders").contentType(MediaType.APPLICATION_JSON).bodyValue(orderDTO).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody(OrderDTO.class)
				.isEqualTo(expectedResponse);
	}

	@Test
	void testTranscode() {
		IncomingOrderDTO incomingOrderDTO = createIncomingOrderDTO();
		OrderDTO orderDTO = new OrderDTO();
		OrderDTO expectedResponse = new OrderDTO();

		when(orderHandler.handleTranscode(any(ServerRequest.class)))
				.thenReturn(Mono.just(orderDTO).flatMap(order -> ServerResponse.ok().bodyValue(order)));
		webTestClient.post().uri("/transcoding").contentType(MediaType.APPLICATION_JSON).bodyValue(incomingOrderDTO)
				.exchange().expectStatus().isOk().expectBody(OrderDTO.class).isEqualTo(expectedResponse);
	}

	private IncomingOrderDTO createIncomingOrderDTO() {
		return IncomingOrderDTO.builder()
				.userRequest(IncomingOrderDTO.UserRequest.builder().firstName("John").lastName("Doe").build())
				.productRequest(IncomingOrderDTO.ProductRequest.builder().productId(UUID.randomUUID().toString())
						.productName("Product 1").price(100.0).quantity(1).isInStock(true).build())
				.build();
	}

}
