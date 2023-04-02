package com.application.products.service;

import static com.application.products.utils.Constants.LOG_INFO_SERVICE;
import static com.application.products.utils.Constants.PRODUCT_NOT_FOUND;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.products.controller.dto.OrderDTO;
import com.application.products.controller.dto.ProductDTO;
import com.application.products.controller.exception.ProductNotFoundException;
import com.application.products.documents.Order;
import com.application.products.mapper.OrderMapper;
import com.application.products.mapper.ProductMapper;
import com.application.products.repo.OrderRepository;
import com.application.products.repo.RepoCustom;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	public ProductServiceImpl(ProductMapper productMapper, RepoCustom customRepo, OrderMapper orderMapper,
			OrderRepository orderRepo) {
		super();
		this.productMapper = productMapper;
		this.customRepo = customRepo;
		this.orderMapper = orderMapper;
		this.orderRepo = orderRepo;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductMapper productMapper;
	private final RepoCustom customRepo;
	private final OrderMapper orderMapper;

	private final OrderRepository orderRepo;

	@Override
	public Mono<List<ProductDTO>> findProductsByCriteria(String productName, Double minPrice, Double maxPrice) {
		LOGGER.info(LOG_INFO_SERVICE, productName, minPrice, maxPrice);
		return customRepo.findProductsByCriteria(productName, minPrice, maxPrice).map(productMapper::toDTO)
				.collectList().switchIfEmpty(Mono.error(new ProductNotFoundException(PRODUCT_NOT_FOUND)));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Mono<ProductDTO> createProduct(ProductDTO productRequest) {
		String uuid = UUID.randomUUID().toString();
		productRequest.setProductId(uuid);
		return customRepo.save(productMapper.toEntity(productRequest)).map(productMapper::toDTO);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Mono<OrderDTO> createOrder(OrderDTO orderRequest) {
		Order order = orderMapper.toEntity(orderRequest);
		order.setOrderStatus(Order.OrderStatus.CREATED);

		return orderRepo.save(order).map(orderMapper::toDto);
	}

}
