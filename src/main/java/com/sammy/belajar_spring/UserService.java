package com.sammy.belajar_spring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User("Elkan", 25));
        users.add(new User("Baggot", 30));
        users.add(new User("Andi", 45));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }
}