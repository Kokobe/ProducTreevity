package com.example.productreevity;

public abstract class User {
    private String name;
    private String username;
    private String password;
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
