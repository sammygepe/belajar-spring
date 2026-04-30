package com.sammy.belajar_spring.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Konfigurasi security utama
@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // ==========================================
    // Password encoder (dipakai di AuthService)
    // ==========================================
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ==========================================
    // Security rules + JWT filter
    // ==========================================
    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
                // REST API → csrf tidak dipakai
                .csrf(csrf -> csrf.disable())

                // ❗ penting: stateless (tidak pakai session)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Atur endpoint mana yang bebas / butuh login
                .authorizeHttpRequests(auth -> auth
                        // Auth bebas akses
                        .requestMatchers("/auth/**").permitAll()

                        // Swagger bebas
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // selain itu wajib login (JWT)
                        .anyRequest().authenticated()
                )

                // ❗ inject JWT filter sebelum filter bawaan Spring
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

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