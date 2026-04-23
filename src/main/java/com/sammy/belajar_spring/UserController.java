package com.sammy.belajar_spring;

// Untuk menjalankan validation dari User.java
import jakarta.validation.Valid;

// Import anotasi REST API
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Menandakan class ini controller REST
@RestController

// Semua endpoint diawali /users
@RequestMapping("/users")
public class UserController {

    // Memanggil logic dari service layer
    private final UserService userService;

    // Constructor Injection (best practice Spring)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ========================================
    // GET ALL USERS
    // Endpoint: GET /users
    // ========================================
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ========================================
    // GET USER BY ID
    // Endpoint: GET /users/1
    // ========================================
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {

        // @PathVariable mengambil angka dari URL
        return userService.getUserById(id);
    }

    // ========================================
    // CREATE USER
    // Endpoint: POST /users
    // ========================================
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {

        // @RequestBody ambil JSON dari request
        // @Valid jalankan validation
        return userService.addUser(user);
    }

    // ========================================
    // UPDATE USER
    // Endpoint: PUT /users/1
    // ========================================
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @Valid @RequestBody User user) {

        return userService.updateUser(id, user);
    }

    // ========================================
    // DELETE USER
    // Endpoint: DELETE /users/1
    // ========================================
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        return userService.deleteUser(id);
    }
}