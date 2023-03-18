package com.application.ordine.documents;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.mapstruct.Mapper;
import org.springframework.data.mongodb.core.mapping.Document;

import com.application.ordine.documents.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Document(collection = "order")
@NoArgsConstructor
@Setter
@ToString
@Mapper
public class Order {
	
	@NotNull
	private String transactionId;
	@NotNull
	private String userName;
	@NotNull
	@JsonProperty("typeOfOrder")

	private OrderType typeOfOrder;
	@NotNull
	private LocalDateTime orderedAt;
	@NotNull
	private LocalDateTime deliveredAt;
	@NotNull
	@Positive
	private Double price;
	@NotNull
	@Positive
	private Long quantity;
	@NotNull
	private Boolean isInStock;

	
}
