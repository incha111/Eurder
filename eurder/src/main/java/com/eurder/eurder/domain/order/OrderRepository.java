package com.eurder.eurder.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOrderByOrderIdAndCustomerId(int orderId,int customerId);
    List<Order> findOrdersByCustomerId(int customerId);
    Order findOrderByOrderId(int orderId);

    @Query("select (max(o.orderId)) from Order o")
    int findMaxId();
}
