package com.application.products.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.application.products.controller.exception.NotFoundException;
import com.application.products.models.RestResponse;

import reactor.core.publisher.Mono;

class CommonHandler {

	protected Mono<ServerResponse> onServiceValidationError(NotFoundException e) {
		return Mono.just(e.getMessage()).flatMap(s -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.bodyValue(new RestResponse<>(ErrorCode.VALIDATION_ERROR.getValue(), s)));
	}

	

	protected Mono<ServerResponse> onGenericError(Throwable e) {
		return Mono.just(e.getMessage()).flatMap(s -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.bodyValue(new RestResponse<>(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), s)));
	}
	public enum ErrorCode {

		INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"), VALIDATION_ERROR("VALIDATION_ERROR");

		private final String value;

		private ErrorCode(String string) {
			this.value = string;
		}

		public String getValue() {
			return value;
		}

	}
}
