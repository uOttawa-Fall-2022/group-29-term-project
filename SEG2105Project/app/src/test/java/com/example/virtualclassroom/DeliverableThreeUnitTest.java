package com.example.virtualclassroom;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeliverableThreeUnitTest {

    @Test
    public void dayHourConflictTest(){
        Courses course = new Courses("CODE","NAME");
        course.setCourseDays("Monday");
        course.setCourseHours("1 pm - 2 pm");
        Courses otherCourse = new Courses("NAME","CODE");
        otherCourse.setCourseDays("Monday");
        otherCourse.setCourseHours("1 pm - 2 pm");
        assertTrue(DayTimeConflict.dayHourConflict(course,otherCourse));
    }

    @Test
    public void dayNullConflictTest(){
        Courses course = new Courses("CODE","NAME");
        course.setCourseDays("Monday");
        course.setCourseHours("1 pm - 2pm");
        Courses nullDay = new Courses("NAME","CODE");
        nullDay.setCourseHours("1 pm - 2pm");
        assertFalse(DayTimeConflict.dayHourConflict(course,nullDay));
    }

    @Test
    public void multiDayHourConflictTest(){
        Courses course = new Courses("CODE","NAME");
        course.setCourseDays("Monday, Friday, Tuesday");
        course.setCourseHours("1 pm - 2pm, 3 am - 4 am");
        Courses otherCourse = new Courses("NAME","CODE");
        otherCourse.setCourseDays("Wednesday, Tuesday");
        otherCourse.setCourseHours("3 pm - 4 pm, 5 pm - 6pm");
        assertFalse(DayTimeConflict.dayHourConflict(course,otherCourse));
    }

    @Test
    public void nullCourseConflict(){
        Courses course = new Courses("CODE","NAME");
        assertFalse(DayTimeConflict.dayHourConflict(course,null));
    }

    @Test
    public void directNullHours(){
        Courses course = new Courses("CODE","NAME");
        Courses otherCourse = new Courses("NAME","CODE");
        assertTrue(DayTimeConflict.hoursConflict(course,otherCourse));
    }
}
