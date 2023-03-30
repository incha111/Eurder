package com.eurder.eurder.service.order;

import com.eurder.eurder.api.order.OrderDto;
import com.eurder.eurder.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderDto toDto(Order order){
        return new OrderDto(
                order.getId(),
                order.getCustomerId(),
                order.getItemGroupList(),
                order.getOrderDate(),
                order.getTotalPrice()
        );
    }
    public List<OrderDto> toDto(List<Order> orderList){
        return orderList.stream()
                .map(o -> toDto(o))
                .collect(Collectors.toList());
    }
}
