package com.example.demo.services.impl;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public Map<String, User> getUsers() {
        return users;
    }

    @Override
    public List<User> getUsersByAge(final int min, final int max) {
        return users.values().stream()
                .filter(user -> user.getAge() >= min && user.getAge() <= max)
                .toList();
    }

    @Override
    public User getUserByName(final String name) {
        return users.get(name);
    }

    @Override
    public User addUser(final User user) {
        users.put(user.getName(), user);
        return user;
    }

    @Override
    public User changeAge(final User user) {
        users.put(user.getName(), user);
        return users.get(user.getName());
    }

    @Override
    public void deleteUser(final String name) {
        users.remove(name);
    }
}
