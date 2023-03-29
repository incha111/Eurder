package com.eurder.eurder.api.item.dto;

public class CreateItemDto {
    private final String name;
    private String description;
    private double price;
    private int stockAmount;

    public CreateItemDto(String name, String description, double price, int stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
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
}
