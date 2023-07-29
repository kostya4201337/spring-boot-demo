package com.example.demo.services;

import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.model.entities.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUsers();

    List<User> getUsersByAgeRange(int min, int max);

    User getUserById(final long id);

    void addUser(UserCreation user);

    void updateUser(UserUpdate userUpdate);

    void deleteUser(final long id);
}
