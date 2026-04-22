package com.sammy.belajar_spring;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET ALL
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET BY ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // CREATE
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // UPDATE
    public User updateUser(Long id, User newUser) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setNama(newUser.getNama());
            user.setUmur(newUser.getUmur());
            return userRepository.save(user);
        }

        return null;
    }

    // DELETE
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            userRepository.delete(user);
            return "User deleted";
        }

        return "User not found";
    }
}