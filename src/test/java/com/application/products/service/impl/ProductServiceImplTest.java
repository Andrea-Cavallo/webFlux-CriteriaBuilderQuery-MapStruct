package com.application.products.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.products.controller.dto.ProductDTO;
import com.application.products.controller.exception.ProductNotFoundException;
import com.application.products.documents.Product;
import com.application.products.mapper.ProductMapper;
import com.application.products.repo.CustomRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductServiceImplTest {

	private ProductMapper productMapper;
	@Mock
	private CustomRepository<Product> productRepository;
	private ProductServiceImpl productService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		productMapper = new ProductMapper();
		productService = new ProductServiceImpl(productMapper, productRepository);
	}

//	@Test
//	@DisplayName(""
//			+ "GIVEN no products matching the search criteria in the repository"
//			+ "WHEN findProductsByCriteria is called with search criteria" +
//			"THEN the Mono should complete with an error of ProductNotFoundException"
//			)
//	 void testFindProductsByCriteriaWithNoResults() {
//		when(productRepository.findByCriteria(anyString(), anyDouble(), anyDouble())).thenReturn(Flux.empty());
//
//		Mono<List<ProductDTO>> products = productService.findProductsByCriteria("product", 10.0, 20.0);
//
//	    Assertions.assertThrowsExactly(ProductNotFoundException.class, products::block);
//	}

	@Test
	@DisplayName("GIVEN some products matching the search criteria in the repository"
			+ "WHEN findProductsByCriteria is called with search criteria"
			+ "THEN the Mono should complete with a list of ProductDTOs"

	)
	void testFindProductsByCriteriaWithResults() {
		Product product1 = new Product();
		product1.setProductName("switch");
		product1.setIsInStock(true);
		Product product2 = new Product();
		product2.setProductName("xbox");
		product2.setIsInStock(true);
		when(productRepository.findByCriteria(anyString(), anyDouble(), anyDouble())).thenReturn(Flux.just(product1));

		Mono<List<ProductDTO>> products = productService.findProductsByCriteria("switch", Double.MIN_VALUE,
				Double.MAX_VALUE);

		List<ProductDTO> expectedProducts = Arrays.asList(productMapper.toDTO(product1));
		Assertions.assertEquals(expectedProducts, products.block());
	}

	@Test
	@DisplayName("GIVEN a product to create" + "WHEN createProduct is called with the product DTO"
			+ "THEN the Mono should complete with the created product DTO")
	void testCreateProduct() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductName("product");
		productDTO.setPrice(10.0);

		Product product = new Product();
		product.setProductId(UUID.randomUUID().toString());
		product.setProductName("product");
		product.setPrice(10.0);
		when(productRepository.save(any())).thenReturn(Mono.just(product));

		Mono<ProductDTO> createdProduct = productService.createProduct(productDTO);

		ProductDTO expectedProduct = productMapper.toDTO(product);
		Assertions.assertEquals(expectedProduct, createdProduct.block());
	}

	@Test
	@DisplayName("GIVEN no product matching the ID in the repository" + "WHEN findByProductId is called with an ID "
			+ "THEN the Mono should complete with an error of ProductNotFoundException")
	void testFindByProductIdWithNoResult() {
		when(productRepository.findByName(anyString())).thenReturn(Mono.empty());

		Mono<ProductDTO> product = productService.findByProductId(UUID.randomUUID().toString());

		Assertions.assertThrows(ProductNotFoundException.class, product::block);
	}

	@Test
	@DisplayName("GIVEN a product matching the ID in the repository"
			+ "WHEN findByProductId is called with the product ID"
			+ "THEN the Mono should complete with the found product DTO"

	)
	void testFindByProductIdWithResult() {
		Product product = new Product();
		product.setProductId(UUID.randomUUID().toString());
		product.setProductName("product");
		product.setPrice(10.0);
		when(productRepository.findByName(anyString())).thenReturn(Mono.just(product));

		Mono<ProductDTO> foundProduct = productService.findByProductId(product.getProductId());

		ProductDTO expectedProduct = productMapper.toDTO(product);
		Assertions.assertEquals(expectedProduct, foundProduct.block());
	}

}
