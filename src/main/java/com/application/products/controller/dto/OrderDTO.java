package com.application.products.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@ToString

public class OrderDTO implements Serializable {

    private OrderStatus orderStatus;
    private OrderInfo orderInfo;
    private UserInfo userInfo;
    private ProductInfo productInfo;

    public enum OrderStatus {
        PROCESSING, REFUSED, CREATED
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Setter
    @ToString
    @Builder(toBuilder = true)

    public static class OrderInfo implements Serializable {
        private String orderId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        private String orderedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        private String deliveredAt;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Setter
    @ToString
    @Builder(toBuilder = true)

    public static class UserInfo implements Serializable {
        private String firstName;
        private String lastName;
        private String email;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Setter
    @ToString
    @Builder(toBuilder = true)

    public static class ProductInfo implements Serializable {
        private UUID productId;
        private String productName;
        private Double price;
        private Integer quantity;
        private Boolean isInStock;
    }
}
