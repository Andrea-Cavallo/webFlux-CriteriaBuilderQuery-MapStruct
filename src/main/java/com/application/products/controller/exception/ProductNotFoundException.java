package com.application.products.controller.exception;

public class ProductNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7281264479898969692L;

    public ProductNotFoundException(String message) {
        super( message );
    }

}
