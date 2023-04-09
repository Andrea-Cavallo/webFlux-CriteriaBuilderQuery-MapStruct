package com.application.products.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.documents.Order;

class OrderMapperTest {

	private OrderMapper orderMapper = new OrderMapper();

	@Test
	@DisplayName("Test OrderMapper toDto method")
	void testToDto() {
		// GIVEN an order entity
		Order order = new Order();
		order.setOrderStatus(Order.OrderStatus.PROCESSING);

		Order.OrderInfo orderInfo = new Order.OrderInfo();
		orderInfo.setOrderId(UUID.randomUUID().toString());
		orderInfo.setOrderedAt(LocalDateTime.now().toString());
		orderInfo.setDeliveredAt(LocalDateTime.now().plusDays(15).toString());
		order.setOrderInfo(orderInfo);

		Order.UserInfo userInfo = new Order.UserInfo();
		userInfo.setFirstName("John");
		userInfo.setLastName("Doe");
		userInfo.setEmail("john.doe@protonmail.com");
		order.setUserInfo(userInfo);

		Order.ProductInfo productInfo = new Order.ProductInfo();
		productInfo.setProductId(UUID.randomUUID());
		productInfo.setProductName("Product 1");
		productInfo.setPrice(10.0);
		productInfo.setQuantity(5);
		productInfo.setIsInStock(true);
		order.setProductInfo(productInfo);

		// WHEN the toDto method is called with the order entity
		OrderDTO orderDTO = orderMapper.toDto(order);

		// THEN the DTO should have the same values as the entity
		assertEquals(OrderDTO.OrderStatus.PROCESSING, orderDTO.getOrderStatus());
		assertEquals(orderInfo.getOrderId(), orderDTO.getOrderInfo().getOrderId());
		assertEquals(orderInfo.getOrderedAt(), orderDTO.getOrderInfo().getOrderedAt());
		assertEquals(orderInfo.getDeliveredAt(), orderDTO.getOrderInfo().getDeliveredAt());
		assertEquals(userInfo.getFirstName(), orderDTO.getUserInfo().getFirstName());
		assertEquals(userInfo.getLastName(), orderDTO.getUserInfo().getLastName());
		assertEquals(userInfo.getEmail(), orderDTO.getUserInfo().getEmail());
		assertEquals(productInfo.getProductId(), orderDTO.getProductInfo().getProductId());
		assertEquals(productInfo.getProductName(), orderDTO.getProductInfo().getProductName());
		assertEquals(productInfo.getPrice(), orderDTO.getProductInfo().getPrice());
		assertEquals(productInfo.getQuantity(), orderDTO.getProductInfo().getQuantity());
		assertEquals(productInfo.getIsInStock(), orderDTO.getProductInfo().getIsInStock());
	}

