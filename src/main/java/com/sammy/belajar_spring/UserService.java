package com.sammy.belajar_spring;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET ALL
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET BY ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User dengan id " + id + " tidak ditemukan"));
    }

    // CREATE
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // UPDATE
    public User updateUser(Long id, User newUser) {

        User user = getUserById(id);

        user.setNama(newUser.getNama());
        user.setUmur(newUser.getUmur());

        return userRepository.save(user);
    }

    // DELETE
    public String deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);

        return "User berhasil dihapus";
    }
}