package com.example.productreevity.classes;

import com.example.productreevity.StudentHomeActivity;

import java.util.HashMap;
import java.util.Map;

public class Teacher extends User {
    Map<String, StudentHomeActivity> students;
    public Teacher(String name, String username, String password) {
        super(name, username, password);
        this.students = new HashMap<>();
    }
    public Teacher(String name, String username, String password, Map students) {
        super(name, username, password);
        this.students = students;
    }
    public void addStudent(String username, StudentHomeActivity student) {
        this.students.put(username, student);
    }
}