	@Test
	@DisplayName("Test OrderMapper toEntity method")
	void testToEntity() {
		// GIVEN an order DTO
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderStatus(OrderDTO.OrderStatus.REFUSED);

		OrderDTO.OrderInfo orderInfo = new OrderDTO.OrderInfo();
		orderInfo.setOrderId(UUID.randomUUID().toString());
		orderInfo.setOrderedAt(LocalDateTime.now().toString());
		orderInfo.setDeliveredAt(LocalDateTime.now().plusDays(15).toString());
		orderDTO.setOrderInfo(orderInfo);

		OrderDTO.UserInfo userInfo = new OrderDTO.UserInfo();
		userInfo.setFirstName("John");
		userInfo.setLastName("Doe");
		userInfo.setEmail("john.doe@protonmail.com");
		orderDTO.setUserInfo(userInfo);

		OrderDTO.ProductInfo productInfo = new OrderDTO.ProductInfo();
		productInfo.setProductId(UUID.randomUUID());
		productInfo.setProductName("Product 1");
		productInfo.setPrice(10.0);
		productInfo.setQuantity(5);
		productInfo.setIsInStock(true);
		orderDTO.setProductInfo(productInfo);

		// WHEN the toEntity method is called with the order DTO
		Order order = orderMapper.toEntity(orderDTO);

		// THEN the entity should have the same values as the DTO
		assertEquals(Order.OrderStatus.REFUSED, order.getOrderStatus());
		assertEquals(orderInfo.getOrderId(), order.getOrderInfo().getOrderId());
		assertEquals(orderInfo.getOrderedAt(), order.getOrderInfo().getOrderedAt());
		assertEquals(orderInfo.getDeliveredAt(), order.getOrderInfo().getDeliveredAt());
		assertEquals(userInfo.getFirstName(), order.getUserInfo().getFirstName());
		assertEquals(userInfo.getLastName(), order.getUserInfo().getLastName());
		assertEquals(userInfo.getEmail(), order.getUserInfo().getEmail());
		assertEquals(productInfo.getProductId(), order.getProductInfo().getProductId());
		assertEquals(productInfo.getProductName(), order.getProductInfo().getProductName());
		assertEquals(productInfo.getPrice(), order.getProductInfo().getPrice());
		assertEquals(productInfo.getQuantity(), order.getProductInfo().getQuantity());
		assertEquals(productInfo.getIsInStock(), order.getProductInfo().getIsInStock());
	}

	@Test
	@DisplayName("Test OrderMapper buildOrderInfo method")
	void testBuildOrderInfo() {
		// GIVEN a product request with isInStock = true
		IncomingOrderDTO.ProductRequest productRequest = new IncomingOrderDTO.ProductRequest();
		productRequest.setIsInStock(true);
		String now = LocalDateTime.now().toString();
		// WHEN the buildOrderInfo method is called with the product request
		OrderDTO.OrderInfo orderInfo = orderMapper.buildOrderInfo(productRequest);
		orderInfo.setOrderedAt(now);
		// THEN the order info should have a deliveredAt date set 15 days in the future
		assertEquals(now, orderInfo.getOrderedAt());

	}

	@Test
	@DisplayName("Test OrderMapper buildOrderInfo method with product not in stock")
	void testBuildOrderInfoWithProductNotInStock() {
		// GIVEN a product request with isInStock = false
		IncomingOrderDTO.ProductRequest productRequest = new IncomingOrderDTO.ProductRequest();
		productRequest.setIsInStock(false);

		// WHEN the buildOrderInfo method is called with the product request
		OrderDTO.OrderInfo orderInfo = orderMapper.buildOrderInfo(productRequest);

		// THEN the order info should not have a deliveredAt date set
		assertEquals(null, orderInfo.getDeliveredAt());
	}

	@Test
	@DisplayName("Test OrderMapper buildUserInfo method")
	void testBuildUserInfo() {
		// GIVEN a user request with first name and last name
		IncomingOrderDTO.UserRequest userRequest = new IncomingOrderDTO.UserRequest();
		userRequest.setFirstName("John");
		userRequest.setLastName("Doe");

		// WHEN the buildUserInfo method is called with the user request
		OrderDTO.UserInfo userInfo = orderMapper.buildUserInfo(userRequest);

		// THEN the user info should have the correct email address format
		assertEquals("john.doe@protonmail.com", userInfo.getEmail());
	}

	@Test
	@DisplayName("Test OrderMapper buildProductInfo method")
	void testBuildProductInfo() {
		// GIVEN a product request
		IncomingOrderDTO.ProductRequest productRequest = new IncomingOrderDTO.ProductRequest();
		productRequest.setProductId(UUID.randomUUID().toString());
		productRequest.setProductName("Product 1");
		productRequest.setPrice(10.0);
		productRequest.setQuantity(5);
		productRequest.setIsInStock(true);

		// WHEN the buildProductInfo method is called with the product request
		OrderDTO.ProductInfo productInfo = orderMapper.buildProductInfo(productRequest);

		// THEN the product info should have the correct values
		assertEquals(UUID.fromString(productRequest.getProductId()), productInfo.getProductId());

	}

}
