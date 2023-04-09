package com.application.products.models;

import java.util.HashMap;
import java.util.Map;

import com.application.products.controller.dto.Dto;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class RestResponse<T> implements Dto {

	private static final long serialVersionUID = 1L;

	private T output;

	private Map<String, String> errorMessages;

	public RestResponse(T output) {
		super();
		this.output = output;
	}

	public RestResponse(String errorCode, String errorMessage) {
		this.errorMessages = new HashMap<>();
		errorMessages.put(errorCode, errorMessage);
	}

}
