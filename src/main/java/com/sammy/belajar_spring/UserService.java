package com.sammy.belajar_spring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1, "Elkan", 25));
        users.add(new User(2, "Baggot", 30));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User addUser(User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    public User updateUser(int id, User newUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setNama(newUser.getNama());
                user.setUmur(newUser.getUmur());
                return user;
            }
        }
        return null;
    }

    public String deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return "User deleted";
            }
        }
        return "User not found";
    }
}