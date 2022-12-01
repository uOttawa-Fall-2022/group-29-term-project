package com.example.virtualclassroom;

import java.util.ArrayList;

public class Student extends User{

    ArrayList<Courses> courseList = new ArrayList<>();

    public Student(String name, String username, String password) {
        super(name, username, password);
    }

    public String getType() {
        return "Student";
    }

    public void enrollInCourse(Courses course) {
        courseList.add(course);
    }

    public void unEnrollFromCourse(String code) {

        for(int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseCode().equals(code)) {
                courseList.remove(i);
            }
        }
    }
}
