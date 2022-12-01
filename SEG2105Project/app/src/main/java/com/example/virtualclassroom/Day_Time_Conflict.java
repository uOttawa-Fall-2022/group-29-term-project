package com.example.virtualclassroom;

public class Day_Time_Conflict {

    public static boolean dayTimeConflict(Courses course1, Courses course2){
        return (course1.getCourseDays().equals(course2.getCourseDays()) && course1.getCourseHours().equals(course2.getCourseHours()));
    }
}
