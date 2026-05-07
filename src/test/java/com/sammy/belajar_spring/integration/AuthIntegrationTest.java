package com.sammy.belajar_spring.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sammy.belajar_spring.dto.LoginRequest;
import com.sammy.belajar_spring.entity.User;

import com.sammy.belajar_spring.repository.UserRepository;
import com.sammy.belajar_spring.repository.OrderHeaderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 🔥 Integration Test
 *
 * Test:
 * - controller asli
 * - service asli
 * - repository asli
 * - database asli
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthIntegrationTest {

    // Simulasi HTTP request
    @Autowired
    private MockMvc mockMvc;

    // Convert object ↔ JSON
    @Autowired
    private ObjectMapper objectMapper;

    // Repository asli
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    // Encoder asli
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ==========================================
    // PREPARE DATA
    // ==========================================
    @BeforeEach
    void setup() {

        // Hapus child table dulu
        orderHeaderRepository.deleteAll();

        // Baru parent table
        userRepository.deleteAll();

        // ======================================
        // CREATE USER TEST
        // ======================================

        User user = new User();

        user.setNama("Sammy");
        user.setUmur(25);
        user.setUsername("sammy");

        // password encrypted
        user.setPassword(
                passwordEncoder.encode("123")
        );

        user.setRole("USER");

        // save user
        userRepository.save(user);
    }

    // ==========================================
    // TEST LOGIN SUCCESS
    // ==========================================
    @Test
    void login_success() throws Exception {

        // ======================================
        // REQUEST LOGIN
        // ======================================

        LoginRequest request =
                new LoginRequest();

        request.setUsername("sammy");
        request.setPassword("123");

        // ======================================
        // EXECUTE REQUEST
        // ======================================

        mockMvc.perform(

                        post("/auth/login")

                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )

                                .content(
                                        objectMapper.writeValueAsString(
                                                request
                                        )
                                )
                )

                // ==================================
                // ASSERT RESPONSE
                // ==================================

                .andExpect(status().isOk())

                .andExpect(
                        jsonPath("$.message")
                                .value("Login success")
                )

                // access token exists
                .andExpect(
                        jsonPath("$.data.accessToken")
                                .exists()
                )

                // refresh token exists
                .andExpect(
                        jsonPath("$.data.refreshToken")
                                .exists()
                );
    }
}