package com.eurder.eurder.api.order;

import com.eurder.eurder.api.order.dto.CreateOrderDto;
import com.eurder.eurder.api.order.dto.OrderDto;
import com.eurder.eurder.api.order.dto.ViewOrderReportDto;
import com.eurder.eurder.service.order.OrderService;
import com.eurder.eurder.service.security.Feature;
import com.eurder.eurder.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    public final OrderService orderService;
    public final SecurityService securityService;

    @Autowired
    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.VIEW_ALL_ORDERS);
        return orderService.getAllOrders();
    }

    @GetMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable int orderId, @RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.VIEW_SINGLE_ORDER);
        return orderService.getOrderById(orderId);
    }

    @GetMapping("{customerId}/report")
    @ResponseStatus(HttpStatus.OK)
    public ViewOrderReportDto getCustomerOrders(@PathVariable int customerId, @RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.VIEW_ORDER_REPORT);
        return orderService.getCustomerOrders(customerId);
    }

    @PostMapping(value = "register", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody CreateOrderDto createOrderDto,@RequestHeader String authorization){
        //securityService.validateAuthorization(authorization, Feature.CREATE_ORDER);
        return orderService.createOrder(createOrderDto);
    }
    @PostMapping(value = "{orderId}/{customerId}/register", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reOrder(@PathVariable int orderId, @PathVariable int customerId,@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.RE_ORDER);
        return orderService.reOrder(orderId,customerId);
    }
}
