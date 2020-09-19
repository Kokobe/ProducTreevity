package com.example.productreevity.classes;

import com.example.productreevity.TeacherHomeActivity;

public class Student extends User {
    private TeacherHomeActivity teacher;
    public Student(String name, String username, String password) {
        super(name, username, password);
        this.teacher = null;
    }
    public Student(String name, String username, String password, TeacherHomeActivity teacher) {
        super(name, username, password);
        this.teacher = teacher;
    }
}