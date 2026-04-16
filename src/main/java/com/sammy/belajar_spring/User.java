package com.sammy.belajar_spring;

public class User {
    private String nama;
    private int umur;

    public User(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

/*
Kenapa HARUS pakai getter?
1. Standard Java (WAJIB di backend)
    Getter = cara resmi akses data
2. Jackson default pakai getter

    Normalnya:

    getNama() → "nama"
    getUmur() → "umur"

    👉 tanpa getter:

    bisa error di beberapa config
    bisa tidak terbaca

3. Encapsulation (yang kamu sudah pelajari)
    private String nama;

    👉 hanya boleh diakses lewat:

    getNama()

4. Bisa custom logic
    public String getNama() {
        return nama.toUpperCase();
    }

    👉 JSON jadi:

    "NAMA": "ELKAN"
*/
}
