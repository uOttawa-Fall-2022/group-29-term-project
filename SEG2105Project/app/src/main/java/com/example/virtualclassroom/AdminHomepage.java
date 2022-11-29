package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

public class AdminHomepage extends AppCompatActivity {

    EditText name;
    EditText username;
    EditText password;
    EditText confirmpass;
    Button addStudent;
    Button addInstructor;
    Button delete;
    Button courseMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminHomepage.this);
        
        name = (EditText) findViewById(R.id.nameAH);
        username = (EditText) findViewById(R.id.usernameAH);
        password = (EditText) findViewById(R.id.passwordAH);
        confirmpass = (EditText) findViewById(R.id.confirmpassAH);
        addStudent = (Button) findViewById(R.id.addStudent);
        addInstructor = (Button) findViewById(R.id.addInstructor);
        delete = (Button) findViewById(R.id.deleteAccount);
        courseMenu = (Button) findViewById(R.id.courseMenu);

        addStudent.setOnClickListener(v -> {
            Student student;

            try {
                
                if(username.getText().toString().length() > 20 ||
                        password.getText().toString().length() > 20) {
                    Toast.makeText(AdminHomepage.this, "Username or Password must be less" +
                            " than 20 characters", Toast.LENGTH_SHORT).show();
                }else if((username.getText().toString().equals("")) ||
                        (name.getText().toString().equals("")) ||
                        (password.getText().toString().equals(""))) {
                    Toast.makeText(AdminHomepage.this, "Name or Username or Password or cannot " +
                                    "be blank",
                            Toast.LENGTH_SHORT).show();
                }else if (!(password.getText().toString()).equals(confirmpass.getText().toString())) {
                    Toast.makeText(AdminHomepage.this, "Confirm Password does not match with " +
                            "Password", Toast.LENGTH_SHORT).show();
                }else if (dataBaseHelper.checkUsername(name.getText().toString())) {
                    Toast.makeText(AdminHomepage.this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    student = new Student(name.getText().toString(), username.getText().toString(),
                            password.getText().toString());
                    boolean success = dataBaseHelper.addOne(student);
                    Toast.makeText(AdminHomepage.this, "Successfully registered " + success, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(AdminHomepage.this, "Error creating Student",
                        Toast.LENGTH_SHORT).show();
            }

        });

        addInstructor.setOnClickListener(v -> {

            Instructor instructor;

            try {

                if(username.getText().toString().length() > 20 ||
                        password.getText().toString().length() > 20) {
                    Toast.makeText(AdminHomepage.this, "Username or Password must be less" +
                            " than 20 characters", Toast.LENGTH_SHORT).show();
                }else if((username.getText().toString().equals("")) ||
                        (name.getText().toString().equals("")) ||
                        (password.getText().toString().equals(""))) {
                    Toast.makeText(AdminHomepage.this, "Name or Username or Password or cannot " +
                                    "be blank",
                            Toast.LENGTH_SHORT).show();
                }else if (!(password.getText().toString()).equals(confirmpass.getText().toString())) {
                    Toast.makeText(AdminHomepage.this, "Confirm Password does not match with " +
                            "Password", Toast.LENGTH_SHORT).show();
                }else if (dataBaseHelper.checkUsername(name.getText().toString())) {
                    Toast.makeText(AdminHomepage.this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    instructor = new Instructor(name.getText().toString(), username.getText().toString(),
                            password.getText().toString());
                    boolean success = dataBaseHelper.addOne(instructor);
                    Toast.makeText(AdminHomepage.this, "Successfully registered " + success, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(AdminHomepage.this, "Error creating Instructor",
                        Toast.LENGTH_SHORT).show();
            }

        });

        delete.setOnClickListener(v -> {
            if ((username.getText().toString().equals(""))) {

                Toast.makeText(AdminHomepage.this, " Username cannot be blank",
                        Toast.LENGTH_SHORT).show();
            } else if(!dataBaseHelper.checkUsername(username.getText().toString())) {
                Toast.makeText(AdminHomepage.this, "Username does not exist", Toast.LENGTH_SHORT).show();
            } else {
                boolean success = dataBaseHelper.deleteOne(username.getText().toString());
                Toast.makeText(AdminHomepage.this, "Successfully removed " + success, Toast.LENGTH_SHORT).show();
            }
        });


        courseMenu.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomepage.this, AdminCourses.class);
            startActivity(intent);
        });


    }

}

