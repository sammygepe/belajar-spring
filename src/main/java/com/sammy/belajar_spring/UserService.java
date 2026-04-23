package com.sammy.belajar_spring;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Menandakan class ini service layer
@Service
public class UserService {

    // Ambil akses database dari repository
    private final UserRepository userRepository;

    // Constructor Injection (best practice)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // =====================================
    // AMBIL SEMUA USER
    // =====================================
    public List<User> getAllUsers() {

        // SELECT * FROM users
        return userRepository.findAll();
    }

    // =====================================
    // AMBIL USER BERDASARKAN ID
    // =====================================
    public User getUserById(Long id) {

        // Optional = bisa ada / tidak ada
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
    }

    // =====================================
    // TAMBAH USER BARU
    // =====================================
    public User addUser(User user) {

        // INSERT INTO users ...
        return userRepository.save(user);
    }

    // =====================================
    // UPDATE USER
    // =====================================
    public User updateUser(Long id, User newUser) {

        // Cari user lama dulu
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        // Update field
        user.setNama(newUser.getNama());
        user.setUmur(newUser.getUmur());

        // Save kembali
        return userRepository.save(user);
    }

    // =====================================
    // DELETE USER
    // =====================================
    public String deleteUser(Long id) {

        // Cek apakah ada
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        // Hapus
        userRepository.delete(user);

        return "User berhasil dihapus";
    }
}