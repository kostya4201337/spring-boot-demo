package com.example.demo.repositories;

import com.example.demo.model.dto.User;
import com.example.demo.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<User> findByAgeBetween(int min, int max);
}
