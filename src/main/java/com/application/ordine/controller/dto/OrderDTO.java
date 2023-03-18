package com.application.ordine.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
@NoArgsConstructor
@Setter
@ToString

public class OrderDTO implements Dto {

	@NotNull
	private String transactionId;
	@NotNull
	private String userName;
	@NotNull
	@JsonProperty("typeOfOrder")
	private OrderType typeOfOrder;
	@NotNull
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
	private LocalDateTime orderedAt;
	@NotNull
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
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
