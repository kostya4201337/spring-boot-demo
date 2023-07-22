package com.example.demo.services;

import com.example.demo.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, User> getUsers();

    List<User> getUsersByAge(int min, int max);

    User getUserByName(String name);

    User addUser(User user);

    User changeAge(User user);

    void deleteUser(String name);
}
