package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StudentSearchCourses extends AppCompatActivity {

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_courses);

        back = (Button) findViewById(R.id.studentSearchToolbarBack);


        back.setOnClickListener(view -> {
            Intent intent = new Intent(StudentSearchCourses.this, StudentHomepage.class);
            startActivity(intent);
        });
    }

}