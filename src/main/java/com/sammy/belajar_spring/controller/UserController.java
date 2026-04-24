package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.ApiResponse;
import com.sammy.belajar_spring.dto.UserRequest;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ==================================================
    // GET ALL USERS + PAGINATION + SORTING
    //
    // Contoh:
    // /users?page=0&size=5
    // /users?page=0&size=5&sortBy=nama&direction=asc
    // ==================================================
    @GetMapping
    public ResponseEntity<ApiResponse<Page<User>>> getAllUsers(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction

    ) {

        Page<User> users =
                userService.findAllUsers(page, size, sortBy, direction);

        return ResponseEntity.ok(
                new ApiResponse<>("Success get users", users)
        );
    }

    // ==================================================
    // SEARCH USER BY NAMA
    //
    // Contoh:
    // /users/search?keyword=sam&page=0&size=5
    // ==================================================
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<User>>> searchUsers(

            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size

    ) {

        Page<User> users =
                userService.searchUsers(keyword, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>("Success search users", users)
        );
    }

    // ==================================================
    // GET USER BY ID
    // ==================================================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(
            @PathVariable Long id
    ) {

        User user = userService.findUserById(id);

        return ResponseEntity.ok(
                new ApiResponse<>("Success get user", user)
        );
    }

    // ==================================================
    // CREATE USER
    // ==================================================
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(

            @Valid @RequestBody UserRequest request

    ) {

        User savedUser = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>("User created", savedUser)
                );
    }

    // ==================================================
    // UPDATE USER
    // ==================================================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(

            @PathVariable Long id,
            @Valid @RequestBody UserRequest request

    ) {

        User updatedUser =
                userService.updateUser(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>("User updated", updatedUser)
        );
    }

    // ==================================================
    // DELETE USER
    // ==================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(
            @PathVariable Long id
    ) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>("User deleted", null)
        );
    }
}