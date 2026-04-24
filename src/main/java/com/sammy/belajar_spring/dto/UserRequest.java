package com.sammy.belajar_spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    // Nama wajib isi
    @NotBlank(message = "Nama wajib diisi")
    private String nama;

    // Umur minimal 1
    @Min(value = 1, message = "Umur minimal 1")
    private int umur;

    // Getter Setter
    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}