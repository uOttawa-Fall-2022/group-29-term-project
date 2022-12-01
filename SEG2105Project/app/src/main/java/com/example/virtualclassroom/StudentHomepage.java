package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StudentHomepage extends AppCompatActivity {

    Button Enroll, Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        Enroll = (Button) findViewById(R.id.StudentHPEnroll);
        Search = (Button) findViewById(R.id.StudentHPSearchCourses);

        Enroll.setOnClickListener(view -> {
            Intent intent = new Intent(StudentHomepage.this, StudentEnroll.class);
            startActivity(intent);
        });

        Search.setOnClickListener(view -> {
            Intent intent = new Intent(StudentHomepage.this, StudentSearchCourses.class);
            startActivity(intent);
        });


    }


}