package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<String, User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/age")
    public List<User> getUsersByAge(@RequestParam final int min, @RequestParam final int max) {
        return userService.getUsersByAge(min, max);
    }

    @GetMapping("{name}")
    public User getUserByName(@PathVariable final String name) {
        return userService.getUserByName(name);
    }

    @PostMapping
    public User addUser(@RequestBody final User user) {
        return userService.addUser(user);
    }

    @PutMapping
    public User changeAge(@RequestBody final User user) {
        return userService.changeAge(user);
    }

    @DeleteMapping("{name}")
    public String deleteUser(@PathVariable final String name) {
        userService.deleteUser(name);
        return "ok";
    }
}

