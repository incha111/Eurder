package com.eurder.eurder.api.item.dto;

public class CreateItemGroupDto {
    private final int itemId;
    private final int orderedItemAmount;

    public CreateItemGroupDto(int itemId, int orderedItemAmount) {
        this.itemId = itemId;
        this.orderedItemAmount = orderedItemAmount;
    }

    public int getItemId() {
        return itemId;
    }

    public int getOrderedItemAmount() {
        return orderedItemAmount;
    }
}
