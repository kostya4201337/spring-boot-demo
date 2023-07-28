package com.example.demo.mappers;

import com.example.demo.model.Role;
import com.example.demo.model.dto.User;
import com.example.demo.model.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User map(final UserEntity userEntity){
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getAge(), userEntity.getCreatedAt(), userEntity.getUpdatedAt(), userEntity.getRole());
    }

    public List<User> map(final List<UserEntity> userEntity){
        final List<User> users = new ArrayList<>();
        userEntity.forEach(userEntity1 -> users.add(map(userEntity1)));
        return users;
    }
}
