package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminHomepage extends AppCompatActivity {

    Button viewAcc, delAcc, addIns, addStu, courseMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        viewAcc = (Button) findViewById(R.id.viewAccount);
        delAcc = (Button) findViewById(R.id.deleteAccount);
        addIns = (Button) findViewById(R.id.addInstructor);
        addStu = (Button) findViewById(R.id.addStudent);
        courseMenu = (Button) findViewById(R.id.coursesMenu);

        courseMenu.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomepage.this, AdminCourses.class);
            startActivity(intent);
        });
    }
}