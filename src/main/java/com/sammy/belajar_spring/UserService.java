package com.sammy.belajar_spring;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        users.add(new User("Elkan", 25));
        users.add(new User("Baggot", 30));
        users.add(new User("Andi", 45));

        return users;
    }
}