package com.example.demo.model.dto;

import com.example.demo.model.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private long id;

    private String name;

    private int age;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Role role;

    public User(final long id, final String name, final int age, final LocalDateTime createdAt, final LocalDateTime updatedAt, final Role role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Role getRole() {
        return role;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, createdAt, updatedAt, role);
    }

}
