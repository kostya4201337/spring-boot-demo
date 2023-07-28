package com.example.demo.services.impl;

import com.example.demo.mappers.UserEntityMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final UserEntityMapper userEntityMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userEntityMapper = userEntityMapper;
    }


    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<User> users = userMapper.map(userEntities);
        return users;
    }

    @Override
    public List<User> getUsersByAgeRange(final int min, final int max) {
        List<User> users = userRepository.findByAgeBetween(min, max);
        return users;
    }

    @Override
    public User getUserById(final long id) {
        return userMapper.map(userRepository.getById(id));
    }

    @Override
    public String addUser(UserCreation user) {
        userRepository.save(userEntityMapper.map(user));
        return "User has been added";
    }

    @Transactional
    public String updateUser(final UserUpdate userUpdate) {
        Date creationDate = userRepository.getById(userUpdate.getId()).getCreatedAt();
        UserEntity updatedUserEntity = userEntityMapper.map(userUpdate);
        updatedUserEntity.setCreatedAt(creationDate);
        userRepository.save(updatedUserEntity);
        return "User has been updated";
    }

    @Override
    public String deleteUser(final long id) {
        userRepository.delete(userRepository.getById(id));
        return "User has been deleted";
    }
}
