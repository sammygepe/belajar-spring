package com.sammy.belajar_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HelloController {

/*
    1. lihat URL /hello
    2. cari @GetMapping("/hello")
    3. jalankan method hello()
    4. kirim hasil ke browser
*/

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @GetMapping("/user")
    public User getUser() {
        return new User("Elkan", 25);
    }

    @GetMapping("/")
    public String home() {
        return "Home Page";
    }

/*
    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        users.add(new User("Elkan", 25));
        users.add(new User("Baggot", 30));
        users.add(new User("Andi", 40));

        return users;
    }
*/

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}