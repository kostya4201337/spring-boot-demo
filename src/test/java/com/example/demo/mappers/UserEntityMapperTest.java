package com.example.demo.mappers;

import com.example.demo.model.Role;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.model.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserEntityMapperTest {

    @InjectMocks
    UserEntityMapper userEntityMapper;

    @Test
    void should_mapUserEntity_when_userCreationPassed() {
        //given
        UserCreation userCreation = new UserCreation("bob", 12, "qqq");

        //when
        UserEntity userEntity = userEntityMapper.map(userCreation);

        //then
        UserEntity expectedUserEntity = new UserEntity(0, "bob", 12, null, null, Role.USER, "qqq");

        assertThat(userEntity).isEqualTo(expectedUserEntity);
    }

    @Test
    void should_mapUserEntity_when_userUpdatePassed() {
        //given
        UserUpdate userUpdate = new UserUpdate(0, "bob", 12, Role.USER, "qqq");

        //when
        UserEntity userEntity = userEntityMapper.map(userUpdate);

        //then
        UserEntity expectedUserEntity = new UserEntity(0, "bob", 12, null, null, Role.USER, "qqq");
        assertThat(userEntity).isEqualTo(expectedUserEntity);
    }
}