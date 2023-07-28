package com.example.demo.model.entities;

import com.example.demo.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity(name="users")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserEntity() {

    }

    public UserEntity(final String name, final int age, final LocalDateTime createdAt, final LocalDateTime updatedAt, final Role role, final String password) {
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
        this.password = password;
    }

    public UserEntity(final long id, final String name, final int age, final LocalDateTime createdAt, final LocalDateTime updatedAt, final Role role, final String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
