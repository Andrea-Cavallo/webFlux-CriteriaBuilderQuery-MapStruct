package com.application.products.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, ignoreUnknown = true)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@ToString
public class ProductDTO {

	private String productId;
	private String productName;
	private Double price;
	private Long quantity;
	private Boolean isInStock;

}
