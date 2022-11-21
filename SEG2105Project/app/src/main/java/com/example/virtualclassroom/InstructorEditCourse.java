package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InstructorEditCourse extends AppCompatActivity {

    Button editDays, editHours, editDescription, editCapacity, assign, unAssign;
    EditText updateCourseInfo;
    TextView courseCode, courseName, courseDays, courseHours, courseDescription,
            courseCapacity, courseInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_homepage);

        editDays = (Button) findViewById(R.id.setDays);
        editHours = (Button) findViewById(R.id.setHours);
        editDescription = (Button) findViewById(R.id.setDescription);
        editCapacity = (Button) findViewById(R.id.setCapacity);
        assign = (Button) findViewById(R.id.teachCourse);
        unAssign = (Button) findViewById(R.id.unAssign);

        updateCourseInfo = (EditText) findViewById(R.id.updateCourseInfo);

        courseCode = (TextView) findViewById(R.id.courseCode);
        courseName = (TextView) findViewById(R.id.courseName);
        courseDays = (TextView) findViewById(R.id.courseDays);
        courseHours = (TextView) findViewById(R.id.courseHours);
        courseDescription = (TextView) findViewById(R.id.courseDescription);
        courseCapacity = (TextView) findViewById(R.id.courseCapacity);
        courseInstructor = (TextView) findViewById(R.id.courseInstructor);

        CourseDB db = new CourseDB(InstructorEditCourse.this);

        editDays.setOnClickListener(view ->{
            if(editDays(courseName.getText().toString(),updateCourseInfo.getText().toString())){
                courseDays.setText(updateCourseInfo.getText().toString());
                updateCourseInfo.setText("");
                Toast.makeText(InstructorEditCourse.this,"Course Updated",Toast.LENGTH_SHORT).show();
            }
        });

        editHours.setOnClickListener(view ->{
            if(editHours(courseName.getText().toString(),updateCourseInfo.getText().toString())){
                courseHours.setText(updateCourseInfo.getText().toString());
                updateCourseInfo.setText("");
                Toast.makeText(InstructorEditCourse.this,"Course Updated",Toast.LENGTH_SHORT).show();
            }
        });
        editDescription.setOnClickListener(view ->{
            if(editDescription(courseName.getText().toString(),updateCourseInfo.getText().toString())){
                courseDescription.setText(updateCourseInfo.getText().toString());
                updateCourseInfo.setText("");
                Toast.makeText(InstructorEditCourse.this,"Course Updated",Toast.LENGTH_SHORT).show();
            }
        });
        editCapacity.setOnClickListener(view ->{
            if(editCapacity(courseName.getText().toString(),Integer.parseInt(updateCourseInfo.getText().toString()))){
                courseCapacity.setText(updateCourseInfo.getText().toString());
                updateCourseInfo.setText("");
                Toast.makeText(InstructorEditCourse.this,"Course Updated",Toast.LENGTH_SHORT).show();
            }
        });
        unAssign.setOnClickListener(view ->{
            if(unAssignInstructor(courseName.getText().toString())){
                updateCourseInfo.setText("");
                courseDays.setText(updateCourseInfo.getText().toString());
                courseHours.setText(updateCourseInfo.getText().toString());
                courseDescription.setText(updateCourseInfo.getText().toString());
                courseInstructor.setText(updateCourseInfo.getText().toString());
                courseCapacity.setText(updateCourseInfo.getText().toString());
                Toast.makeText(InstructorEditCourse.this,"Unassigned instructor from course",Toast.LENGTH_SHORT).show();
            }
        });
        assign.setOnClickListener(view ->{
            if(false){        //assignInstructor(courseName.getText().toString())){
                Toast.makeText(InstructorEditCourse.this,"Assigned instructor to course",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean editDays(String courseName,String days){
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        return db.setCourseDays(courseName,days);
    }
    public boolean editHours(String courseName,String hours){
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        return db.setCourseHours(courseName,hours);
    }
    public boolean editDescription(String courseName,String description){
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        return db.setCourseDays(courseName,description);
    }
    public boolean editCapacity(String courseName,int capacity){
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        return db.setCourseCapacity(courseName,capacity);
    }
    public boolean assignInstructor(String courseName,Instructor instructor){
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        return db.setCourseInstructor(courseName,instructor);
    }
    public boolean unAssignInstructor(String courseName) {
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        boolean days,hours,desc,cap,ins;
        ins=db.setCourseInstructor(courseName,null);
        days=db.setCourseDays(courseName,null);
        hours=db.setCourseHours(courseName,null);
        desc=db.setCourseDescription(courseName,null);
        cap= db.setCourseCapacity(courseName,-1);
        return(ins&&days&&hours&&cap&&desc);
    }
}