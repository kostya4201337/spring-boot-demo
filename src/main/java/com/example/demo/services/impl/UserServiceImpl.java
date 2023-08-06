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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String NO_USERS_FOUND_WARN = "no users found in DB";

    private static final String AGE_VALID_ERROR = "user with %s id age validation error";

    private static final String USER_DELETE_INFO = "user with %s id has been deleted";

    private static final String USER_DELETE_ERROR = "user with the given ID does not exist and can't be deleted";

    private static final String USER_UPDATE_ERROR = "user with given id does not exist and can't be updated";

    private static final String GET_USER_BY_ID_ERROR = "user with given id doesn't exist";

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
        final List<User> users = userRepository
                .findAll()
                .stream()
                .map(userMapper::map)
                .toList();
        if (users.isEmpty()) {
            log.warn(NO_USERS_FOUND_WARN);
        }
        return users;
    }

    @Override
    public List<User> getUsersByAgeRange(final int min, final int max) {
        return userRepository.findByAgeBetween(min, max);
    }

    @Override
    public User getUserById(final long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            log.error(GET_USER_BY_ID_ERROR);
            throw new RuntimeException(GET_USER_BY_ID_ERROR);
        }

        return userMapper.map(userEntity.get());
    }

    @Override
    public void addUser(final UserCreation user) {
        UserEntity userEntity = userEntityMapper.map(user);
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(final UserUpdate userUpdate) {
        Optional<UserEntity> userEntity = userRepository.findById(userUpdate.getId());

        if (userEntity.isEmpty()) {
            log.error(USER_UPDATE_ERROR);
            throw new RuntimeException(USER_UPDATE_ERROR);
        }

        final UserEntity updatedUserEntity = userEntity.get();
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
        log.info(format(USER_DELETE_INFO, id));
    }
}
