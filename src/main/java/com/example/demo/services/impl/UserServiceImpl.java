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

    private static final String noUsersFoundWarn = "no users found in DB";

    private static final String ageValidError = "age validation error";

    private static final String userDeleteInfo = "user has been successfully deleted";

    private static final String userDeleteError = "user with the given ID does not exist and can't be deleted";

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
        List<User> users = userRepository
                .findAll()
                .stream()
                .map(userMapper::map)
                .toList();
        if(users.isEmpty()){
            log.warn(noUsersFoundWarn);
            throw new RuntimeException(noUsersFoundWarn);
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
    public boolean addUser(final UserCreation user) {
        if (user.getAge() < 0) {
            log.error(ageValidError);
            throw new RuntimeException(ageValidError);
        }
        userRepository.save(userEntityMapper.map(user));
        return true;
    }

    @Override
    public boolean updateUser(final UserUpdate userUpdate) {
        if (userUpdate.getAge() < 0) {
            log.error(ageValidError);
            throw new RuntimeException(ageValidError);
        }

        final LocalDateTime creationDate = userRepository.getById(userUpdate.getId()).getCreatedAt();
        final UserEntity updatedUserEntity = userEntityMapper.map(userUpdate);
        updatedUserEntity.setCreatedAt(creationDate);
        userRepository.save(updatedUserEntity);
        return true;

    }

    @Override
    public boolean deleteUser(final long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info(userDeleteInfo);
            return true;
        }
        log.error(userDeleteError);
        throw new RuntimeException(userDeleteError);
    }
}
