package com.sammy.belajar_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

// Menandakan class ini tabel database
@Entity

// Nama tabel di MySQL
@Table(name = "users")
public class User {

    // Primary key auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nama wajib diisi
    @NotBlank(message = "Nama wajib diisi")
    private String nama;

    // Umur minimal 1
    @Min(value = 1, message = "Umur minimal 1")
    private int umur;

    // 1 user punya banyak order
    // JsonIgnore agar tidak infinite loop saat convert ke JSON
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<OrderHeader> orders;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Constructor kosong wajib JPA
    public User() {
    }

    // Constructor manual
    public User(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public List<OrderHeader> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderHeader> orders) {
        this.orders = orders;
    }
}