package com.application.products.controller.exception;

public class ProductNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -273060534510161075L;

	public ProductNotFoundException(String id) {
	
		super(ValidationErrorCode.PRODUCT_NOT_FOUND, String.format("Product with productId, or name: %s  was not found on the system", id));
		}
	

}
