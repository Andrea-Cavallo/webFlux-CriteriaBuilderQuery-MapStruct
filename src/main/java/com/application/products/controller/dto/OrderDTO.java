package com.application.products.controller.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@ToString

public class OrderDTO implements Serializable {

	private OrderStatus orderStatus;
	private OrderInfo orderInfo;
	private UserInfo userInfo;
	private ProductInfo productInfo;

	public enum OrderStatus {
		PROCESSING, REFUSED, CREATED
	}

	@AllArgsConstructor
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	public static class OrderInfo implements Serializable {
		private String orderId;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
		private String orderedAt;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
		private String deliveredAt;
	}

	@AllArgsConstructor
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	public static class UserInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String firstName;
		private String lastName;
		private String email;
	}

	@AllArgsConstructor
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	@Builder(toBuilder = true)

	public static class ProductInfo implements Serializable {
		private UUID productId;
		private String productName;
		private Double price;
		private Integer quantity;
		private Boolean isInStock;
	}
}
