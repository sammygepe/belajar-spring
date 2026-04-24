package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.ApiResponse;
import com.sammy.belajar_spring.dto.UserRequest;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =====================================
    // GET ALL USERS
    // =====================================
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {

        List<User> users = userService.findAllUsers();

        return ResponseEntity.ok(
                new ApiResponse<>("Success get users", users)
        );
    }

    // =====================================
    // GET USER BY ID
    // =====================================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {

        User user = userService.findUserById(id);

        return ResponseEntity.ok(
                new ApiResponse<>("Success get user", user)
        );
    }

    // =====================================
    // CREATE USER
    // =====================================
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(
            @Valid @RequestBody UserRequest request) {

        User savedUser = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("User created", savedUser));
    }

    // =====================================
    // UPDATE USER
    // =====================================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        User updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>("User updated", updatedUser)
        );
    }

    // =====================================
    // DELETE USER
    // =====================================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>("User deleted", null)
        );
    }
}