package com.application.ordine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.application.ordine.controller.dto.OrderDTO;
import com.application.ordine.documents.Order;

@Mapper
public interface OrderMapper {

	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	/**
	 * please note: the typeOfOrder field between the two objects, while the
	 * remaining fields are mapped automatically based on their names and types
	 * 
	 * @param Order
	 * @return OrderDto
	 */

	@Mapping(target = "typeOfOrder", source = "typeOfOrder")
	OrderDTO toDTO(Order order);

	@Mapping(target = "typeOfOrder", source = "typeOfOrder")
	Order toEntity(OrderDTO orderDTO);
}
