package com.sammy.belajar_spring;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tidak boleh kosong
    @NotBlank(message = "Nama wajib diisi")
    private String nama;

    // Minimal umur = 1
    @Min(value = 1, message = "Umur minimal 1")
    private int umur;

    public User() {
    }

    public User(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

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