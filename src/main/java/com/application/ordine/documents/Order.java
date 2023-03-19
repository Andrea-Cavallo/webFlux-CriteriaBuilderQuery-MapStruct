package com.application.ordine.documents;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {

	private String transactionId;
	private String userName;
	// Uncomment the following line if you want to use the typeOfOrder property
	// private OrderType typeOfOrder;
	private LocalDateTime orderedAt;
	private LocalDateTime deliveredAt;
	private Double price;
	private Long quantity;
	private Boolean isInStock;
}
