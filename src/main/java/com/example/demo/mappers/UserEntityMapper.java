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

    public UserEntity map(final UserCreation userCreation) {
        return new UserEntity(userCreation.getName(), userCreation.getAge(), null, null, Role.USER, userCreation.getPassword());
    }
    public UserEntity map(final UserUpdate userUpdate) {
        return new UserEntity(userUpdate.getId(), userUpdate.getName(), userUpdate.getAge(), null, null, userUpdate.getRole(), userUpdate.getPassword());
    }
}
