package com.sammy.belajar_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Menandakan ini class konfigurasi Spring
@Configuration
public class SecurityConfig {

    // ==========================================
    // BCrypt untuk hash password saat register
    // dan compare password saat login
    // ==========================================
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ==========================================
    // Konfigurasi security endpoint
    // ==========================================
    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
                // Disable csrf karena project kita REST API
                .csrf(csrf -> csrf.disable())

                // Atur hak akses endpoint
                .authorizeHttpRequests(auth -> auth

                        // Endpoint auth bebas akses
                        .requestMatchers("/auth/**").permitAll()

                        // Swagger bebas akses
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Endpoint lain wajib login
                        .anyRequest().authenticated()
                )

                // Sementara pakai basic auth bawaan Spring
                // Nanti akan diganti JWT Filter
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}