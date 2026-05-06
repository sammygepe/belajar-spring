package com.sammy.belajar_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO untuk menerima request dari client saat membuat order
 */
public class CreateOrderRequest {

    // ID user wajib ada (untuk relasi ke user)
    @NotNull(message = "User ID tidak boleh kosong")
    private Long userId;

    // Invoice wajib diisi
    @NotBlank(message = "Invoice tidak boleh kosong")
    private String invoiceNo;

    // Tanggal order wajib diisi
    @NotNull(message = "Tanggal order tidak boleh kosong")
    private LocalDate orderDate;

    // Minimal 1 item
    @Size(min = 1, message = "Minimal 1 item")
    private List<CreateOrderDetailRequest> details;

    public @NotNull(message = "User ID tidak boleh kosong") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID tidak boleh kosong") Long userId) {
        this.userId = userId;
    }

    public @NotBlank(message = "Invoice tidak boleh kosong") String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(@NotBlank(message = "Invoice tidak boleh kosong") String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public @NotNull(message = "Tanggal order tidak boleh kosong") LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@NotNull(message = "Tanggal order tidak boleh kosong") LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public @Size(min = 1, message = "Minimal 1 item") List<CreateOrderDetailRequest> getDetails() {
        return details;
    }

    public void setDetails(@Size(min = 1, message = "Minimal 1 item") List<CreateOrderDetailRequest> details) {
        this.details = details;
    }
}