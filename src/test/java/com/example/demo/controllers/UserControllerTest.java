package com.example.demo.controllers;

import com.example.demo.model.Role;
import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private final User USER = new User(1, "a", 2, null, null, Role.USER);

    @Test
    void should_getUsers_when_usersExist() {
        //given
        List<User> expectedUsers = List.of(USER);

        given(userService.getUsers()).willReturn(expectedUsers);

        //when
        List<User> users = userController.getUsers();

        //then
        assertThat(users).containsAll(expectedUsers);
    }

    @Test
    void should_getUsersByAgeRange() {
        // given
        int min = 0;
        int max = 50;
        List<User> expectedUsers = List.of(USER);
        given(userService.getUsersByAgeRange(min, max)).willReturn(expectedUsers);

        // when
        List<User> users = userController.getUsersByAgeRange(min, max);

        // then
        assertThat(users).containsAll(expectedUsers);
    }

    @Test
    void should_getUserById() {
        //given
        given(userService.getUserById(1L)).willReturn(USER);

        //when
        User user = userController.getUserById(1);

        //then
        assertThat(user).isEqualTo(USER);
    }

    @Test
    void should_returnSuccessfulMessage_when_userAdded() {
        //given
        UserCreation userCreation = new UserCreation("a", 12, "qqq");
        willDoNothing().given(userService).addUser(userCreation);

        //when
        String message = userController.addUser(userCreation);

        //then
        String expectedMessage = "User has been added";
        assertThat(message).isEqualTo(expectedMessage);
    }

    @Test
    void should_returnSuccessfulMessage_when_userUpdated() {
        //given
        UserUpdate userUpdate = new UserUpdate(1, "b", 2, Role.USER, "qqq");
        willDoNothing().given(userService).updateUser(userUpdate);

        //when
        String message = userController.updateUser(userUpdate);

        //then
        String expectedMessage = "User has been updated";
        assertThat(message).isEqualTo(expectedMessage);
    }

    @Test
    void should_returnSuccessfulMessage_when_userDeleted() {
        //given
        willDoNothing().given(userService).deleteUser(1);

        //when
        String message = userController.deleteUser(2);

        //then
        String expectedMessage = "User has been deleted";
        assertThat(message).isEqualTo(expectedMessage);
    }
}