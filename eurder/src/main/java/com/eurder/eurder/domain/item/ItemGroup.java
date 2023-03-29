package com.eurder.eurder.domain.item;

import java.time.LocalDate;

public class ItemGroup {

    private static int counter;
    private final int ItemGroupId;
    //private final Item selectedItem;
    private final Item item;
    private final int orderedItemAmount;
    private final LocalDate shippingDate;
    private final double groupPrice;

    public ItemGroup(Item item, int orderedItemAmount, LocalDate shippingDate, double groupPrice) {
        this.ItemGroupId = ++counter;
        this.item = item;
        this.orderedItemAmount = orderedItemAmount;
        this.shippingDate = shippingDate;
        this.groupPrice = groupPrice;
    }

    public int getItemGroupId() {
        return ItemGroupId;
    }

    public Item getItem() {
        return item;
    }

    public int getOrderedItemAmount() {
        return orderedItemAmount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getGroupPrice() {
        return groupPrice;
    }

}
