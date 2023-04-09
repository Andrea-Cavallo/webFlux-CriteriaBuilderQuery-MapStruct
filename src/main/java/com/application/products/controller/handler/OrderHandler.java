package com.application.products.controller.handler;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.service.OrderService;
import com.application.products.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;

    private Logger logger = LogManager.getLogger( OrderHandler.class );

    public Mono<ServerResponse> createOrder(ServerRequest request) {
        logger.info( "Order Handler: Received a request to create a new Order {}", Utils.mapToJsonString( request ) );
        return request.body( BodyExtractors.toMono( OrderDTO.class ) )
                .flatMap( orderDTO -> orderService.createOrder( orderDTO ).flatMap( orderCreated -> ServerResponse.ok()
                        .contentType( MediaType.APPLICATION_JSON ).body( BodyInserters.fromValue( orderCreated ) ) ) );
    }

    public Mono<ServerResponse> transcode(ServerRequest request) {
        logger.info( "Order Handler: Received a request to Transcode a new Order {}", Utils.mapToJsonString( request ) );
        return request.body( BodyExtractors.toMono( IncomingOrderDTO.class ) )
                .flatMap( orderToTranscode -> orderService.transcodeOrder( orderToTranscode )
                        .flatMap( orderTranscode -> ServerResponse.ok()
                                .contentType( MediaType.APPLICATION_JSON ).body( BodyInserters.fromValue( orderTranscode ) ) ) );

    }
}
