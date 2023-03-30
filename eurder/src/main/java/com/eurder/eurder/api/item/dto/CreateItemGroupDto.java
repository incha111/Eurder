package com.eurder.eurder.api.item.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateItemGroupDto {
    private final int id;
    private final int orderedItemAmount;

    public CreateItemGroupDto(int id,  int orderedItemAmount) {
        this.id = id;
        this.orderedItemAmount = orderedItemAmount;
    }

    public int getId() {
        return id;
    }

    public int getOrderedItemAmount() {
        return orderedItemAmount;
    }
}
