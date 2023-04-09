package com.application.products.documents;

import java.io.Serializable;
import java.util.UUID;

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
	public static class OrderInfo {
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
	@Builder
	public static class ProductInfo {
		private UUID productId;
		private String productName;
		private Double price;
		private Integer quantity;
		private Boolean isInStock;
	}
}