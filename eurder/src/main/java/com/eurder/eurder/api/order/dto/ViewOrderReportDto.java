package com.eurder.eurder.api.order.dto;

import java.util.List;

public class ViewOrderReportDto {
    private final String title;
    private final List<ViewOrderDto> viewOrderDtoList;
    private final double totalOfAllOrderPrice;

    public ViewOrderReportDto(String title, List<ViewOrderDto> viewOrderDtoList, double totalOfAllOrderPrice) {
        this.title = title;
        this.viewOrderDtoList = viewOrderDtoList;
        this.totalOfAllOrderPrice = totalOfAllOrderPrice;
    }

    public String getTitle() {
        return title;
    }

    public List<ViewOrderDto> getViewOrderDtoList() {
        return viewOrderDtoList;
    }

    public double getTotalOfAllOrderPrice() {
        return totalOfAllOrderPrice;
    }
}
