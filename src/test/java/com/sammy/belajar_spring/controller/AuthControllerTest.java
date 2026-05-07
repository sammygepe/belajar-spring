package com.sammy.belajar_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sammy.belajar_spring.dto.LoginRequest;
import com.sammy.belajar_spring.service.AuthService;
import com.sammy.belajar_spring.service.JwtService;
import com.sammy.belajar_spring.service.TokenBlacklistService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 🔥 disable security filter untuk controller test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

/**
 * 🧪 Controller Test untuk AuthController
 *
 * Fokus test:
 * - request masuk benar
 * - response JSON benar
 * - status HTTP benar
 *
 * BUKAN test business logic.
 */
@WebMvcTest(AuthController.class)

// 🔥 disable spring security saat test
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    /**
     * MockMvc
     *
     * simulasi HTTP request
     * tanpa menjalankan server asli
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Convert object Java ↔ JSON
     */
    @Autowired
    private ObjectMapper objectMapper;

    // ==========================================
    // MOCK DEPENDENCIES
    // ==========================================

    /**
     * Mock AuthService
     *
     * karena controller bergantung ke service
     */
    @MockBean
    private AuthService authService;

    /**
     * Mock JWT service
     */
    @MockBean
    private JwtService jwtService;

    /**
     * Mock blacklist service
     */
    @MockBean
    private TokenBlacklistService tokenBlacklistService;

    // ==========================================
    // TEST LOGIN SUCCESS
    // ==========================================
    @Test
    void login_success() throws Exception {

        // ======================================
        // PREPARE REQUEST
        // ======================================

        LoginRequest request =
                new LoginRequest();

        request.setUsername("sammy");
        request.setPassword("123");

        // ======================================
        // MOCK RESPONSE SERVICE
        // ======================================

        Map<String, String> tokens =
                new HashMap<>();

        tokens.put(
                "accessToken",
                "mock-access-token"
        );

        tokens.put(
                "refreshToken",
                "mock-refresh-token"
        );

        /**
         * Saat authService.login dipanggil
         * return token dummy
         */
        when(authService.login(any()))
                .thenReturn(tokens);

        // ======================================
        // EXECUTE REQUEST
        // ======================================

        mockMvc.perform(

                        post("/auth/login")

                                // content type JSON
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )

                                // body request JSON
                                .content(
                                        objectMapper.writeValueAsString(
                                                request
                                        )
                                )
                )

                // ==================================
                // ASSERT RESPONSE
                // ==================================

                // status HTTP harus 200
                .andExpect(status().isOk())

                // cek message JSON
                .andExpect(
                        jsonPath("$.message")
                                .value("Login success")
                )

                // cek access token
                .andExpect(
                        jsonPath("$.data.accessToken")
                                .value("mock-access-token")
                )

                // cek refresh token
                .andExpect(
                        jsonPath("$.data.refreshToken")
                                .value("mock-refresh-token")
                );
    }
}