package com.example.productreevity;

public class Student extends User {
    private Teacher teacher;
    public Student(String name, String username, String password) {
        super(name, username, password);
        this.teacher = null;
    }
    public Student(String name, String username, String password, Teacher teacher) {
        super(name, username, password);
        this.teacher = teacher;
    }
}
