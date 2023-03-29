package com.eurder.eurder.domain.item;

public class Item {
    public static int counter;
    private final int itemId;
    private String name;
    private String description;
    private double price;
    private int stockAmount;

    public Item(String name, String description, double price, int stockAmount) {
        this.itemId = ++counter;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public int getItemId() {
        return itemId;
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

    public void changeDescription(String description){
        this.description = description;
    }
    public void changeName(String name) {
        this.name = name;
    }
    public void changePrice(double price){
        this.price = price;
    }
    public void changeStockAmount(int stockAmount){
        this.stockAmount = stockAmount;
    }


}
