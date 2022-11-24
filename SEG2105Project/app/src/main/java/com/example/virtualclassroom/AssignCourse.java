package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AssignCourse extends AppCompatActivity {
    
    EditText courseCode;
    Button assign, unassign;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_course);

        courseCode = (EditText) findViewById(R.id.courseCodeAC);
        assign = (Button) findViewById(R.id.assign);
        unassign = (Button) findViewById(R.id.unassign);
        back = (TextView) findViewById(R.id.assignBack);

        CourseDB db = new CourseDB(AssignCourse.this);

        assign.setOnClickListener(view -> {

            Courses course = db.findCourseByCode(courseCode.getText().toString());

            if((course == null)) {
                Toast.makeText(AssignCourse.this,"Invalid course code.",Toast.LENGTH_SHORT).show();

            } else if (course.getInstructor() == null) {
                db.setCourseInstructorByCode(courseCode.getText().toString(), SignIn.getCurrIns());
                Toast.makeText(AssignCourse.this, ("Successfully assigned to " + courseCode.getText().toString()),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AssignCourse.this, ("This course is currently assigned to " +
                        course.getInstructor().toString()), Toast.LENGTH_SHORT).show();
            }

        });


        unassign.setOnClickListener(view -> {

            Courses course = db.findCourseByCode(courseCode.getText().toString());

            if(course == null) {
                Toast.makeText(AssignCourse.this, "Invalid course code", Toast.LENGTH_SHORT).show();


            } else if((course.getInstructor().equals(SignIn.getCurrIns()))) {
                db.setCourseInstructorByCode(courseCode.getText().toString(), null);
                db.setCourseDays(courseCode.getText().toString(), null);
                db.setCourseHours(courseCode.getText().toString(), null);
                db.setCourseDescription(courseCode.getText().toString(), null);
                db.setCourseCapacity(courseCode.getText().toString(), -1);
                Toast.makeText(AssignCourse.this, "Successfully un-assigned", Toast.LENGTH_SHORT).show();

            } else if (course.getInstructor() == null) {

                Toast.makeText(AssignCourse.this, "Already un-assigned", Toast.LENGTH_SHORT).show();

            } else  if ((course.getInstructor() != null) && (!(course.getInstructor().equals(SignIn.getCurrIns())))) {

                Toast.makeText(AssignCourse.this,
                        "This course is currently assigned to " + course.getInstructor().toString(),
                        Toast.LENGTH_SHORT).show();
            }


        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(AssignCourse.this, InstructorHomepage.class);
            startActivity(intent);
        });


        
    }
    
    
}