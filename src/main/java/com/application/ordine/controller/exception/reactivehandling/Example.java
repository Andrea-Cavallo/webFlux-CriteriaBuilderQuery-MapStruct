package com.application.ordine.controller.exception.reactivehandling;

import java.util.concurrent.Callable;

public class Example {

	public static void main(String[] args) {
		Callable<String> successCallable = () -> "Hello, world!";
		Try<String> successAttempt = Try.attempt(successCallable);

		if (successAttempt instanceof Success) {
			System.out.println("Success: " + ((Success<String>) successAttempt).getValue());
		} else {
			System.out.println("Failure: " + ((Failure<String>) successAttempt).getEx().getMessage());
		}

		Callable<String> failureCallable = () -> {
			throw new RuntimeException("Something went wrong!");
		};
		Try<String> failureAttempt = Try.attempt(failureCallable);

		if (failureAttempt instanceof Success) {
			System.out.println("Success: " + ((Success<String>) failureAttempt).getValue());
		} else {
			System.out.println("Failure: " + ((Failure<String>) failureAttempt).getEx().getMessage());
		}
	}

}
