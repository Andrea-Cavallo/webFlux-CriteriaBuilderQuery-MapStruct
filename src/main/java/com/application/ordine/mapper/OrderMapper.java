package com.application.ordine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.application.ordine.controller.dto.OrderDTO;
import com.application.ordine.documents.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	 OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	    @Mappings({
	        @Mapping(source = "transactionId", target = "transactionId"),
	        @Mapping(source = "userName", target = "userName"),
	        // Uncomment the following line if you want to use the typeOfOrder property
	        // @Mapping(source = "typeOfOrder", target = "typeOfOrder"),
	        @Mapping(source = "orderedAt", target = "orderedAt"),
	        @Mapping(source = "deliveredAt", target = "deliveredAt"),
	        @Mapping(source = "price", target = "price"),
	        @Mapping(source = "quantity", target = "quantity"),
	        @Mapping(source = "isInStock", target = "isInStock")
	    })
	    OrderDTO toDTO(Order order);

	    @Mappings({
	        @Mapping(source = "transactionId", target = "transactionId"),
	        @Mapping(source = "userName", target = "userName"),
	        // Uncomment the following line if you want to use the typeOfOrder property
	        // @Mapping(source = "typeOfOrder", target = "typeOfOrder"),
	        @Mapping(source = "orderedAt", target = "orderedAt"),
	        @Mapping(source = "deliveredAt", target = "deliveredAt"),
	        @Mapping(source = "price", target = "price"),
	        @Mapping(source = "quantity", target = "quantity"),
	        @Mapping(source = "isInStock", target = "isInStock")
	    })
	    Order toEntity(OrderDTO orderDTO);

}
