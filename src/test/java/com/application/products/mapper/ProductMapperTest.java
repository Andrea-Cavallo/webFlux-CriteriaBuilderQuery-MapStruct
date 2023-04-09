package com.application.products.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.application.products.controller.dto.ProductDTO;
import com.application.products.models.Product;

public class ProductMapperTest {

	private ProductMapper productMapper = new ProductMapper();

	@Test
	@DisplayName("Test ProductMapper toDTO method")
	void testToDTO() {
		// GIVEN a product entity
		Product product = new Product();
		product.setProductId("123");
		product.setProductName("Product 1");
		product.setPrice(10.0);
		product.setQuantity(5L);
		product.setIsInStock(true);

		// WHEN the toDTO method is called with the product entity
		ProductDTO productDTO = productMapper.toDTO(product);

		// THEN the DTO should have the same values as the entity
		assertEquals("123", productDTO.getProductId());
		assertEquals("Product 1", productDTO.getProductName());
		assertEquals(10.0, productDTO.getPrice());
		assertEquals(5L, productDTO.getQuantity());
		assertEquals(true, productDTO.getIsInStock());
	}

	@Test
	@DisplayName("Test ProductMapper toEntity method")
	void testToEntity() {
		// GIVEN a product DTO
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId("123");
		productDTO.setProductName("Product 1");
		productDTO.setPrice(10.0);
		productDTO.setQuantity(5L);
		productDTO.setIsInStock(true);

		// WHEN the toEntity method is called with the product DTO
		Product product = productMapper.toEntity(productDTO);

		// THEN the entity should have the same values as the DTO
		assertEquals("123", product.getProductId());
		assertEquals("Product 1", product.getProductName());
		assertEquals(10.0, product.getPrice());
		assertEquals(5L, product.getQuantity());
		assertEquals(true, product.getIsInStock());
	}
}