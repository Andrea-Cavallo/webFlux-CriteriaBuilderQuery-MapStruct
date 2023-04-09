package com.application.products.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.documents.Order;

@Component
public class OrderMapper {

	public OrderDTO toDto(Order order) {
		return OrderDTO.builder().orderStatus(OrderDTO.OrderStatus.valueOf(order.getOrderStatus().name()))
				.orderInfo(OrderDTO.OrderInfo.builder().orderId(order.getOrderInfo().getOrderId())
						.orderedAt(order.getOrderInfo().getOrderedAt())
						.deliveredAt(order.getOrderInfo().getDeliveredAt()).build())
				.userInfo(OrderDTO.UserInfo.builder().firstName(order.getUserInfo().getFirstName())
						.lastName(order.getUserInfo().getLastName()).email(order.getUserInfo().getEmail()).build())
				.productInfo(OrderDTO.ProductInfo.builder().productId(order.getProductInfo().getProductId())
						.productName(order.getProductInfo().getProductName()).price(order.getProductInfo().getPrice())
						.quantity(order.getProductInfo().getQuantity()).isInStock(order.getProductInfo().getIsInStock())
						.build())
				.build();
	}

	public Order toEntity(OrderDTO dto) {
		return Order.builder().orderStatus(Order.OrderStatus.valueOf(dto.getOrderStatus().name()))
				.orderInfo(Order.OrderInfo.builder().orderId(dto.getOrderInfo().getOrderId())
						.orderedAt(dto.getOrderInfo().getOrderedAt()).deliveredAt(dto.getOrderInfo().getDeliveredAt())
						.build())
				.userInfo(Order.UserInfo.builder().firstName(dto.getUserInfo().getFirstName())
						.lastName(dto.getUserInfo().getLastName()).email(dto.getUserInfo().getEmail()).build())
				.productInfo(Order.ProductInfo.builder().productId(dto.getProductInfo().getProductId())
						.productName(dto.getProductInfo().getProductName()).price(dto.getProductInfo().getPrice())
						.quantity(dto.getProductInfo().getQuantity()).isInStock(dto.getProductInfo().getIsInStock())
						.build())
				.build();
	}

	public OrderDTO.OrderInfo buildOrderInfo(IncomingOrderDTO.ProductRequest productInfo) {

		if (Boolean.TRUE.equals(productInfo.getIsInStock())) {
			return OrderDTO.OrderInfo.builder().orderId(UUID.randomUUID().toString())
					.orderedAt(LocalDateTime.now().toString()).deliveredAt(LocalDateTime.now().plusDays(15).toString())
					.build();
		} else {
			return OrderDTO.OrderInfo.builder().orderId(UUID.randomUUID().toString())
					.orderedAt(LocalDateTime.now().toString()).build();
		}

	}

	public OrderDTO.UserInfo buildUserInfo(IncomingOrderDTO.UserRequest userRequest) {
		String email = userRequest.getFirstName().concat(".").concat(userRequest.getLastName())
				.concat("@protonmail.com");
		return OrderDTO.UserInfo.builder().firstName(userRequest.getFirstName()).lastName(userRequest.getLastName())
				.email(email.toLowerCase()).build();
	}

	public OrderDTO.ProductInfo buildProductInfo(IncomingOrderDTO.ProductRequest productRequest) {

		return OrderDTO.ProductInfo.builder().productId(UUID.fromString(productRequest.getProductId()))
				.productName(productRequest.getProductName()).price(productRequest.getPrice())
				.quantity(productRequest.getQuantity()).isInStock(productRequest.getIsInStock()).build();
	}

	public OrderDTO.OrderStatus getOrderStatus(OrderDTO.ProductInfo productInfo) {
		if (Boolean.TRUE.equals(productInfo.getIsInStock())) {
			return OrderDTO.OrderStatus.PROCESSING;
		} else {
			return OrderDTO.OrderStatus.REFUSED;
		}
	}

}
