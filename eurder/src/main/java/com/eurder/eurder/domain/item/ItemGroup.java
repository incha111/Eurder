package com.eurder.eurder.domain.item;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "item_group")
public class ItemGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "item_price")
    private double itemPrice;
    @Column(name = "ordered_item_amount")
    private int orderedItemAmount;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;
    @Column(name = "fk_order_id")
    private int orderId;
    @Transient
    private double groupPrice;

    public ItemGroup() {
    }

    public ItemGroup(Item item, int orderedItemAmount, LocalDate shippingDate,double groupPrice) {
        this.item = item;
        this.orderedItemAmount = orderedItemAmount;
        this.shippingDate = shippingDate;
        this.groupPrice = groupPrice;
        this.itemPrice = item.getPrice();
        calculateGroupPrice();
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public double getItemPrice() {
        return itemPrice;
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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void calculateGroupPrice(){
        this.groupPrice =orderedItemAmount * this.itemPrice;
    }
}
