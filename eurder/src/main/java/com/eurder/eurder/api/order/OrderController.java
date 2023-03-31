package com.eurder.eurder.api.order;

import com.eurder.eurder.api.order.dto.CreateOrderDto;
import com.eurder.eurder.api.order.dto.OrderDto;
import com.eurder.eurder.api.order.dto.ViewOrderReportDto;
import com.eurder.eurder.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    public final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable int orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("{customerId}/report")
    @ResponseStatus(HttpStatus.OK)
    public ViewOrderReportDto getAllCustomerOrders(@PathVariable int customerId){
        return orderService.getAllCustomerOrders(customerId);
    }

    @PostMapping(value = "register", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody CreateOrderDto createOrderDto){
        return orderService.createOrder(createOrderDto);
    }
    @PostMapping(value = "{orderId}/{customerId}/register", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reOrder(@PathVariable int orderId, @PathVariable int customerId){
        return orderService.reOrder(orderId,customerId);
    }
}
