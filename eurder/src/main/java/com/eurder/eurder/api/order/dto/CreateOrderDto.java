package com.eurder.eurder.api.order.dto;

import com.eurder.eurder.api.item.dto.CreateItemGroupDto;
import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.domain.item.ItemGroup;

import java.time.LocalDate;
import java.util.List;

public class CreateOrderDto {
    private final List<CreateItemGroupDto> createItemGroupDtoList;
    private final int customerId;
    private final LocalDate orderDate;

    public CreateOrderDto(List<CreateItemGroupDto> createItemGroupDtoList, int customerId, LocalDate orderDate) {
        this.createItemGroupDtoList = createItemGroupDtoList;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public List<CreateItemGroupDto> getCreateItemGroupDto() {
        return createItemGroupDtoList;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
