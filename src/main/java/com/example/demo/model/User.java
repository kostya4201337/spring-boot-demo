package com.example.demo.model;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
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
