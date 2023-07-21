package com.example.demo.controllers;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, User> users = new HashMap<>();

    @GetMapping
    public Map<String, User> getUsers() {
        return users;
    }

    @GetMapping("/age")
    public List<User> getUsersByAge(@RequestParam int min, @RequestParam int max) {
        List<User> result = new ArrayList<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getAge() >= min && entry.getValue().getAge() <= max) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    @GetMapping("{name}")
    public User getUserByName(@PathVariable String name) {
        return users.get(name);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        users.put(user.getName(), user);
        return user;
    }

    @PutMapping
    public User changeAge(@RequestBody User user) {
        users.put(user.getName(), user);
        return users.get(user.getName());
    }

    @DeleteMapping("{name}")
    public String deleteUser(@PathVariable String name) {
        users.remove(name);
        return "Ok";
    }
}

