package com.sammy.belajar_spring.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 🔐 Security Configuration (JWT + RBAC)
 *
 * Mengatur:
 * - Authentication (JWT)
 * - Authorization (ROLE USER / ADMIN)
 * - Stateless session
 * - Endpoint protection
 */
@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    // Constructor Injection untuk JWT Filter
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // ==========================================
    // 🔑 PASSWORD ENCODER
    // ==========================================
    // Digunakan untuk hashing password (BCrypt)
    // Dipakai di AuthService saat register & login
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ==========================================
    // 🔐 MAIN SECURITY CONFIG
    // ==========================================
    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http

                // ==========================================
                // ❌ Disable CSRF (karena kita pakai REST API)
                // ==========================================
                .csrf(csrf -> csrf.disable())

                // ==========================================
                // ❗ Stateless session (tidak pakai session)
                // Semua auth pakai JWT
                // ==========================================
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // ==========================================
                // 🔥 AUTHORIZATION RULES (RBAC)
                // ==========================================
                .authorizeHttpRequests(auth -> auth

                        // ✅ Endpoint bebas akses (login & register)
                        .requestMatchers("/auth/**").permitAll()

                        // ✅ Swagger UI bebas akses
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // ✅ USER & ADMIN boleh akses orders
                        .requestMatchers("/orders/**")
                        .hasAnyRole("USER", "ADMIN")

                        // 🔒 Hanya ADMIN boleh akses
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")

                        // 🔐 Selain itu wajib login
                        .anyRequest().authenticated()
                )

                // ==========================================
                // 🔥 JWT FILTER
                // ==========================================
                // Dijalankan sebelum filter bawaan Spring
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                // ==========================================
                // ❌ HANDLE UNAUTHORIZED (401)
                // ==========================================
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {

                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");

                            response.getWriter().write("""
                        {
                            "message": "Unauthorized",
                            "data": null
                        }
                    """);
                        })
                );

        return http.build();
    }
}