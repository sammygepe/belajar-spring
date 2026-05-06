package com.sammy.belajar_spring.dto;

import java.math.BigDecimal;

public class OrderResponse {

    private Long id;
    private String invoice;
    private String customerName;
    private BigDecimal totalAmount;

    public OrderResponse() {}

    public OrderResponse(Long id, String invoice, String customerName, BigDecimal totalAmount) {
        this.id = id;
        this.invoice = invoice;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}