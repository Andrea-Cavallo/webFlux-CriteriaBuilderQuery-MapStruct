package com.application.products.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.documents.Order;
import com.application.products.mapper.OrderMapper;
import com.application.products.repo.CustomRepository;

import reactor.core.publisher.Mono;

class OrderServiceImplTest {

	@Mock
	private OrderMapper orderMapper;

	@Mock
	private CustomRepository<Order> orderRepositoryImpl;

	@InjectMocks
	private OrderServiceImpl orderService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateOrder() {
		OrderDTO orderRequest = OrderDTO.builder().orderStatus(OrderDTO.OrderStatus.CREATED)
				.orderInfo(OrderDTO.OrderInfo.builder().orderId(UUID.randomUUID().toString())
						.orderedAt(LocalDateTime.now().toString()).build())
				.userInfo(OrderDTO.UserInfo.builder().firstName("John").lastName("Doe").email("john.doe@example.com")
						.build())
				.productInfo(OrderDTO.ProductInfo.builder().productId(UUID.randomUUID()).productName("Product 1")
						.price(100.0).quantity(1).isInStock(true).build())
				.build();

		Order order = Order.builder().orderStatus(Order.OrderStatus.CREATED)
				.orderInfo(Order.OrderInfo.builder().orderId(UUID.randomUUID().toString())
						.orderedAt(LocalDateTime.now().toString()).build())
				.userInfo(Order.UserInfo.builder().firstName("John").lastName("Doe").email("john.doe@example.com")
						.build())
				.productInfo(Order.ProductInfo.builder().productId(UUID.randomUUID()).productName("Product 1")
						.price(100.0).quantity(1).isInStock(true).build())
				.build();

		when(orderMapper.toEntity(orderRequest)).thenReturn(order);
		when(orderRepositoryImpl.save(order)).thenReturn(Mono.just(order));
		when(orderMapper.toDto(order)).thenReturn(orderRequest);

		Mono<OrderDTO> result = orderService.createOrder(orderRequest);

		assertEquals(orderRequest, result.block());
	}

	@Test
	void testTranscodeOrder() {

		IncomingOrderDTO incomingOrderDTO = IncomingOrderDTO.builder()
				.userRequest(IncomingOrderDTO.UserRequest.builder().firstName("John").lastName("Doe").build())
				.productRequest(IncomingOrderDTO.ProductRequest.builder().productId(UUID.randomUUID().toString())
						.productName("Product 1").price(100.0).quantity(1).isInStock(true).build())
				.build();

		OrderDTO.OrderInfo orderInfo = OrderDTO.OrderInfo.builder().orderId(UUID.randomUUID().toString())
				.orderedAt(LocalDateTime.now().toString()).deliveredAt(LocalDateTime.now().plusDays(15).toString())
				.build();

		OrderDTO.UserInfo userInfo = OrderDTO.UserInfo.builder().firstName("John").lastName("Doe")
				.email("john.doe@example.com").build();

		OrderDTO.ProductInfo productInfo = OrderDTO.ProductInfo.builder().productId(UUID.randomUUID())
				.productName("Product 1").price(100.0).build();

		OrderDTO expectedOrderDTO = OrderDTO.builder().orderStatus(OrderDTO.OrderStatus.PROCESSING).orderInfo(orderInfo)
				.userInfo(userInfo).productInfo(productInfo).build();

		when(orderMapper.buildOrderInfo(incomingOrderDTO.getProductRequest())).thenReturn(orderInfo);
		when(orderMapper.buildUserInfo(incomingOrderDTO.getUserRequest())).thenReturn(userInfo);
		when(orderMapper.buildProductInfo(incomingOrderDTO.getProductRequest())).thenReturn(productInfo);
		when(orderMapper.getOrderStatus(productInfo)).thenReturn(OrderDTO.OrderStatus.PROCESSING);

		Mono<OrderDTO> result = orderService.transcodeOrder(incomingOrderDTO);

		assertEquals(expectedOrderDTO, result.block());
	}
}
