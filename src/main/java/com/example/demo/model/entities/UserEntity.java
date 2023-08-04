package com.example.demo.model.entities;

import com.example.demo.model.Role;
import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
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

    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && age == that.age && Objects.equals(name, that.name) && role == that.role && Objects.equals(password, that.password) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, role, password, createdAt, updatedAt);
    }

}
