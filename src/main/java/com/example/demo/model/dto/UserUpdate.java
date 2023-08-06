package com.example.demo.model.dto;

import com.example.demo.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public class UserUpdate {

    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 0, message = "Age can't be less than 0")
    @Max(value = 150, message = "Age can't be more than 150")
    private int age;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is mandatory")
    private Role role;

    @NotBlank(message = "Password is mandatory")
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
