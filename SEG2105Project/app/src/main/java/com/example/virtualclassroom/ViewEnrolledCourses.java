package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewEnrolledCourses extends AppCompatActivity {

    ArrayList<String> coursesList;
    ArrayAdapter adapter;

    ListView allCourses;

    CourseDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_view_all_courses);

        allCourses = (ListView) findViewById(R.id.viewAllCourses);

        db = new CourseDB(ViewEnrolledCourses.this);

        try {
            viewAllCourses();
        }catch(Exception e){
            Toast.makeText(ViewEnrolledCourses.this,"Error while displaying courses.",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void viewAllCourses(){
        coursesList.clear();
        ArrayList<Courses> courses=null;
        for(int i = 0; i < SignUp.studentList().size(); i++) {
           if(SignUp.studentList().get(i).getUsername().equals(SignIn.getCurrStudent())) {
               courses = SignUp.studentList().get(i).getCourseList();
           }
        }
        if(courses==null){
            Toast.makeText(ViewEnrolledCourses.this, "No courses to show.", Toast.LENGTH_SHORT).show();
        }else{
            for (Courses value : courses) {
                coursesList.add("Code: " + value.getCourseCode() + ", Name: " + value.getCourseName());
            }
        }

        adapter = new ArrayAdapter<>(ViewEnrolledCourses.this, android.R.layout.simple_list_item_1 ,coursesList);
        allCourses.setAdapter(adapter);
    }
}

