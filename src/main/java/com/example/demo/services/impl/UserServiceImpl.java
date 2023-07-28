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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserEntityMapper userEntityMapper;

    public UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper, final UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public List<User> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::map)
                .toList();
    }

    @Override
    public List<User> getUsersByAgeRange(final int min, final int max) {
        return userRepository.findByAgeBetween(min, max);
    }

    @Override
    public User getUserById(final long id) {
        return userMapper.map(userRepository.getById(id));
    }

    @Override
    public void addUser(final UserCreation user) {
        userRepository.save(userEntityMapper.map(user));
    }

    @Override
    public void updateUser(final UserUpdate userUpdate) {
        final LocalDateTime creationDate = userRepository.getById(userUpdate.getId()).getCreatedAt();
        final UserEntity updatedUserEntity = userEntityMapper.map(userUpdate);
        updatedUserEntity.setCreatedAt(creationDate);
        userRepository.save(updatedUserEntity);
    }

    @Override
    public void deleteUser(final long id) {
        userRepository.deleteById(id);
    }
}
