package com.example.demo.mappers;

import com.example.demo.model.Role;
import com.example.demo.model.dto.User;
import com.example.demo.model.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Test
    void should_mapUser_when_userEntityPassed() {
        // given
        LocalDateTime createdAt = LocalDateTime.of(2023, 12, 29, 12, 34);
        LocalDateTime updatedAt = LocalDateTime.of(2022, 12, 29, 12, 34);
        UserEntity userEntity = new UserEntity(1, "a", 2, createdAt, updatedAt, Role.USER, "bbb");

        // when
        User user = userMapper.map(userEntity);

        // then
        User expectedUser = new User(1, "a", 2, createdAt, updatedAt, Role.USER);
        assertThat(user).isEqualTo(expectedUser);
    }

    @Test
    void should_mapUserList_when_userEntityListPassed() {
        // given
        LocalDateTime createdAt = LocalDateTime.of(2023, 12, 29, 12, 34);
        LocalDateTime updatedAt = LocalDateTime.of(2022, 12, 29, 12, 34);
        UserEntity userEntity = new UserEntity(1, "a", 2, createdAt, updatedAt, Role.USER, "bbb");
        List<UserEntity> userEntities = List.of(userEntity);

        // when
        List<User> users = userMapper.map(userEntities);

        // then
        User user = new User(1, "a", 2, createdAt, updatedAt, Role.USER);
        List<User> expectedUsers = List.of(user);
        assertThat(users).isEqualTo(expectedUsers);
    }
}