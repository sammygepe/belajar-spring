package com.sammy.belajar_spring.dto;

import java.math.BigDecimal;

public class DashboardSummaryResponse {

    private Long totalOrders;
    private BigDecimal totalRevenue;
    private BigDecimal averageOrder;

    public DashboardSummaryResponse(
            Long totalOrders,
            BigDecimal totalRevenue,
            BigDecimal averageOrder
    ) {
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.averageOrder = averageOrder;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public BigDecimal getAverageOrder() {
        return averageOrder;
    }
}