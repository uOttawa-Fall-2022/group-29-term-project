package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignIn extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private TextView forgotPassText;
    private TextView signupText;
    private static String currIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(SignIn.this);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText1);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginBtn = (Button) findViewById(R.id.loginbtn);
        forgotPassText = (TextView) findViewById(R.id.forgotpassText);
        signupText = findViewById(R.id.signupText);
        DataBaseHelper db = new DataBaseHelper(SignIn.this);
        CourseDB currentInstructor = new CourseDB(SignIn.this);

        if(!db.checkUsername("admin123")) {
            dataBaseHelper.addOne(Admin.getAdmin());
        }

        signupText.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {
            if(db.checkUsernamePassword((usernameEditText.getText().toString()),
                    passwordEditText.getText().toString())) {
                Toast.makeText(SignIn.this, "Login Successful", Toast.LENGTH_SHORT).show();

                if(db.userType((usernameEditText.getText().toString()), "Admin")) {
                    Toast.makeText(SignIn.this, "Logged in as Admin: " + usernameEditText.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, AdminHomepage.class);
                    startActivity(intent);
                } else if(db.userType((usernameEditText.getText().toString()), "Student")) {
                    Toast.makeText(SignIn.this, "Logged in as Student: " + usernameEditText.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                } else if(db.userType((usernameEditText.getText().toString()), "Instructor")) {
                    Toast.makeText(SignIn.this, "Logged in as Instructor: " + usernameEditText.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                    currIns = db.getInstructorName(usernameEditText.getText().toString());
                    Intent intent = new Intent(SignIn.this, InstructorHomepage.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(SignIn.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                usernameEditText.setText("");
                passwordEditText.setText("");
            }
        });

    }

    public static String getCurrIns() {
        return currIns;
    }

    public static void setCurrIns(String s) {
        currIns = s;
    }

}