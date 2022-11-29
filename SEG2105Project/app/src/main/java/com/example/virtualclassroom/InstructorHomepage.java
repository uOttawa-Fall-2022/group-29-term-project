package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InstructorHomepage extends AppCompatActivity {

    Button viewCourse, searchCourse, assignCourse, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_homepage);

        viewCourse = (Button) findViewById(R.id.viewCourses);
        assignCourse = (Button) findViewById(R.id.assignCourse);
        logout = (Button) findViewById(R.id.insLogOut);

        viewCourse.setOnClickListener(view ->{
            Intent intent = new Intent(InstructorHomepage.this, InstructorViewAllCourses.class);
            startActivity(intent);
        });

        searchCourse.setOnClickListener(view -> {
            Intent intent = new Intent(InstructorHomepage.this, InstructorCourses.class);
            startActivity(intent);
        });

        assignCourse.setOnClickListener(view -> {

            Intent intent = new Intent(InstructorHomepage.this, AssignCourse.class);

            startActivity(intent);
        });

        logout.setOnClickListener(view -> {

            Intent intent = new Intent(InstructorHomepage.this, SignIn.class);

           SignIn.setCurrIns(null);

            startActivity(intent);

            Toast.makeText(InstructorHomepage.this, "Successfully logged out",
                    Toast.LENGTH_SHORT).show();
        });
    }



    
}