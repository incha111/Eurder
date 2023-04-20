package com.eurder.eurder.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOrderByOrderIdAndCustomerId(int orderId,int customerId);
    List<Order> findOrdersByCustomerId(int customerId);
}
