package com.example.demo.services.impl;

import com.example.demo.mappers.UserEntityMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String NO_USERS_FOUND_WARN = "no users found in DB";

    private static final String AGE_VALID_ERROR = "age validation error";

    private static final String USER_DELETE_INFO = "user has been successfully deleted";

    private static final String USER_DELETE_ERROR = "user with the given ID does not exist and can't be deleted";

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserEntityMapper userEntityMapper;

    private void ageValidation (int age, long id) {
        if (age < 0) {
            log.error(AGE_VALID_ERROR + ", user id: " + id);
            throw new RuntimeException(AGE_VALID_ERROR + ", user id: " + id);
        }
    }

    private void ageValidation (int age) {
        if (age < 0) {
            log.error(AGE_VALID_ERROR);
            throw new RuntimeException(AGE_VALID_ERROR);
        }
    }

    public UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper, final UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public List<User> getUsers() {
        final List<User> users = userRepository
                .findAll()
                .stream()
                .map(userMapper::map)
                .toList();
        if (users.isEmpty()) {
            log.warn(NO_USERS_FOUND_WARN);
            throw new RuntimeException(NO_USERS_FOUND_WARN);
        }
        return users;
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
        ageValidation(user.getAge());
        userRepository.save(userEntityMapper.map(user));
    }

    @Override
    public void updateUser(final UserUpdate userUpdate) {
        ageValidation(userUpdate.getAge(), userUpdate.getId());

        final UserEntity updatedUserEntity = userRepository.getById(userUpdate.getId());
        updatedUserEntity.setName(userUpdate.getName());
        updatedUserEntity.setAge(userUpdate.getAge());
        updatedUserEntity.setRole(userUpdate.getRole());
        updatedUserEntity.setPassword(userUpdate.getPassword());
        userRepository.save(updatedUserEntity);
    }

    @Override
    public void deleteUser(final long id) {
        if (!userRepository.existsById(id)) {
            log.error(USER_DELETE_ERROR);
            throw new RuntimeException(USER_DELETE_ERROR);
        }
        userRepository.deleteById(id);
        log.info(USER_DELETE_INFO  + ", user id: " + id);
    }
}
