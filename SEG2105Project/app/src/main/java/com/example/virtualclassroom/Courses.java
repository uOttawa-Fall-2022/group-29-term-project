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

    // instructor name class for Course
    private String courseInstructor;

    //Constructors
    public Courses(){
        courseCode = null;
        courseName = null;
        courseDays = null;
        courseHours = null;
        courseInstructor = null;
        courseDescription = null;
        courseStudentCapacity = -1;
    }
    public Courses(String code,String name){
        courseCode = code;
        courseName = name;
        courseDays = null;
        courseHours = null;
        courseInstructor = null;
        courseDescription = null;
        courseStudentCapacity = -1;
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
    public int getCourseStudentCapacity() { return courseStudentCapacity; }
    public String getInstructor() { return courseInstructor; }
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
    public void setCourseInstructor(String instructor) { courseInstructor = instructor; }

}