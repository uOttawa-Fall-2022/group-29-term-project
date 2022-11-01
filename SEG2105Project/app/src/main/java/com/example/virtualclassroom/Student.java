package com.example.virtualclassroom;

public class Student extends User{

    public Student(String name, String username, String password) {
        super(name, username, password);
    }

    public String getType() {
        return "Student";
    }
}
