package com.eurder.eurder.api.order;

import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.domain.item.ItemGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public int getCustomer() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
