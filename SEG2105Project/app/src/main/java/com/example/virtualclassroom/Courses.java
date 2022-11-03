package com.example.virtualclassroom;

public class Courses{
    private String courseCode;
    private String courseName;

    //Constructors
    public Courses(){
        courseCode = null;
        courseName = null;
    }
    public Courses(String code,String name){
        courseCode = code;
        courseName = name;
    }

    //getters
    public String getCourseCode(){
        return courseCode;
    }
    public String getCourseName(){
        return courseName;
    }
    //setters
    public void setCourseCode(String code){
        courseCode = code;
    }
    public void setCourseName(String name){
        courseName = name;
    }

}