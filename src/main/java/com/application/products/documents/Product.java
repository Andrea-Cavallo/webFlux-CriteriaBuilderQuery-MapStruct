package com.application.products.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product implements Serializable {

    private String productId;
    private String productName;
    private Double price;
    private Long quantity;
    private Boolean isInStock;
}
