package com.application.ordine.controller.exception.reactivehandling;

import lombok.Getter;

public class Failure<T> implements Try<T>{
	public Exception getEx() {
		return ex;
	}
	public void setEx(Exception ex) {
		this.ex = ex;
	}
	private Exception ex ;
	public Failure(Exception ex) {
		this.ex = ex;
	}
}
