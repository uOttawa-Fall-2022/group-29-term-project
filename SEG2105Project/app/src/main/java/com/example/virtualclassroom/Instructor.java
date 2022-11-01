package com.example.virtualclassroom;

public class Instructor extends User{

    public Instructor(String name, String username, String password) {
        super(name, username, password);
    }

    public String getType() {
        return "Instructor";
    }
}
