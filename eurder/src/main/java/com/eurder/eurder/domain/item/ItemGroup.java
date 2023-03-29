package com.eurder.eurder.domain.item;

import java.time.LocalDate;

public class ItemGroup {
    public static final int SHIPPINGDAYS_WHEN_ITEM_IS_IN_STOCK = 1;
    public static final int SHIPPINGDAYS_WHEN_ITEM_IS_NOT_IN_STOCK = 7;
    private static int counter;
    private final int ItemGroupId;
    private final Item selectedItem;
    private final int orderedItemAmount;
    private LocalDate shippingDate;
    private double groupPrice;

    public ItemGroup(Item selectedItem, int orderedItemAmount) {
        this.ItemGroupId = ++counter;
        this.selectedItem = selectedItem;
        this.orderedItemAmount = orderedItemAmount;
        this.shippingDate = calculateShippingDate();
        this.groupPrice = calculateGroupPrice();
    }

    public int getItemGroupId() {
        return ItemGroupId;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getGroupPrice() {
        return groupPrice;
    }

    private LocalDate calculateShippingDate(){
        if(selectedItem.getStockAmount() >= this.orderedItemAmount){
            return shippingDate.plusDays(SHIPPINGDAYS_WHEN_ITEM_IS_IN_STOCK);
        } else {
            return shippingDate.plusDays(SHIPPINGDAYS_WHEN_ITEM_IS_NOT_IN_STOCK);
        }
    }
    private double calculateGroupPrice(){
        return selectedItem.getPrice() * orderedItemAmount;
    }

}
