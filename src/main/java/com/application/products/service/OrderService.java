package com.application.products.service;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<OrderDTO> createOrder(OrderDTO orderRequest);

    Mono<OrderDTO> transcodeOrder(IncomingOrderDTO incomingOrder);

}
