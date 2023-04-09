package com.application.products.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@ToString
@Builder

public class ProductDTO implements Serializable {

    private String productId;
    private String productName;
    private Double price;
    private Long quantity;
    private Boolean isInStock;

}
