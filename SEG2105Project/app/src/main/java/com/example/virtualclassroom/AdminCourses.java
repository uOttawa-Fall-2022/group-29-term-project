package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminCourses extends AppCompatActivity {

    EditText courseCode, courseName, editCode, editName;
    Button findBtn, addBtn, editBtn, delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_courses);

        courseCode = (EditText) findViewById(R.id.courseCode);
        courseName = (EditText) findViewById(R.id.courseName);
        editCode = (EditText) findViewById(R.id.editCode);
        editName = (EditText) findViewById(R.id.editName);

        findBtn = (Button) findViewById(R.id.findCourse);
        addBtn = (Button) findViewById(R.id.addCourse);
        editBtn = (Button) findViewById(R.id.editCourse);
        delBtn = (Button) findViewById(R.id.deleteCourse);

        CourseDB db = new CourseDB(AdminCourses.this);

        findBtn.setOnClickListener(view -> {

            Courses course = db.findCourseByCode(courseCode.getText().toString());
            Courses course2 = db.findCourseByName(courseName.getText().toString());

            if(course!=null){
                Toast.makeText(AdminCourses.this,"Course info: Course name: " +
                        course.getCourseCode() + "  Course code: " +
                        course.getCourseName(),Toast.LENGTH_SHORT).show();
            }
            else if(course2!=null){
                Toast.makeText(AdminCourses.this,"Course info: Course name: " +
                        course2.getCourseCode() + "  Course code: " +
                        course2.getCourseName(),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AdminCourses.this,"Invalid course name or invalid course code.",Toast.LENGTH_SHORT).show();
            }
        });

        addBtn.setOnClickListener(view -> {

            String code = courseCode.getText().toString();
            String name = courseName.getText().toString();

            if(db.findCourseByName(name) == null && db.findCourseByCode(code) == null) {
                db.addCourse(new Courses(code, name));
                Toast.makeText(AdminCourses.this, "Course successfully added.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AdminCourses.this, "Error: a course with that code or name already exists.", Toast.LENGTH_SHORT).show();
            }
        });

        editBtn.setOnClickListener(view -> {

            Courses course = db.findCourseByCode(courseCode.getText().toString());
            Courses course2 = db.findCourseByName(courseName.getText().toString());

            Courses editCourse = db.findCourseByCode(editCode.getText().toString());
            Courses editCourse2 = db.findCourseByCode(editName.getText().toString());

            if(course == null || course2 == null){
                Toast.makeText(AdminCourses.this, "Error: course name or course code point to a nonexistent course.", Toast.LENGTH_SHORT).show();
            }
            else if(!course.getCourseName().equals(course2.getCourseName())){
                Toast.makeText(AdminCourses.this, "Error: course name and course code point to two different courses.", Toast.LENGTH_SHORT).show();
            }
            else if(editCourse != null || editCourse2 != null){
                Toast.makeText(AdminCourses.this, "Error: new name or code already used by another course.", Toast.LENGTH_SHORT).show();
            }
            else{
                db.editCourse(course.getCourseCode(),course2.getCourseName(),editCode.getText().toString(),editName.getText().toString());
                Toast.makeText(AdminCourses.this, "Course edited successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        delBtn.setOnClickListener(view ->{
            Courses course = db.findCourseByCode(courseCode.getText().toString());
            if(course==null){
                Toast.makeText(AdminCourses.this, "Error: course does not exist.", Toast.LENGTH_SHORT).show();
            }
            else if(!course.getCourseName().equals(courseName.getText().toString())){
                Toast.makeText(AdminCourses.this, "Error: course name and course code point to two different courses.", Toast.LENGTH_SHORT).show();
            }
            else if(db.deleteCourseByCode(courseCode.getText().toString())){
                Toast.makeText(AdminCourses.this, "Course deleted successfully.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AdminCourses.this, "Error: course does not exist.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}