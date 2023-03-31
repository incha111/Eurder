package com.eurder.eurder.api.item.dto;

public class ViewItemGroupReport {
    private final String itemName;
    private final int orderedAmount;
    private final double groupPrice;

    public ViewItemGroupReport(String itemName, int orderedAmount, double groupPrice) {
        this.itemName = itemName;
        this.orderedAmount = orderedAmount;
        this.groupPrice = groupPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getOrderedAmount() {
        return orderedAmount;
    }

    public double getGroupPrice() {
        return groupPrice;
    }
}
