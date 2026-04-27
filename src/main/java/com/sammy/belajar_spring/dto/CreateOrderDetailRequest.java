package com.sammy.belajar_spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

// DTO item detail order
public class CreateOrderDetailRequest {

    // Nama barang wajib isi
    @NotBlank(message = "Nama barang wajib diisi")
    private String itemName;

    // Qty minimal 1
    @Min(value = 1, message = "Qty minimal 1")
    private Integer qty;

    // Harga minimal 1
    @Min(value = 1, message = "Harga minimal 1")
    private BigDecimal price;

    // Getter Setter
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}