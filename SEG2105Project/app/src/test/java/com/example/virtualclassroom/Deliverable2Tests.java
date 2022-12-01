package com.example.virtualclassroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class Deliverable2Tests {

    @Mock private CourseDB db;
    @Test
    public void createCourseInDatabase(){
        db = new CourseDB(null);
        Courses course = new Courses("CODE","NAME");
        db.addCourse(course);
        assertEquals(course,db.findCourseByCode("CODE"));
    }

    @Test
    public void editCourseInDatabase(){}

    @Test
    public void deleteCourseFromDatabase(){}

    @Test
    public void signUpInstructor(){}

    @Test
    public void signInInstructor(){}
}
