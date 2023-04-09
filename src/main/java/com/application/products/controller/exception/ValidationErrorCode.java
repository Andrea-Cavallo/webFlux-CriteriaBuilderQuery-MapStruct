package com.application.products.controller.exception;

public enum ValidationErrorCode {

	ORDER_NOT_FOUND("ORDER NOT FOUND: "), 
	PRODUCT_NOT_FOUND("PRODUCT NOT FOUND: ");


	private String errorCode;

	private ValidationErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
