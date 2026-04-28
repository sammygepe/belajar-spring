package com.sammy.belajar_spring.dto;

import java.math.BigDecimal;

public class TopCustomerResponse {

    private String customerName;
    private Long totalOrders;
    private BigDecimal totalSpend;

    public TopCustomerResponse(
            String customerName,
            Long totalOrders,
            BigDecimal totalSpend
    ) {
        this.customerName = customerName;
        this.totalOrders = totalOrders;
        this.totalSpend = totalSpend;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public BigDecimal getTotalSpend() {
        return totalSpend;
    }
}