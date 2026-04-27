package com.sammy.belajar_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

// Menandakan class ini tabel database
@Entity

// Nama tabel di MySQL
@Table(name = "order_details")
public class OrderDetail {

    // Primary key auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nama barang
    private String itemName;

    // Jumlah beli
    private Integer qty;

    // Harga satuan
    private BigDecimal price;

    // qty x price
    private BigDecimal subtotal;

    // Banyak detail milik 1 header
    @ManyToOne
    @JoinColumn(name = "order_header_id")

    // Supaya tidak loop JSON
    @JsonIgnore
    private OrderHeader orderHeader;

    // Constructor kosong wajib JPA
    public OrderDetail() {
    }

    // =========================
    // Getter Setter
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }
}