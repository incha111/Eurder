package com.eurder.eurder.service.order;

import com.eurder.eurder.api.order.dto.OrderDto;
import com.eurder.eurder.api.order.dto.ViewOrderDto;
import com.eurder.eurder.api.order.dto.ViewOrderReportDto;
import com.eurder.eurder.domain.order.Order;
import com.eurder.eurder.service.Item.ItemMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final ItemMapper itemMapper;

    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public OrderDto toDto(Order order){
        return new OrderDto(
                order.getOrderId(),
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
    public ViewOrderDto toViewDto(Order order){
        return new ViewOrderDto(
                order.getOrderId(),
                itemMapper.toViewDto(order.getItemGroupList()),
                order.getTotalPrice() //getOrderTotalPrice
        );
    }
    public List<ViewOrderDto> toViewDto(List<Order> orderList){
        return orderList.stream()
                .map(o -> toViewDto(o))
                .collect(Collectors.toList());
    }
    public ViewOrderReportDto toViewReportDto(List<ViewOrderDto> viewOrderDtoList){
        return new ViewOrderReportDto(
                "Report of orders",
               viewOrderDtoList,
               viewOrderDtoList.stream()
                       .map(v -> v.getTotalOrderPrice())
                       .reduce(0.0,(a,b) -> a + b));

    }
}
