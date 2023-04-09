package com.application.products.service.impl;

import static com.application.products.utils.Constants.LOG_INFO_SERVICE;
import static com.application.products.utils.Constants.PRODUCT_NOT_FOUND;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.products.controller.dto.ProductDTO;
import com.application.products.controller.exception.ProductNotFoundException;
import com.application.products.documents.Product;
import com.application.products.mapper.ProductMapper;
import com.application.products.repo.CustomRepository;
import com.application.products.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductMapper productMapper;
	private final CustomRepository<Product> productRepositoryImpl;

	private Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	/**
	 * 
	 * Finds a list of products based on the provided search criteria.
	 * 
	 * @param productName the name of the product to search for
	 * 
	 * @param minPrice    the minimum price of the products to search for
	 * 
	 * @param maxPrice    the maximum price of the products to search for
	 * 
	 * @return a Mono that emits the list of matching ProductDTOs
	 * 
	 * @throws ProductNotFoundException if no products are found matching the search
	 *                                  criteria
	 */
	@Override
	public Mono<List<ProductDTO>> findProductsByCriteria(String productName, Double minPrice, Double maxPrice) {
		logger.info(LOG_INFO_SERVICE, productName, minPrice, maxPrice);
		return productRepositoryImpl.findByCriteria(productName, minPrice, maxPrice).map(productMapper::toDTO)
				.collectList().switchIfEmpty(Mono.error(new ProductNotFoundException(PRODUCT_NOT_FOUND)))
				.doOnError(e -> logger.error("Error in findProductsByCriteria: {}", e.getMessage()));
	}

	/**
	 * 
	 * Creates a new product based on the provided ProductDTO.
	 * 
	 * @param productRequest the ProductDTO containing the details of the new
	 *                       product
	 * 
	 * @return a Mono that emits the created ProductDTO
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Mono<ProductDTO> createProduct(ProductDTO productRequest) {

		String uuid = UUID.randomUUID().toString();
		productRequest.setProductId(uuid);
		logger.info("ProductService: create product with productID {}", uuid);
		return productRepositoryImpl.save(productMapper.toEntity(productRequest)).map(productMapper::toDTO);

	}

	/**
	 * 
	 * Finds a product by its ID.
	 * 
	 * @param productId the ID of the product to find
	 * 
	 * @return a Mono that emits the matching ProductDTO
	 * 
	 * @throws ProductNotFoundException if no product is found with the given ID
	 */
	@Override
	public Mono<ProductDTO> findByProductId(String productId) {
		logger.info("In product service product id is {}", productId);
		return productRepositoryImpl.findByName(productId).map(productMapper::toDTO)
				.switchIfEmpty(Mono.error(new ProductNotFoundException(PRODUCT_NOT_FOUND)));
	}

	/**
	 * 
	 * Deletes a product with the given ID.
	 * 
	 * @param productId the ID of the product to delete
	 * 
	 * @return a Mono that completes when the product is deleted
	 * 
	 * @throws ProductNotFoundException if no product is found with the given ID
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Mono<Void> deleteByProductId(String productId) {
		logger.info("In product service product to delete has id: {}", productId);
		return productRepositoryImpl.findByName(productId)
				.flatMap(product -> productRepositoryImpl.deleteById(productId))
				.switchIfEmpty(Mono.error(new ProductNotFoundException(PRODUCT_NOT_FOUND)));
	}

}
