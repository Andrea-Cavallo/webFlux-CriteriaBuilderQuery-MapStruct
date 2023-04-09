package com.application.products.service.impl;

import static com.application.products.utils.Constants.PRODUCT_NOT_FOUND;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.controller.exception.ProductNotFoundException;
import com.application.products.documents.Order;
import com.application.products.mapper.OrderMapper;
import com.application.products.repo.CustomRepository;
import com.application.products.service.OrderService;
import com.application.products.utils.Utils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderMapper orderMapper;
	private final CustomRepository<Order> orderRepositoryImpl;
	private Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	/**
	 * 
	 * Creates a new Order based on the provided OrderDTO.
	 * 
	 * @param orderRequest the OrderDTO containing the details of the new Order
	 * 
	 * @return a Mono that emits the created OrderDTO
	 * 
	 * @throws Exception if an error occurs while creating the Order
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Mono<OrderDTO> createOrder(OrderDTO orderRequest) {
		try {
			logger.info("OrderService: Create Order with order Request: {}", Utils.mapToJsonString(orderRequest));
			Order order = orderMapper.toEntity(orderRequest);
			order.setOrderStatus(Order.OrderStatus.CREATED);
			return orderRepositoryImpl.save(order).map(orderMapper::toDto);
		} catch (Exception ex) {
			logger.error("Error creating order", ex);
			throw ex;
		}
	}

	/**
	 * 
	 * Transcodes an incoming order by extracting and converting its details to an
	 * OrderDTO.
	 * 
	 * @param incomingOrderDTO the IncomingOrderDTO containing the details of the
	 *                         incoming order
	 * 
	 * @return a Mono that emits the transcoded OrderDTO
	 */
	@Override
	public Mono<OrderDTO> transcodeOrder(IncomingOrderDTO incomingOrderDTO) {
		logger.info("OrderService: Transcode incoming order: {}", Utils.mapToJsonString(incomingOrderDTO));
		OrderDTO.OrderInfo orderInfo = orderMapper.buildOrderInfo(incomingOrderDTO.getProductRequest());
		OrderDTO.UserInfo userInfo = orderMapper.buildUserInfo(incomingOrderDTO.getUserRequest());
		OrderDTO.ProductInfo productInfo = orderMapper.buildProductInfo(incomingOrderDTO.getProductRequest());
		OrderDTO.OrderStatus orderStatus = orderMapper.getOrderStatus(productInfo);
		logger.info("OrderService: orderInfo is {}", Utils.mapToJsonString(orderInfo));
		logger.info("OrderService: userInfo is {}", Utils.mapToJsonString(userInfo));
		logger.info("OrderService: productInfo is {}", Utils.mapToJsonString(productInfo));
		logger.info("OrderService: orderStatus is {}", Utils.mapToJsonString(orderStatus));
		return Mono.just(OrderDTO.builder().orderStatus(orderStatus).orderInfo(orderInfo).userInfo(userInfo)
				.productInfo(productInfo).build());
	}

	/**
	 * 
	 * Deletes an Order with the given ID.
	 * 
	 * @param orderId the ID of the Order to delete
	 * 
	 * @return a Mono that completes when the Order is deleted
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Mono<Void> deleteByOrderId(String orderId) {
		logger.info("In product service Order to delete has id: {}", orderId);
		return orderRepositoryImpl.findByName(orderId).flatMap(product -> orderRepositoryImpl.deleteById(orderId))
				.switchIfEmpty(Mono.error(new ProductNotFoundException(PRODUCT_NOT_FOUND)));
	}
}
