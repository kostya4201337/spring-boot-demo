package com.example.demo.services.impl;

import com.example.demo.mappers.UserEntityMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.propery.BlackListProperties;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exception.NoUserFoundByIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BlackListProperties blackListProperties;

    private final UserEntity USER_ENTITY = new UserEntity(1, "a", 2, null, null, Role.USER, "qqq");
    private final User USER = new User(1, "a", 2, null, null, Role.USER);

    @Test
    void should_getUsersByAgeRange() {
        // given
        int min = 0;
        int max = 50;
        List<User> expectedUsers = List.of(USER);
        given(userRepository.findByAgeBetween(min, max)).willReturn(expectedUsers);

        // when
        List<User> users = userService.getUsersByAgeRange(min, max);

        // then
        assertThat(users).containsAll(expectedUsers);
    }

    @Test
    void should_getUsers_when_usersExist() {
        //given
        List<UserEntity> userEntities = List.of(USER_ENTITY);

        given(userRepository.findAll()).willReturn(userEntities);
        given(userMapper.map(USER_ENTITY)).willReturn(USER);
        given(blackListProperties.getNames()).willReturn(Set.of());

        //when
        List<User> users = userService.getUsers();

        //then
        List<User> expectedUsers = List.of(USER);
        assertThat(users).containsAll(expectedUsers);
    }

    @Test
    void should_getUsers_when_BlackListExists() {
        //given
        List<UserEntity> userEntities = List.of(USER_ENTITY, new UserEntity(2, "s", 14, null, null, Role.USER, "qqq"));

        given(userRepository.findAll()).willReturn(userEntities);
        given(userMapper.map(USER_ENTITY)).willReturn(USER);
        given(blackListProperties.getNames()).willReturn(Set.of("s"));

        //when
        List<User> users = userService.getUsers();

        //then
        List<User> expectedUsers = List.of(USER);
        assertThat(users).containsAll(expectedUsers);
    }

    @Test
    void should_getEmptyUsers_when_usersDontExist() {
        //given
        given(userRepository.findAll()).willReturn(List.of());

        //when
        List<User> users = userService.getUsers();

        //then
        assertThat(users).isEmpty();
    }

    @Test
    void should_getUserById_when_userExists() {
        //given
        given(userRepository.findById(1L)).willReturn(Optional.of(USER_ENTITY));
        given(userMapper.map(USER_ENTITY)).willReturn(USER);

        //when
        User user = userService.getUserById(1);

        //then
        assertThat(user).isEqualTo(USER);
    }

    @Test
    void should_throwException_when_userByIdDoesNotExist() {
        //given
        final String GET_USER_BY_ID_ERROR = "user with given id doesn't exist";
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> userService.getUserById(1));

        //then
        assertThat(throwable)
                .isInstanceOf(NoUserFoundByIdException.class)
                .hasMessageContaining(GET_USER_BY_ID_ERROR);
    }

    @Test
    void should_addUser_when_ageValid() {
        //given
        UserCreation userCreation = new UserCreation("a", 2, "qqq");
        given(userEntityMapper.map(userCreation)).willReturn(USER_ENTITY);

        //when
        userService.addUser(userCreation);

        //then
        then(userRepository).should().save(USER_ENTITY);
    }

    @Test
    void should_updateUser_when_ageValidAndUserExists() {
        //given
        Optional<UserEntity> userEntity = Optional.of(USER_ENTITY);
        UserUpdate userUpdate = new UserUpdate(1, "b", 2, Role.USER, "qqq");
        given(userRepository.findById(1L)).willReturn(userEntity);

        //when
        userService.updateUser(userUpdate);

        //then
        UserEntity expectedUserEntity = new UserEntity(1, "b", 2, null, null, Role.USER, "qqq");
        then(userRepository).should().save(expectedUserEntity);
    }

    @Test
    void should_throwException_when_updateUserDoesNotExist() {
        //given
        final String USER_UPDATE_ERROR = "user with given id does not exist and can't be updated";
        UserUpdate userUpdate = new UserUpdate(1, "a", 1, Role.USER, "qqq");
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> userService.updateUser(userUpdate));

        //then
        assertThat(throwable)
                .isInstanceOf(NoUserFoundByIdException.class)
                .hasMessageContaining(USER_UPDATE_ERROR);
    }

    @Test
    void should_deleteUser_when_userExists() {
        //given
        given(userRepository.existsById(1L)).willReturn(true);

        //when
        userService.deleteUser(1);

        //then
        then(userRepository).should().deleteById(1L);
    }

    @Test
    void should_throwException_when_deleteUserDoesNotExist() {
        //given
        final String USER_DELETE_ERROR = "user with the given ID does not exist and can't be deleted";
        given(userRepository.existsById(1L)).willReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> userService.deleteUser(1));

        //then
        assertThat(throwable)
                .isInstanceOf(NoUserFoundByIdException.class)
                .hasMessageContaining(USER_DELETE_ERROR);
    }
}