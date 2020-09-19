package com.example.productreevity.classes;

public class User {
    public String name;
    public String username;
    public String password;
    public boolean teacher;
    public User(String name, String username, String password, boolean teacher) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.teacher = teacher;
    }
    public String getUsername() {
        return username;
    }
}
