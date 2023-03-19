package com.application.ordine.mapper;

import com.application.ordine.controller.dto.OrderDTO;
import com.application.ordine.documents.Order;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T12:02:36+0100",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.transactionId( order.getTransactionId() );
        orderDTO.userName( order.getUserName() );
        orderDTO.orderedAt( order.getOrderedAt() );
        orderDTO.deliveredAt( order.getDeliveredAt() );
        orderDTO.price( order.getPrice() );
        orderDTO.quantity( order.getQuantity() );
        orderDTO.isInStock( order.getIsInStock() );

        return orderDTO.build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.transactionId( orderDTO.getTransactionId() );
        order.userName( orderDTO.getUserName() );
        order.orderedAt( orderDTO.getOrderedAt() );
        order.deliveredAt( orderDTO.getDeliveredAt() );
        order.price( orderDTO.getPrice() );
        order.quantity( orderDTO.getQuantity() );
        order.isInStock( orderDTO.getIsInStock() );

        return order.build();
    }
}
