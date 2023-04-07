package com.application.products.controller.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class IncomingOrderDTO {
    private OrderStatus orderStatus;
    private UserRequest userRequest;
    private ProductRequest productRequest;

    public enum OrderStatus {
    	PROCESSING, REFUSED, CREATED
    }

    @Data
    public static class UserRequest {
        private String firstName;
        private String lastName;
        private Integer quantity;
        private String productName;
    }

    @Data
    public static class ProductRequest {
        private String productId;
        private String productName;
        private Double price;
        private Integer quantity;
        private Boolean isInStock;
		
    }
}
