package com.example.productreevity.classes;

public class User {
    public String user_id;
    public String name;
    public String username;
    public String email;
    public String password;
    public String type;
    public String student_id; // student or class id
    public int seeds;

    public User(){}

    public User(String user_id, String name, String username, String email, String password, String type, String student_id) {
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.email = email;
        assert(email.contains("@") && email.contains(".") && email.indexOf('@') < email.lastIndexOf('.'));
        this.password = password;
        this.type = type;
        this.student_id = student_id;
        this.seeds = 0;
    }
    public int getSeeds() {
        return seeds;
    }
    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }
    public void changeSeeds(int seeds) {
        this.seeds += seeds;
    }
    public String getUsername() {
        return username;
    }
}
