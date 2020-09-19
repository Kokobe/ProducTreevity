package com.example.productreevity;

import java.util.HashMap;
import java.util.Map;

public class Teacher extends User {
    Map<String, Student> students;
    public Teacher(String name, String username, String password) {
        super(name, username, password);
        this.students = new HashMap<>();
    }
    public Teacher(String name, String username, String password, Map students) {
        super(name, username, password);
        this.students = students;
    }
    public void addStudent(String username, Student student) {
        this.students.put(username, student);
    }
}
