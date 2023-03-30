package com.eurder.eurder.domain.item;

import java.util.Objects;

public class Item {
    public static int counter;
    private final int id;
    private String name;
    private String description;
    private double price;
    private int stockAmount;

    public Item(String name, String description, double price, int stockAmount) {
        this.id = ++counter;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
