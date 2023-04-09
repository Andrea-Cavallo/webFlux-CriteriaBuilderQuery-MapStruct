package com.application.products.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146162569483059928L;
	private String productId;
	private String productName;
	private Double price;
	private Long quantity;
	private Boolean isInStock;
}
