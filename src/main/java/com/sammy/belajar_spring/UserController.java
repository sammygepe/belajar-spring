package com.sammy.belajar_spring;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // CREATE USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}