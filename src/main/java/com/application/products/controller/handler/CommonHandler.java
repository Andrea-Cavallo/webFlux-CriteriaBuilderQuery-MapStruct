package com.application.products.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

class CommonHandler {

	protected Mono<ServerResponse> buildError() {
		return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue("An error occurred!"));
	}
}
