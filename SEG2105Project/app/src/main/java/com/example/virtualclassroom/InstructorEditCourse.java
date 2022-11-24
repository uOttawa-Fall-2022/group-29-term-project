package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    Courses course;
    String instructorName;

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

        Intent intent = getIntent();
        course = db.findCourseByName(intent.getStringExtra("course_name"));
        instructorName = intent.getStringExtra("instructor_name");

        setCourseViewerInfo();

        editDays.setOnClickListener(view ->{
            if(!course.getInstructor().equals(instructorName)){
                Toast.makeText(InstructorEditCourse.this,"Error: not instructor for this course.",Toast.LENGTH_SHORT).show();
            }
            else if(editDays(courseName.getText().toString(),updateCourseInfo.getText().toString())){
                String tmp = "Course days: "+updateCourseInfo.getText().toString();
                courseDays.setText(tmp);
                updateCourseInfo.setText("");
                Toast.makeText(InstructorEditCourse.this,"Course Updated",Toast.LENGTH_SHORT).show();
            }
        });

        editHours.setOnClickListener(view ->{
            if(!course.getInstructor().equals(instructorName)){
                Toast.makeText(InstructorEditCourse.this,"Error: not instructor for this course.",Toast.LENGTH_SHORT).show();
            }
            else if(editHours(courseName.getText().toString(),updateCourseInfo.getText().toString())){
                String tmp = "Course hours: "+updateCourseInfo.getText().toString();
                courseHours.setText(tmp);
                updateCourseInfo.setText("");
                Toast.makeText(InstructorEditCourse.this,"Course Updated",Toast.LENGTH_SHORT).show();
            }
        });
        editDescription.setOnClickListener(view ->{
            if(!course.getInstructor().equals(instructorName)){
                Toast.makeText(InstructorEditCourse.this,"Error: not instructor for this course.",Toast.LENGTH_SHORT).show();
            }
            else if(editDescription(courseName.getText().toString(),updateCourseInfo.getText().toString())){
                String tmp = "Description: "+updateCourseInfo.getText().toString();
                courseDescription.setText(tmp);
                updateCourseInfo.setText("");
                Toast.makeText(InstructorEditCourse.this,"Course Updated",Toast.LENGTH_SHORT).show();
            }
        });
        editCapacity.setOnClickListener(view ->{
            try {
                if (!course.getInstructor().equals(instructorName)) {
                    Toast.makeText(InstructorEditCourse.this, "Error: not instructor for this course.", Toast.LENGTH_SHORT).show();
                } else if (editCapacity(courseName.getText().toString(), Integer.parseInt(updateCourseInfo.getText().toString()))) {
                    String tmp = "Capacity: " + updateCourseInfo.getText().toString();
                    courseCapacity.setText(tmp);
                    updateCourseInfo.setText("");
                    Toast.makeText(InstructorEditCourse.this, "Course Updated", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                Toast.makeText(InstructorEditCourse.this,"Error while changing capacity.",Toast.LENGTH_SHORT).show();
            }
        });
        unAssign.setOnClickListener(view ->{
            if(unAssignInstructor(courseName.getText().toString())){
                String none = "NONE";
                String none1 = "Course days: "+none;
                String none2 = "Course hours: "+none;
                String none3 = "Description: "+none;
                String none4 = "Instructor: "+none;
                String none5 = "Capacity: 0";
                updateCourseInfo.setText("");
                courseDays.setText(none1);
                courseHours.setText(none2);
                courseDescription.setText(none3);
                courseInstructor.setText(none4);
                courseCapacity.setText(none5);
                Toast.makeText(InstructorEditCourse.this,"Unassigned instructor from course",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(InstructorEditCourse.this,"Error occurred while unassigning from course.",Toast.LENGTH_SHORT).show();
            }
        });
        assign.setOnClickListener(view ->{
            if(!course.getInstructor().equals("NONE") && assignInstructor(courseName.getText().toString(),instructorName)){
                String tmp = "Course days: "+updateCourseInfo.getText().toString();
                courseInstructor.setText(tmp);
                Toast.makeText(InstructorEditCourse.this,"Assigned instructor to course",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(InstructorEditCourse.this,"Error: course already has an instructor.",Toast.LENGTH_SHORT).show();
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
    public boolean assignInstructor(String courseName,String instructor){
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        return db.setCourseInstructor(courseName,instructor);
    }
    public boolean unAssignInstructor(String courseName) {
        CourseDB db = new CourseDB(InstructorEditCourse.this);
        boolean days,hours,desc,cap,ins;
        ins=db.setCourseInstructor(courseName,"NONE");
        days=db.setCourseDays(courseName,"NONE");
        hours=db.setCourseHours(courseName,"NONE");
        desc=db.setCourseDescription(courseName,"NONE");
        cap= db.setCourseCapacity(courseName,0);
        return(ins&&days&&hours&&cap&&desc);
    }

    private void setCourseViewerInfo(){
        String code = "Course code: "+course.getCourseCode();
        String name = "Course name: "+course.getCourseName();
        String days = "Course name: "+course.getCourseDays();
        String hours = "Course name: "+course.getCourseHours();
        String desc = "Course name: "+course.getCourseDescription();
        String cap = "Course name: "+course.getCourseStudentCapacity();
        String ins = "Course name: "+course.getInstructor();

        courseCode.setText(code);
        courseName.setText(name);
        courseDays.setText(days);
        courseHours.setText(hours);
        courseDescription.setText(desc);
        courseCapacity.setText(cap);
        courseInstructor.setText(ins);
    }
}