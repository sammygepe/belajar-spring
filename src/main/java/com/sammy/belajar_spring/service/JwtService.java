package com.sammy.belajar_spring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 🔐 Service untuk handle JWT:
 * - generate token
 * - validasi token
 * - extract data dari token
 */
@Service
public class JwtService {

    // Secret key untuk signing token (minimal 256-bit)
    private final String SECRET =
            "sammybelajarspringjwtsecretkeysammy123456";

    // Expired time
    private final long ACCESS_EXP = 1000 * 60 * 15;      // 15 menit
    private final long REFRESH_EXP = 1000L * 60 * 60 * 24 * 7; // 7 hari

    // Generate key
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ==========================================
    // GENERATE ACCESS TOKEN
    // ==========================================
    public String generateAccessToken(String username, String role) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // simpan role di token

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_EXP))
                .signWith(getKey())
                .compact();
    }

    // ==========================================
    // GENERATE REFRESH TOKEN
    // ==========================================
    public String generateRefreshToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_EXP))
                .signWith(getKey())
                .compact();
    }

    // ==========================================
    // EXTRACT ALL CLAIMS
    // ==========================================
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // ==========================================
    // EXTRACT USERNAME
    // ==========================================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ==========================================
    // EXTRACT ROLE
    // ==========================================
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ==========================================
    // VALIDATE TOKEN
    // ==========================================
    public boolean isValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}