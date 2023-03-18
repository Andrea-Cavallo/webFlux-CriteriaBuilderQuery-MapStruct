package com.application.ordine.mapper;

import com.application.ordine.controller.dto.OrderDTO;
import com.application.ordine.controller.dto.OrderDTO.OrderDTOBuilder;
import com.application.ordine.documents.Order;
import com.application.ordine.documents.Order.OrderBuilder;
import javax.annotation.processing.Generated;
import javax.inject.Named;
import javax.inject.Singleton;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-18T16:00:22+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Singleton
@Named
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.typeOfOrder( order.getTypeOfOrder() );
        orderDTO.deliveredAt( order.getDeliveredAt() );
        orderDTO.isInStock( order.getIsInStock() );
        orderDTO.orderedAt( order.getOrderedAt() );
        orderDTO.price( order.getPrice() );
        orderDTO.quantity( order.getQuantity() );
        orderDTO.transactionId( order.getTransactionId() );
        orderDTO.userName( order.getUserName() );

        return orderDTO.build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        OrderBuilder order = Order.builder();

        order.typeOfOrder( orderDTO.getTypeOfOrder() );
        order.deliveredAt( orderDTO.getDeliveredAt() );
        order.isInStock( orderDTO.getIsInStock() );
        order.orderedAt( orderDTO.getOrderedAt() );
        order.price( orderDTO.getPrice() );
        order.quantity( orderDTO.getQuantity() );
        order.transactionId( orderDTO.getTransactionId() );
        order.userName( orderDTO.getUserName() );

        return order.build();
    }
}
