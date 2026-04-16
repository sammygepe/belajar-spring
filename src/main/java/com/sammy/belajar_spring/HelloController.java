package com.sammy.belajar_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}