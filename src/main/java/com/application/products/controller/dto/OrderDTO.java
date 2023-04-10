package com.application.products.controller.dto;

import java.io.Serializable;

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

public class OrderDTO implements Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7998197139881654526L;
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
	public static class OrderInfo implements Dto {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2232307970853341817L;
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
	public static class UserInfo implements Dto {
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
		/**
		 * 
		 */
		private static final long serialVersionUID = -6916229809506856199L;
		private String productId;
		private String productName;
		private Double price;
		private Integer quantity;
		private Boolean isInStock;
	}
}
