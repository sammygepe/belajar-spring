package com.sammy.belajar_spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nama user
    private String nama;

    // Umur user
    private int umur;

    // Constructor kosong wajib JPA
    public User() {
    }

    // Constructor manual
    public User(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    // Getter Setter
    public Long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}