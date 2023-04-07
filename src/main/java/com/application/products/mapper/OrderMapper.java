package com.application.products.mapper;

import org.springframework.stereotype.Component;

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
}
