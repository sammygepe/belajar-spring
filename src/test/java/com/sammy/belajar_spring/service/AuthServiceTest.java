package com.sammy.belajar_spring.service;

import com.sammy.belajar_spring.dto.LoginRequest;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * 🧪 Unit Test untuk AuthService
 */
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    /**
     * Mock repository
     * -> database palsu
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Mock JWT service
     */
    @Mock
    private JwtService jwtService;

    /**
     * Mock password encode
     */
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Object asli yang mau dites
     */
    @InjectMocks
    private AuthService authService;

    // ==========================================
    // TEST LOGIN SUCCESS
    // ==========================================
    @Test
    void login_success() {

        // ======================================
        // PREPARE DATA
        // ======================================

        // Request login
        LoginRequest request = new LoginRequest();
        request.setUsername("sammy");
        request.setPassword("123");

        // User dummy
        User user = new User();
        user.setUsername("sammy");

        // Simulasi password database
        user.setPassword("hashed-password");

        user.setRole("USER");

        // ======================================
        // MOCK BEHAVIOR
        // ======================================

        // Saat repository dipanggil
        // return user dummy
        when(userRepository.findByUsername("sammy"))
                .thenReturn(Optional.of(user));

        // Mock password check
        when(passwordEncoder.matches(
                anyString(),
                anyString()
        )).thenReturn(true);

        // Mock JWT token
        when(jwtService.generateAccessToken(
                anyString(),
                anyString()
        )).thenReturn("mock-access-token");

        when(jwtService.generateRefreshToken(
                anyString()
        )).thenReturn("mock-refresh-token");

        // ======================================
        // EXECUTE
        // ======================================

        var result =
                authService.login(request);

        // ======================================
        // ASSERTION
        // ======================================

        assertNotNull(result);

        assertEquals(
                "mock-access-token",
                result.get("accessToken")
        );

        assertEquals(
                "mock-refresh-token",
                result.get("refreshToken")
        );

    }
}