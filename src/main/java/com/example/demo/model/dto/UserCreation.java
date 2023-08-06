package com.example.demo.model.dto;

import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCreation {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 0, message = "Age can't be less than 0")
    @Max(value = 150, message = "Age can't be more than 150")
    private int age;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public UserCreation(final String name, final int age, final String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
