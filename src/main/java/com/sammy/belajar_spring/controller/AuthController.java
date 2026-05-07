package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.ApiResponse;
import com.sammy.belajar_spring.dto.LoginRequest;
import com.sammy.belajar_spring.dto.RegisterRequest;
import com.sammy.belajar_spring.entity.User;

import com.sammy.belajar_spring.service.AuthService;
import com.sammy.belajar_spring.service.JwtService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 🔐 Authentication Controller
 *
 * Handle:
 * - register
 * - login
 * - refresh token
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    // Service auth
    private final AuthService authService;

    // Service JWT
    private final JwtService jwtService;

    // Constructor Injection
    public AuthController(
            AuthService authService,
            JwtService jwtService
    ) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    // ==========================================
    // REGISTER
    // ==========================================
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(

            // Ambil JSON request + validasi
            @Valid @RequestBody RegisterRequest request
    ) {

        // Simpan user baru
        User user = authService.register(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Register success",
                        user
                )
        );
    }

    // ==========================================
    // LOGIN
    // ==========================================
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(

            // Ambil request login
            @Valid @RequestBody LoginRequest request
    ) {

        /**
         * Return:
         * - access token
         * - refresh token
         */
        Map<String, String> tokens =
                authService.login(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Login success",
                        tokens
                )
        );
    }

    // ==========================================
    // REFRESH TOKEN
    // ==========================================
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refreshToken(

            // Refresh token dari query param
            @RequestParam String refreshToken
    ) {

        // Validasi token
        if (!jwtService.isValid(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        // Ambil username dari token
        String username =
                jwtService.extractUsername(refreshToken);

        /**
         * TODO:
         * ambil role dari database
         *
         * sementara hardcode dulu
         */
        String role = "USER";

        // Generate access token baru
        String newAccessToken =
                jwtService.generateAccessToken(
                        username,
                        role
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Success refresh token",
                        newAccessToken
                )
        );
    }
}