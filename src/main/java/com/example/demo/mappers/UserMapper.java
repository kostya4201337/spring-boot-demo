package com.example.demo.mappers;

import com.example.demo.model.Role;
import com.example.demo.model.dto.User;
import com.example.demo.model.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User map(UserEntity userEntity){
        User user = new User(userEntity.getId(), userEntity.getName(), userEntity.getAge(), userEntity.getCreatedAt(), userEntity.getUpdatedAt(), userEntity.getRole());
        return user;
    }

    public List<User> map(List<UserEntity> userEntity){
        List<User> users = new ArrayList<>();

        for (int i = 0; i < userEntity.size(); i++) {
            User user = map(userEntity.get(i));
            users.add(user);
        }

        return users;
    }
}
