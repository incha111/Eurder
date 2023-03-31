package com.eurder.eurder.domain.item;

import java.time.LocalDate;

public class ItemGroup {

    private static int counter;
    private final int id;
    private Item item;
    private ItemCopy itemCopy;
    private final int orderedItemAmount;
    private final LocalDate shippingDate;
    private final double groupPrice;

    public ItemGroup(Item item, int orderedItemAmount, LocalDate shippingDate, double groupPrice) {
        this.id = ++counter;
        this.item = item;
        this.orderedItemAmount = orderedItemAmount;
        this.shippingDate = shippingDate;
        this.groupPrice = groupPrice;
    }

    public int getId() {
        return id;
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

    private class ItemCopy{
        private int id;
        private String name;
        private String description;
        private double price;
        private int stockAmount;

        public ItemCopy(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.description = item.getDescription();
            this.price = item.getPrice();
            this.stockAmount = item.getStockAmount();
        }
    }

}
