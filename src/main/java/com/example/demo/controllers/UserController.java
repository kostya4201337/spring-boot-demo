package com.example.demo.controllers;

import com.example.demo.mappers.UserEntityMapper;
import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final String USER_ADDED_MSG = "User has been added";

    private static final String USER_UPDATED_MSG = "User has been updated";

    private static final String USER_DELETED_MSG  = "User has been deleted";

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/age")
    public List<User> getUsersByAgeRange(@RequestParam final int min, @RequestParam final int max) {
        return userService.getUsersByAgeRange(min, max);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable final long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public String addUser(@RequestBody final UserCreation user) {
        userService.addUser(user);
        return USER_ADDED_MSG;
    }

    @PutMapping
    public String updateUser(@RequestBody final UserUpdate userUpdate) {
        userService.updateUser(userUpdate);
        return USER_UPDATED_MSG;
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable final long id) {
        userService.deleteUser(id);
        return USER_DELETED_MSG;
    }
}

