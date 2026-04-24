package com.sammy.belajar_spring.exception;

import com.sammy.belajar_spring.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// Menangani semua error global di project
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===============================
    // VALIDATION ERROR
    // Contoh:
    // nama kosong
    // umur < 1
    // ===============================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> validation(
            MethodArgumentNotValidException ex
    ) {

        // Simpan field error ke map
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),           // nama field
                                error.getDefaultMessage()  // pesan error
                        )
                );

        // Return status 400
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ApiResponse<>(
                                "Validation failed",
                                errors
                        )
                );
    }

    // ===============================
    // USER NOT FOUND
    // ===============================
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> notFound(
            UserNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiResponse<>(
                                ex.getMessage(),
                                null
                        )
                );
    }

    // ===============================
    // GENERAL ERROR (optional)
    // semua error tak terduga
    // ===============================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> generalError(
            Exception ex
    ) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ApiResponse<>(
                                "Internal server error",
                                null
                        )
                );
    }
}