package com.example.virtualclassroom;

public class Admin extends User{

    private static Admin admin = new Admin("Admin", "admin123", "admin123");


    public Admin(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String getType() {
        return "Admin";
    }

    public static Admin getAdmin() {
        return admin;
    }
}
