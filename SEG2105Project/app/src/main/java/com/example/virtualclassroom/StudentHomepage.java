package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class StudentHomepage extends AppCompatActivity {

    Button Enroll, Search, inVIEW, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        Enroll = (Button) findViewById(R.id.StudentHPEnroll);
        Search = (Button) findViewById(R.id.StudentHPSearchCourses);
        inVIEW = (Button) findViewById(R.id.StudentViewCourses);
        logout = (Button) findViewById(R.id.StudentLogout);

        Enroll.setOnClickListener(view -> {
            Intent intent = new Intent(StudentHomepage.this, StudentEnroll.class);
            startActivity(intent);
        });

        Search.setOnClickListener(view -> {
            Intent intent = new Intent(StudentHomepage.this, StudentSearchCourses.class);
            startActivity(intent);
        });
        inVIEW.setOnClickListener(view -> {
            Intent intent = new Intent(StudentHomepage.this, ViewEnrolledCourses.class);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {

            Intent intent = new Intent(StudentHomepage.this, SignIn.class);

            SignIn.setCurrStudent(null);

            startActivity(intent);

            Toast.makeText(StudentHomepage.this, "Successfully logged out",
                    Toast.LENGTH_SHORT).show();
        });
    }

}