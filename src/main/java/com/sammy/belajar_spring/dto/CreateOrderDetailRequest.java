package com.sammy.belajar_spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * DTO untuk item dalam order
 */
public class CreateOrderDetailRequest {

    // Nama barang wajib diisi
    @NotBlank(message = "Nama item tidak boleh kosong")
    private String itemName;

    // Qty minimal 1
    @Min(value = 1, message = "Qty minimal 1")
    private int qty;

    // Harga tidak boleh null
    @NotNull(message = "Harga tidak boleh kosong")
    private BigDecimal price;

    public @NotBlank(message = "Nama item tidak boleh kosong") String getItemName() {
        return itemName;
    }

    public void setItemName(@NotBlank(message = "Nama item tidak boleh kosong") String itemName) {
        this.itemName = itemName;
    }

    public @Min(value = 1, message = "Qty minimal 1") int getQty() {
        return qty;
    }

    public void setQty(@Min(value = 1, message = "Qty minimal 1") int qty) {
        this.qty = qty;
    }

    public @NotNull(message = "Harga tidak boleh kosong") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Harga tidak boleh kosong") BigDecimal price) {
        this.price = price;
    }
}