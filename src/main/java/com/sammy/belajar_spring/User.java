package com.sammy.belajar_spring;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private int umur;

    public User() {
    }

    public User(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public User(Long id, String nama, int umur) {
        this.id = id;
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