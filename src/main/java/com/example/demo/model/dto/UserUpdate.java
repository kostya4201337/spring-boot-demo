package com.example.demo.model.dto;

import com.example.demo.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class UserUpdate {
    private long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    public UserUpdate (final long id, final String name, final int age, final Role role, final String password) {
        this.id = id;
        this.name = name;
        this.age = age;
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
}
