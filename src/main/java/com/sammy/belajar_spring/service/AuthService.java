package com.sammy.belajar_spring.service;

import com.sammy.belajar_spring.dto.RegisterRequest;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sammy.belajar_spring.dto.LoginRequest;

import java.util.Map;
import java.util.HashMap;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already used");
        }

        User user = new User();
        user.setNama(request.getNama());
        user.setUmur(request.getUmur());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    /**
     * 🔐 LOGIN
     * return access + refresh token
     */
    public Map<String, String> login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Username tidak ditemukan")
                );

        // cek password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Password salah");
        }

        // generate token
        String accessToken =
                jwtService.generateAccessToken(
                        user.getUsername(),
                        user.getRole()
                );

        String refreshToken =
                jwtService.generateRefreshToken(
                        user.getUsername()
                );

        // return ke client
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }
}