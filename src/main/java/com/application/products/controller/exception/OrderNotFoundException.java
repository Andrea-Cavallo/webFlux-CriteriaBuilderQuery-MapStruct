package com.application.products.controller.exception;

public class OrderNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -273060534510161075L;

	public OrderNotFoundException(String id) {
	
		super(ValidationErrorCode.ORDER_NOT_FOUND, String.format("Order with orderId %s not found", id));
		}
	

}
