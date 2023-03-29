package com.eurder.eurder.domain.order;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    public final List<Order> orderList;
    public OrderRepository() {
        this.orderList = new ArrayList<>();
    }

    public List<Order>getAllOrders(){
        return orderList;
    }
    public Order getOrderById(int orderId){
        return orderList.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No order found for order id " + orderId));
    }

    public Order save(Order order){
        orderList.add(order);
        return order;
    }


}
