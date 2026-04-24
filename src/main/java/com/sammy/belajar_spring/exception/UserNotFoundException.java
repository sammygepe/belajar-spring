package com.sammy.belajar_spring.exception;

// ==========================================
// Custom Exception
// Dipakai saat data user tidak ditemukan
//
// Contoh:
// GET /users/99
// Jika id 99 tidak ada di database,
// maka throw UserNotFoundException
// ==========================================
public class UserNotFoundException extends RuntimeException {

    // Constructor menerima pesan error
    public UserNotFoundException(String message) {
        super(message);
    }
}