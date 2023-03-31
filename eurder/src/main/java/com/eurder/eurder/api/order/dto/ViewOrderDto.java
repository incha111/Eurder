package com.eurder.eurder.api.order.dto;

import com.eurder.eurder.api.item.dto.ViewItemGroupReport;

import java.util.List;

public class ViewOrderDto {
    private final int orderId;
    private final List<ViewItemGroupReport> viewItemGroupReports;
    private final double totalOrderPrice;

    public ViewOrderDto(int orderId, List<ViewItemGroupReport> viewItemGroupReports, double totalOrderPrice) {
        this.orderId = orderId;
        this.viewItemGroupReports = viewItemGroupReports;
        this.totalOrderPrice = totalOrderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<ViewItemGroupReport> getViewItemGroupReports() {
        return viewItemGroupReports;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

}
