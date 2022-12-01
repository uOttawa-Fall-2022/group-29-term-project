package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SignUp extends AppCompatActivity {

    private EditText name;
    private EditText username;
    private EditText password;
    private EditText confirmpass;
    private Button backButton;
    private Button signUpStudentButton;
    private Button signUpInstructorButton;
    Admin admin = new Admin("Admin", "admin123", "admin123");
    private boolean adminFlag = false;
    private static ArrayList<Student> studentList = new ArrayList<>();

    public static ArrayList<Student> studentList() {
        return studentList;
    }

    public static void addStudentToList(Student student) {
        studentList.add(student);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(SignUp.this);
        name = (EditText) findViewById(R.id.nameEditText);
        username = (EditText) findViewById(R.id.usernameEditText);
        password = (EditText) findViewById(R.id.passEditText);
        confirmpass = (EditText) findViewById(R.id.confirmPassEditText);
        backButton = (Button) findViewById(R.id.backBtn);
        signUpStudentButton = (Button) findViewById(R.id.signUpStudentBtn);
        signUpInstructorButton = (Button) findViewById(R.id.signUpInstructorBtn);

        if(!adminFlag) {
            dataBaseHelper.addOne(admin);
            adminFlag = true;
        }

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });

        signUpStudentButton.setOnClickListener(v -> {

            Student student;

            try {

                if(username.getText().toString().length() > 20 ||
                        password.getText().toString().length() > 20) {
                    Toast.makeText(SignUp.this, "Username or Password must be less" +
                            " than 20 characters", Toast.LENGTH_SHORT).show();
                }else if((username.getText().toString().equals("")) ||
                        (name.getText().toString().equals("")) ||
                        (password.getText().toString().equals(""))) {
                    Toast.makeText(SignUp.this, "Name or Username or Password or cannot " +
                                    "be blank",
                            Toast.LENGTH_SHORT).show();
                }else if (!(password.getText().toString()).equals(confirmpass.getText().toString())) {
                    Toast.makeText(SignUp.this, "Confirm Password does not match with " +
                            "Password", Toast.LENGTH_SHORT).show();
                }else if (dataBaseHelper.checkUsername(name.getText().toString())) {
                    Toast.makeText(SignUp.this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    student = new Student(name.getText().toString(), username.getText().toString(),
                            password.getText().toString());
                    addStudentToList(student);
                    boolean success = dataBaseHelper.addOne(student);
                    Toast.makeText(SignUp.this, "Successfully registered " + success, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, SignIn.class);
                    startActivity(intent);
                }

            } catch (Exception e) {
                Toast.makeText(SignUp.this, "Error creating Student",
                        Toast.LENGTH_SHORT).show();
            }

        });

        signUpInstructorButton.setOnClickListener(v -> {

            Instructor instructor;

            try {

                if(username.getText().toString().length() > 20 ||
                        password.getText().toString().length() > 20) {
                    Toast.makeText(SignUp.this, "Username or Password must be less" +
                            " than 20 characters", Toast.LENGTH_SHORT).show();
                }else if((username.getText().toString().equals("")) ||
                        (name.getText().toString().equals("")) ||
                        (password.getText().toString().equals(""))) {
                    Toast.makeText(SignUp.this, "Name or Username or Password or cannot " +
                                    "be blank",
                            Toast.LENGTH_SHORT).show();
                }else if (!(password.getText().toString()).equals(confirmpass.getText().toString())) {
                    Toast.makeText(SignUp.this, "Confirm Password does not match with " +
                            "Password", Toast.LENGTH_SHORT).show();
                }else if (dataBaseHelper.checkUsername(name.getText().toString())) {
                    Toast.makeText(SignUp.this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    instructor = new Instructor(name.getText().toString(), username.getText().toString(),
                            password.getText().toString());
                    boolean success = dataBaseHelper.addOne(instructor);
                    Toast.makeText(SignUp.this, "Successfully registered " + success, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, SignIn.class);
                    startActivity(intent);
                }

            } catch (Exception e) {
                Toast.makeText(SignUp.this, "Error creating Instructor",
                        Toast.LENGTH_SHORT).show();
            }

        });

    }

}