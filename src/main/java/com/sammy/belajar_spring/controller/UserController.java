package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.UserRequest;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.service.UserService;
import jakarta.validation.Valid;
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

    // GET /users
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    // GET /users/1
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // POST /users
    @PostMapping
    public User create(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    // PUT /users/1
    @PutMapping("/{id}")
    public User update(@PathVariable Long id,
                       @Valid @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    // DELETE /users/1
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        userService.deleteUser(id);

        return "User berhasil dihapus";
    }
}