package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StudentSearchCourses extends AppCompatActivity {

    Button back, find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_courses);

        back = (Button) findViewById(R.id.studentSearchToolbarBack);
        find = (Button) findViewById(R.id.studentSearchCourse);


        back.setOnClickListener(view -> {
            Intent intent = new Intent(StudentSearchCourses.this, StudentHomepage.class);
            startActivity(intent);
        });

        find.setOnClickListener(view -> {});
    }

}