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
    @RequestMapping("/getUsers")
    public Map<String, User> getUsers() {
        return users;
    }

    @RequestMapping("/getUsersByAge/{min}/{max}")
    public List<User> getByAge(@PathVariable int min, @PathVariable int max) {
        List<User> sorted = new ArrayList<>(users.values());
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).getAge() < min || sorted.get(i).getAge() > max) {
                sorted.remove(i);
                i--;
            }
        }
        return sorted;
    }

    @RequestMapping("/getUser/{name}")
    public User getUser(@PathVariable String name) {
        return users.get(name);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        users.put(user.getName(), user);
        return user;
    }
    // changes
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

