package com.example.demo.services;

import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    List<User> getUsersByAgeRange(int min, int max);

    User getUserById(long id);

    void addUser(UserCreation user);

    void updateUser(UserUpdate userUpdate);

    void deleteUser(long id);
}
