package com.application.products.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6709550997374600901L;
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
	@Builder
	public static class OrderInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6469121778061108633L;
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
	@Builder
	public static class UserInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2724763420962848612L;
		private String firstName;
		private String lastName;
		private String email;
	}

	@AllArgsConstructor
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	@Builder
	public static class ProductInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -9116536928215081032L;
		private String productId;
		private String productName;
		private Double price;
		private Integer quantity;
		private Boolean isInStock;
	}
}