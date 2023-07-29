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

    boolean addUser(UserCreation user);

    boolean updateUser(UserUpdate userUpdate);

    boolean deleteUser(final long id);
}
