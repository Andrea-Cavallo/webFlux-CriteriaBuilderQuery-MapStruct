package com.application.products.controller.exception;


public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2101945398696538697L;

	public NotFoundException(ValidationErrorCode errorCode, String message) {
		super(new StringBuilder().append(errorCode.getErrorCode()).append(message).toString());
	}

}
