package com.example.demo.model.entities;

import com.example.demo.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Objects;

@Entity(name="users")
//@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    private String name;

    private int age;

    private Date createdAt;

    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    public UserEntity() {

    }
    public UserEntity(String name, int age, Date createdAt, Date updatedAt, Role role, String password) {
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
        this.password = password;
    }

    public UserEntity(long id, String name, int age, Date createdAt, Date updatedAt, Role role, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
        this.password = password;
    }

//    public UserEntity(String name, int age, String password) {
//        this.name = name;
//        this.age = age;
//        this.password = password;
//    }


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
    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
