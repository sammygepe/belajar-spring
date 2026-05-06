package com.sammy.belajar_spring.exception;

import com.sammy.belajar_spring.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 🌍 Global Exception Handler (Production Ready)
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ==========================================
    // ❌ VALIDATION ERROR
    // ==========================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> validation(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>("Validation failed", errors));
    }

    // ==========================================
    // ❌ USER NOT FOUND
    // ==========================================
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> notFound(
            UserNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    // ==========================================
    // ❌ BUSINESS ERROR (RuntimeException)
    // ==========================================
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> runtime(
            RuntimeException ex
    ) {

        log.warn("Business error: {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    // ==========================================
    // ❌ SYSTEM ERROR (UNKNOWN)
    // ==========================================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> generalError(
            Exception ex
    ) {

        log.error("System error", ex); // 🔥 proper logging

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>("Internal server error", null));
    }
}