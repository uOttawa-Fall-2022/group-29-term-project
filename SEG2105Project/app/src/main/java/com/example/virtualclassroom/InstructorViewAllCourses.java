package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InstructorViewAllCourses extends AppCompatActivity {

    ArrayList<String> coursesList;
    ArrayAdapter adapter;

    ListView allCourses;

    CourseDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_view_all_courses);

        allCourses = (ListView) findViewById(R.id.viewAllCourses);

        db = new CourseDB(InstructorViewAllCourses.this);
        coursesList = new ArrayList<>();
        try {
            viewAllCourses();
        }catch(Exception e){
            Toast.makeText(InstructorViewAllCourses.this,"Error while displaying courses.",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void viewAllCourses(){
        coursesList.clear();
        ArrayList<Courses> courses = db.getAllCourses();
        if(courses.isEmpty()){
            Toast.makeText(InstructorViewAllCourses.this, "No courses to show.", Toast.LENGTH_SHORT).show();
        }else{
            for (Courses value : courses) {
                coursesList.add("Code: " + value.getCourseCode() + ", Name: " + value.getCourseName());
            }
        }

        adapter = new ArrayAdapter<>(InstructorViewAllCourses.this, android.R.layout.simple_list_item_1 ,coursesList);
        allCourses.setAdapter(adapter);
    }
}