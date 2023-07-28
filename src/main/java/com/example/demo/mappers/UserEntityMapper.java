package com.example.demo.mappers;

import com.example.demo.model.Role;
import com.example.demo.model.dto.UserCreation;
import com.example.demo.model.dto.UserUpdate;
import com.example.demo.model.entities.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class UserEntityMapper {

    public UserEntity map(UserCreation userCreation) {
        return new UserEntity(userCreation.getName(), userCreation.getAge(), LocalDateTime.now(), LocalDateTime.now(), Role.USER, userCreation.getPassword());
    }
    public UserEntity map(UserUpdate userUpdate) {
        return new UserEntity(userUpdate.getId(), userUpdate.getName(), userUpdate.getAge(), LocalDateTime.now(), LocalDateTime.now(), userUpdate.getRole(), userUpdate.getPassword());
    }
}
