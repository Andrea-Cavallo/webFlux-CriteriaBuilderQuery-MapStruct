package com.application.products.mapper;

import com.application.products.controller.dto.ProductDTO;
import com.application.products.documents.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId( product.getProductId() );
        productDTO.setProductName( product.getProductName() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setQuantity( product.getQuantity() );
        productDTO.setIsInStock( product.getIsInStock() );
        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId( productDTO.getProductId() );
        product.setProductName( productDTO.getProductName() );
        product.setPrice( productDTO.getPrice() );
        product.setQuantity( productDTO.getQuantity() );
        product.setIsInStock( productDTO.getIsInStock() );
        return product;
    }
}

