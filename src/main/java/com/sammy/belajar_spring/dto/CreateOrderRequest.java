package com.sammy.belajar_spring.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

// DTO create order header
public class CreateOrderRequest {

    // User id wajib ada
    @NotNull(message = "User wajib dipilih")
    private Long userId;

    // Nomor invoice
    @NotNull(message = "Invoice wajib diisi")
    private String invoiceNo;

    // Tanggal order
    @NotNull(message = "Tanggal wajib diisi")
    private LocalDate orderDate;

    // Minimal 1 item
    @Valid
    @NotEmpty(message = "Detail order wajib ada")
    private List<CreateOrderDetailRequest> details;

    // Getter Setter
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<CreateOrderDetailRequest> getDetails() {
        return details;
    }

    public void setDetails(List<CreateOrderDetailRequest> details) {
        this.details = details;
    }
}