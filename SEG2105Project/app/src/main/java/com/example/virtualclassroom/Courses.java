package com.example.virtualclassroom;

public class Courses{
    // Admin attributes
    private String courseCode;
    private String courseName;

    // Instructor attributes
    private String courseDays;
    private String courseHours;
    private String courseDescription;
    private int courseStudentCapacity;

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
    public String getCourseDays() { return courseDays; }
    public String getCourseHours() { return courseHours; }
    public String getCourseDescription() { return courseDescription; }
    public int getCourseStudentCapacity() {return courseStudentCapacity; }
    //setters
    public void setCourseCode(String code){
        courseCode = code;
    }
    public void setCourseName(String name){
        courseName = name;
    }
    public void setCourseDays(String days) { courseDays = days; }
    public void setCourseHours(String hours) { courseHours = hours; }
    public void setCourseDescription(String description) { courseDescription = description; }
    public void setCourseStudentCapacity(int capacity) { courseStudentCapacity = capacity; }

}