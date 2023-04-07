package com.application.products.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.application.products.controller.dto.ProductDTO;
import com.application.products.documents.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	@Mappings({ @Mapping(source = "productId", target = "productId"),
			@Mapping(source = "productName", target = "productName"),

			@Mapping(source = "price", target = "price"), @Mapping(source = "quantity", target = "quantity"),
			@Mapping(source = "isInStock", target = "isInStock") })
	ProductDTO toDTO(Product product);

	@Mappings({ @Mapping(source = "productId", target = "productId"),
			@Mapping(source = "productName", target = "productName"), @Mapping(source = "price", target = "price"),
			@Mapping(source = "quantity", target = "quantity"), @Mapping(source = "isInStock", target = "isInStock") })
	Product toEntity(ProductDTO productDTO);

}
