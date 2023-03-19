package com.application.ordine.controller.exception.reactivehandling;

import java.util.concurrent.Callable;

public interface Try<T> {

    static <T> Try<T> attempt(Callable<T> callable) {
        try {
            return new Success<>(callable.call());
        } catch (Exception ex) {
            return new Failure<>(ex);
        }
    }
}
