package com.example.demo.model.dto;

public class UserCreation {

    private String name;

    private int age;

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
        if (age < 0) {
            return;
        }
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
