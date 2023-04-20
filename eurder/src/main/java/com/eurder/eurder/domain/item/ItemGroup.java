package com.eurder.eurder.domain.item;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "item_group")
public class ItemGroup {

    //private static int counter;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "order_item_amount")
    private int orderedItemAmount;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;
    @Transient
    private double groupPrice;

    public ItemGroup() {
    }

    public ItemGroup(Item item, int orderedItemAmount, LocalDate shippingDate, double groupPrice) {
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

}
