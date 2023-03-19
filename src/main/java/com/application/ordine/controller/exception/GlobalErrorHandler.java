package com.application.ordine.controller.exception;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandler.class);

	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<ResponseEntity<String>> handleRequestBodyError(WebExchangeBindException ex) {
		LOGGER.error("Exception Caught in handleRequestBodyError: {}", ex.getMessage(), ex);

		String error = ex.getBindingResult().getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).sorted().collect(Collectors.joining(","));
		LOGGER.error("Error is: {}", error);

		return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
	}

//    @ExceptionHandler(OrderNotFoundException.class)
//    public Mono<ResponseEntity<String>> handleOrderNotFoundException(OrderNotFoundException ex) {
//        log.error("Exception Caught in OrderNotFoundException: {}", ex.getMessage(), ex);
//        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
//    }
	
	   @ExceptionHandler(ResponseStatusException.class)
	    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
	        if (ex.getStatus() == HttpStatus.NOT_FOUND) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
	        }
	        return ResponseEntity.status(ex.getStatus()).body(ex.getReason());
	    }
}
