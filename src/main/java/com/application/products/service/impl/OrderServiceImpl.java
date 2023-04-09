package com.application.products.service.impl;

import com.application.products.controller.dto.IncomingOrderDTO;
import com.application.products.controller.dto.OrderDTO;
import com.application.products.documents.Order;
import com.application.products.mapper.OrderMapper;
import com.application.products.repo.CustomRepository;
import com.application.products.service.OrderService;
import com.application.products.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final CustomRepository<Order> orderRepositoryImpl;
    private Logger logger = LogManager.getLogger( OrderServiceImpl.class );

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<OrderDTO> createOrder(OrderDTO orderRequest) {
        logger.info( "OrderService: Create Order with order Request: {}", Utils.mapToJsonString( orderRequest ) );
        Order order = orderMapper.toEntity( orderRequest );
        order.setOrderStatus( Order.OrderStatus.CREATED );
        return orderRepositoryImpl.save( order ).map( orderMapper::toDto );
    }

    @Override
    public Mono<OrderDTO> transcodeOrder(IncomingOrderDTO incomingOrderDTO) {
        logger.info( "OrderService: Transcode incoming order: {}", Utils.mapToJsonString( incomingOrderDTO ) );

        OrderDTO.OrderInfo orderInfo = orderMapper.buildOrderInfo( incomingOrderDTO.getProductRequest() );
        logger.info( "OrderService: orderInfo is {}", Utils.mapToJsonString( orderInfo ) );

        OrderDTO.UserInfo userInfo = orderMapper.buildUserInfo( incomingOrderDTO.getUserRequest() );
        logger.info( "OrderService: userInfo is {}", Utils.mapToJsonString( userInfo ) );

        OrderDTO.ProductInfo productInfo = orderMapper.buildProductInfo( incomingOrderDTO.getProductRequest() );
        logger.info( "OrderService: productInfo is {}", Utils.mapToJsonString( productInfo ) );

        OrderDTO.OrderStatus orderStatus = orderMapper.getOrderStatus( productInfo );
        logger.info( "OrderService: orderStatus is {}", Utils.mapToJsonString( orderStatus ) );

        return Mono.just( OrderDTO.builder()
                .orderStatus( orderStatus )
                .orderInfo( orderInfo )
                .userInfo( userInfo )
                .productInfo( productInfo )
                .build() )
                ;
    }
}

