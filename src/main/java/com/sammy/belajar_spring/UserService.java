package com.sammy.belajar_spring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1L, "Elkan", 25));
        users.add(new User(2L, "Baggot", 30));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User newUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setNama(newUser.getNama());
                user.setUmur(newUser.getUmur());
                return user;
            }
        }
        return null;
    }

    public String deleteUser(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                return "User deleted";
            }
        }
        return "User not found";
    }
}