package com.application.products.controller.dto;

import java.time.LocalDateTime;
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

public class OrderDTO {

	private OrderStatus orderStatus;
	private OrderInfo orderInfo;
	private UserInfo userInfo;
	private ProductInfo productInfo;

	@AllArgsConstructor
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	@Builder(toBuilder = true)

	public static class OrderInfo {
		private String orderId;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
		private LocalDateTime orderedAt;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
		private LocalDateTime deliveredAt;
	}

	@AllArgsConstructor
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	@Builder(toBuilder = true)

	public static class UserInfo {
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

	public static class ProductInfo {
		private UUID productId;
		private String productName;
		private Double price;
		private Integer quantity;
		private Boolean isInStock;
	}

	public enum OrderStatus {
		PROCESSING, REFUSED, CREATED
	}
}
