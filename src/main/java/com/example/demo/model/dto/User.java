package com.example.demo.model.dto;

import com.example.demo.model.Role;
import lombok.Data;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private int age;
    private Date createdAt;
    private Date updatedAt;
    private Role role;

    public User(long id, String name, int age, Date createdAt, Date updatedAt, Role role) {
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
    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

}
