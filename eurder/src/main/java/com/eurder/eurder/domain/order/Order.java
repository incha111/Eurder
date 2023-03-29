package com.eurder.eurder.domain.order;

import com.eurder.eurder.domain.item.ItemGroup;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private static int counter;
    private final int orderId;
    private final List<ItemGroup> itemGroupList;
    //private final Customer customer;
    private final int customerId;
    private final LocalDate orderDate;
    private final double totalPrice;

    public Order(LocalDate orderDate, int customerId, List<ItemGroup> itemGroupList, double totalPrice) {
        this.orderId = ++counter;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
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

    public void addItemGroup(ItemGroup itemGroup){
        itemGroupList.add(itemGroup);
    }

    private double calculateTotalPrice(){
        return itemGroupList.stream()
                .map(i -> i.getGroupPrice())
                .reduce(0.0,(a,b) -> a + b);
    }
}
