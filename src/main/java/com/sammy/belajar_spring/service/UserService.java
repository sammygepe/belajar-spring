package com.sammy.belajar_spring.service;

import com.sammy.belajar_spring.dto.UserRequest;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.exception.UserNotFoundException;
import com.sammy.belajar_spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Ambil semua user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Cari user by id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User id " + id + " tidak ditemukan"));
    }

    // Tambah user baru
    public User createUser(UserRequest request) {

        User user = new User();

        user.setNama(request.getNama());
        user.setUmur(request.getUmur());

        return userRepository.save(user);
    }

    // Update user
    public User updateUser(Long id, UserRequest request) {

        User user = getUserById(id);

        user.setNama(request.getNama());
        user.setUmur(request.getUmur());

        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }
}