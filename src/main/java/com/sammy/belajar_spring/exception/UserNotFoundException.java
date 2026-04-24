package com.sammy.belajar_spring.exception;

// Error custom kalau user tidak ditemukan
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}