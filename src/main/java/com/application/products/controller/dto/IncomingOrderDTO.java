package com.application.products.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class IncomingOrderDTO implements Serializable {
    private OrderStatus orderStatus;
    private UserRequest userRequest;
    private ProductRequest productRequest;

    public enum OrderStatus {
        PROCESSING, REFUSED, CREATED
    }

    @Data
    public static class UserRequest implements Serializable {
        private String firstName;
        private String lastName;
        private Integer quantity;
        private String productName;
    }

    @Data
    public static class ProductRequest implements Serializable {
        private String productId;
        private String productName;
        private Double price;
        private Integer quantity;
        private Boolean isInStock;

    }
}
