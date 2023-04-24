package com.eurder.eurder.api.order.dto;

import com.eurder.eurder.domain.item.ItemGroup;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrderDto {
    private final int orderId;
    private final int customerId;
    private final List<ItemGroup> itemGroupList;
    private final LocalDate orderDate;
    private double totalPrice;

    public OrderDto(int orderId, int customerId,List<ItemGroup> itemGroupList, LocalDate orderDate, double totalPrice) {
        this.orderId = orderId;
        this.itemGroupList = itemGroupList;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return orderId == orderDto.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", itemGroupList=" + itemGroupList +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
