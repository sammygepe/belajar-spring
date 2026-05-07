package com.sammy.belajar_spring.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 🚫 Service untuk menyimpan token blacklist
 *
 * Token yang masuk blacklist:
 * - dianggap logout
 * - tidak boleh dipakai lagi
 */
@Service
public class TokenBlacklistService {

    /**
     * Simpan token blacklist di memory
     *
     * NOTE:
     * untuk production biasanya pakai Redis/database
     */
    private final Set<String> blacklist =
            new HashSet<>();

    // ==========================================
    // TAMBAH TOKEN KE BLACKLIST
    // ==========================================
    public void blacklistToken(String token) {
        blacklist.add(token);
    }

    // ==========================================
    // CEK TOKEN BLACKLIST
    // ==========================================
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}