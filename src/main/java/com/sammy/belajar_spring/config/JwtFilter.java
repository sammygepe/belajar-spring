package com.sammy.belajar_spring.config;

import com.sammy.belajar_spring.service.JwtService;
import com.sammy.belajar_spring.service.TokenBlacklistService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    public JwtFilter(
            JwtService jwtService,
            TokenBlacklistService tokenBlacklistService
    ) {
        this.jwtService = jwtService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // ambil header Authorization
        String authHeader = request.getHeader("Authorization");

        // cek apakah ada Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            // validasi token
            if (
                    jwtService.isValid(token)
                            && !tokenBlacklistService.isBlacklisted(token)
            ) {
                // ambil username & role dari token
                String username = jwtService.extractUsername(token);
                String role = jwtService.extractRole(token);

                // convert role → authority (WAJIB pakai ROLE_)
                var authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + role)
                );

                // inject ke Spring Security
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                authorities
                        );

                auth.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // set ke context (ini yg bikin user "login")
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);
            }
        }

        // lanjut ke filter berikutnya
        filterChain.doFilter(request, response);
    }
}