package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentEnroll extends AppCompatActivity {

    Button back, enroll, unenroll;
    EditText courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enroll);

        back = (Button) findViewById(R.id.studentEnrollToolbarBack);
        enroll = (Button) findViewById(R.id.studentCourseEnroll);
        unenroll = (Button) findViewById(R.id.studentCourseUnenroll);
        courseCode = (EditText) findViewById(R.id.studentEnrollCourseCode);
        CourseDB db = new CourseDB(StudentEnroll.this);

        back.setOnClickListener(view -> {
            Intent intent = new Intent(StudentEnroll.this, StudentHomepage.class);
            startActivity(intent);
        });

        enroll.setOnClickListener(view -> {

            for(int i = 0; i < SignUp.studentList().size(); i++) {
                if(SignUp.studentList().get(i).getUsername().equals(SignIn.getCurrStudent())) {

                    for(int t = 0; t < SignUp.studentList().get(i).courseList.size(); t++) {
                        if(SignUp.studentList().get(i).courseList.get(t).getCourseCode()
                                .equals(courseCode.getText().toString())) {
                            Toast.makeText(StudentEnroll.this, "Already enrolled to this course", Toast.LENGTH_SHORT).show();
                            break;
                        }else if( DayTimeConflict.dayHourConflict( SignUp.studentList().get(i).courseList.get(t),
                                db.findCourseByCode(courseCode.getText().toString()) ) ){
                            Toast.makeText(StudentEnroll.this, "Course time conflicts with other course you are already enrolled in.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    SignUp.studentList().get(i).enrollInCourse(
                            db.findCourseByCode(courseCode.getText().toString()));

                    Toast.makeText(StudentEnroll.this, "Successfully enrolled to this course", Toast.LENGTH_SHORT).show();
                    break;

                }
            }

            Toast.makeText(StudentEnroll.this, "Student not found", Toast.LENGTH_SHORT).show();

        });


        unenroll.setOnClickListener(view -> {

            for(int i = 0; i < SignUp.studentList().size(); i++) {
                if(SignUp.studentList().get(i).getUsername().equals(SignIn.getCurrStudent())) {

                    for(int t = 0; t < SignUp.studentList().get(i).courseList.size(); t++) {
                        if(SignUp.studentList().get(i).courseList.get(t).getCourseCode()
                                .equals(courseCode.getText().toString())) {
                            SignUp.studentList().get(i).unEnrollFromCourse(courseCode.getText().toString());
                            Toast.makeText(StudentEnroll.this, "Successfully un-enrolled from this course", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    Toast.makeText(StudentEnroll.this, "Not enrolled in this course", Toast.LENGTH_SHORT).show();
                    break;

                }
            }

            Toast.makeText(StudentEnroll.this, "Student not found", Toast.LENGTH_SHORT).show();

        });


    }
}