package com.application.products.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder

public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4233959285371087419L;
	private String productId;
	private String productName;
	private Double price;
	private Long quantity;
	private Boolean isInStock;

}
