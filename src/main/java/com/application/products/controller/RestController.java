package com.application.products.controller;

import com.application.products.controller.handler.OrderHandler;
import com.application.products.controller.handler.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.application.products.utils.Constants.*;

@Configuration
@RequiredArgsConstructor
public class RestController {

    private final ProductHandler productHandler;
    private final OrderHandler orderHandler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .GET( PRODUCTS, productHandler::findProductsByCriteria )
                .GET( PRODUCTS_PRODUCT_ID, productHandler::findProductByProductId )
                .POST( PRODUCTS, productHandler::createProduct )
                .POST( ORDERS, orderHandler::createOrder )
                .POST( TRANSCODING, orderHandler::transcode )

                .build();
    }

}
