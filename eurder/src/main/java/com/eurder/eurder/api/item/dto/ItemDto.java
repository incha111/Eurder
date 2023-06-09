package com.eurder.eurder.api.item.dto;

import com.eurder.eurder.domain.item.UrgencyIndicator;

import java.util.Objects;

public class ItemDto {
    private final int id;
    private final String name;
    private String description;
    private double price;
    private int stockAmount;
    private UrgencyIndicator urgencyIndicator;

    public ItemDto(int id, String name, String description, double price, int stockAmount, UrgencyIndicator urgencyIndicator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.urgencyIndicator = urgencyIndicator;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public UrgencyIndicator getUrgencyIndicator() {
        return urgencyIndicator;
    }

    public void changeDescription(String description){
        this.description = description;
    }

    public void changePrice(double price){
        this.price = price;
    }
    public void changeStockAmount(int stockAmount){
        this.stockAmount = stockAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return id == itemDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
