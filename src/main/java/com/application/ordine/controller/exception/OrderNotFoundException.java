package com.application.ordine.controller.exception;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7281264479898969692L;

	public OrderNotFoundException(String message) {
		super(message);
	}

}
