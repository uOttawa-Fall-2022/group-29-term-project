package com.example.virtualclassroom;

public class DayTimeConflict {

    public static boolean dayTimeConflict(Courses course1, Courses course2){
        if(course1==null || course2==null || course1.getCourseHours() == null || course1.getCourseDays()==null || course2.getCourseDays()==null || course2.getCourseHours()==null){
            return false;
        }
        return (course1.getCourseDays().equals(course2.getCourseDays()) && course1.getCourseHours().equals(course2.getCourseHours()));
    }
}
