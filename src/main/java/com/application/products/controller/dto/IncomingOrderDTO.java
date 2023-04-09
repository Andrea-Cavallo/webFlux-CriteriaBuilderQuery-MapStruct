package com.application.products.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IncomingOrderDTO implements Dto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderStatus orderStatus;
	private UserRequest userRequest;
	private ProductRequest productRequest;

	public enum OrderStatus {
		PROCESSING, REFUSED, CREATED
	}

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@EqualsAndHashCode
	public static class UserRequest implements Dto {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7688441553931692448L;
		private String firstName;
		private String lastName;
		private Integer quantity;
		private String productName;
	}

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@EqualsAndHashCode
	public static class ProductRequest implements Dto {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3205848797543138470L;
		private String productId;
		private String productName;
		private Double price;
		private Integer quantity;
		private Boolean isInStock;

	}
}
