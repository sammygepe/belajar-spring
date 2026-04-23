package com.sammy.belajar_spring;

// Import anotasi JPA untuk koneksi ke database
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Import anotasi validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// Menandakan class ini adalah tabel database
@Entity

// Nama tabel di MySQL = users
@Table(name = "users")
public class User {

    // Primary key
    @Id

    // Auto increment di MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nama tidak boleh kosong atau spasi saja
    @NotBlank(message = "Nama wajib diisi")
    private String nama;

    // Umur minimal 1
    @Min(value = 1, message = "Umur minimal 1")
    private int umur;

    // Constructor kosong (WAJIB untuk JPA)
    public User() {
    }

    // Constructor untuk create object baru tanpa id
    public User(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    // Getter ambil id
    public Long getId() {
        return id;
    }

    // Setter isi id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter ambil nama
    public String getNama() {
        return nama;
    }

    // Setter isi nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter ambil umur
    public int getUmur() {
        return umur;
    }

    // Setter isi umur
    public void setUmur(int umur) {
        this.umur = umur;
    }
}