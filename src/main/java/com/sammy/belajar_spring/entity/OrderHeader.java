package com.sammy.belajar_spring.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Menandakan class ini adalah tabel database
@Entity

// Nama tabel di MySQL
@Table(name = "order_headers")
public class OrderHeader {

    // Primary key auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nomor invoice
    private String invoiceNo;

    // Tanggal order
    private LocalDate orderDate;

    // Total seluruh transaksi
    private BigDecimal grandTotal;

    // Banyak order milik 1 user
    @ManyToOne

    // Foreign key ke tabel users
    @JoinColumn(name = "user_id")
    private User user;

    // 1 header punya banyak detail
    @OneToMany(
            mappedBy = "orderHeader",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderDetail> details;

    // Constructor kosong wajib JPA
    public OrderHeader() {
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

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}