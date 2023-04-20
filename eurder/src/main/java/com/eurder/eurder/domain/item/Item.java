package com.eurder.eurder.domain.item;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "item")
public class Item {
    public static final int STOCK_LOW = 5;
    public static final int STOCK_MEDIUM = 10;
    //public static int counter;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    @Column(name = "stock_amount")
    private int stockAmount;
    @Column(name = "urgency_indicator")
    private UrgencyIndicator urgencyIndicator;

    public Item() {

    }

    public Item(String name, String description, double price, int stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.urgencyIndicator = changeUrgencyIndicator();
    }

    public Item(int id, String name, String description, double price, int stockAmount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.urgencyIndicator = changeUrgencyIndicator();
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
    public void changeName(String name) {
        this.name = name;
    }
    public void changePrice(double price){
        this.price = price;
    }
    public void changeStockAmount(int stockAmount){
        this.stockAmount = stockAmount;
    }
    public UrgencyIndicator changeUrgencyIndicator(){
        if(stockAmount >= 10){
            return UrgencyIndicator.STOCK_HIGH;
        }
        if(stockAmount >= 5 && stockAmount < 10){
            return UrgencyIndicator.STOCK_MEDIUM;
        }
        return UrgencyIndicator.STOCK_LOW;
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
