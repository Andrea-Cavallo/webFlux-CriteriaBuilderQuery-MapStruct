package com.application.ordine.controller.exception.reactivehandling;

import lombok.Getter;


public class Success<T> implements Try<T>{
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	T value;
	public Success(T value) {
		this.value = value;
	}

}
