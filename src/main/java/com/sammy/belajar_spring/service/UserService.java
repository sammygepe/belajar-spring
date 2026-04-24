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

    // Dependency Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // =====================================
    // GET ALL USERS
    // =====================================
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // =====================================
    // GET USER BY ID
    // =====================================
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User dengan id " + id + " tidak ditemukan"
                        )
                );
    }

    // =====================================
    // CREATE USER
    // =====================================
    public User createUser(UserRequest request) {

        User user = new User();

        user.setNama(request.getNama());
        user.setUmur(request.getUmur());

        return userRepository.save(user);
    }

    // =====================================
    // UPDATE USER
    // =====================================
    public User updateUser(Long id, UserRequest request) {

        User user = findUserById(id);

        user.setNama(request.getNama());
        user.setUmur(request.getUmur());

        return userRepository.save(user);
    }

    // =====================================
    // DELETE USER
    // =====================================
    public void deleteUser(Long id) {

        User user = findUserById(id);

        userRepository.delete(user);
    }
}