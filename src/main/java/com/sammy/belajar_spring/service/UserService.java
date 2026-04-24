package com.sammy.belajar_spring.service;

import com.sammy.belajar_spring.dto.UserRequest;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.exception.UserNotFoundException;
import com.sammy.belajar_spring.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ==========================================
    // GET ALL USERS + PAGINATION + SORTING
    // ==========================================
    public Page<User> findAllUsers(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        // ASC / DESC
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // page dimulai dari 0
        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.findAll(pageable);
    }

    // ==========================================
    // SEARCH USER BY NAMA + PAGINATION
    // ==========================================
    public Page<User> searchUsers(
            String keyword,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findByNamaContainingIgnoreCase(keyword, pageable);
    }

    // ==========================================
    // GET USER BY ID
    // ==========================================
    public User findUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User dengan id " + id + " tidak ditemukan"
                        ));
    }

    // ==========================================
    // CREATE USER
    // ==========================================
    public User createUser(UserRequest request) {

        User user = new User();
        user.setNama(request.getNama());
        user.setUmur(request.getUmur());

        return userRepository.save(user);
    }

    // ==========================================
    // UPDATE USER
    // ==========================================
    public User updateUser(Long id, UserRequest request) {

        User user = findUserById(id);

        user.setNama(request.getNama());
        user.setUmur(request.getUmur());

        return userRepository.save(user);
    }

    // ==========================================
    // DELETE USER
    // ==========================================
    public void deleteUser(Long id) {

        User user = findUserById(id);

        userRepository.delete(user);
    }
}