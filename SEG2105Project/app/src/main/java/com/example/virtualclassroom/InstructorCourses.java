package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InstructorCourses extends AppCompatActivity {

    Button findCourse, editDays, editHours, editDescription, editCapacity;
    EditText updateCourseInfo;
    TextView courseCode, courseName, courseDays, courseHours, courseDescription,
            courseCapacity, courseInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_courses);

        findCourse = (Button) findViewById(R.id.findCourse);
        editDays = (Button) findViewById(R.id.setDays);
        editHours = (Button) findViewById(R.id.setHours);
        editDescription = (Button) findViewById(R.id.setDescription);
        editCapacity = (Button) findViewById(R.id.setCapacity);

        updateCourseInfo = (EditText) findViewById(R.id.updateCourseInfo);

        courseCode = (TextView) findViewById(R.id.courseCode);
        courseName = (TextView) findViewById(R.id.courseName);
        courseDays = (TextView) findViewById(R.id.courseDays);
        courseHours = (TextView) findViewById(R.id.courseHours);
        courseDescription = (TextView) findViewById(R.id.courseDescription);
        courseCapacity = (TextView) findViewById(R.id.courseCapacity);
        courseInstructor = (TextView) findViewById(R.id.courseInstructor);

        CourseDB db = new CourseDB(InstructorCourses.this);

        findCourse.setOnClickListener(view ->{
            Courses course = db.findCourseByCode(updateCourseInfo.getText().toString());
            if(course==null){
                Toast.makeText(InstructorCourses.this,"Error: No course with the code: "
                        +updateCourseInfo.getText().toString(),Toast.LENGTH_SHORT).show();
            }else{
                setCourseViewerInfo(course);
            }
            updateCourseInfo.setText("");
        });

        editDays.setOnClickListener(view -> {
            Courses course = db.findCourseByCode(courseCode.getText().toString().substring(13));
            if(course.getInstructor()!=null&&course.getInstructor().equals(SignIn.getCurrIns())){
                if(editDays(course.getCourseName(),updateCourseInfo.getText().toString())){
                    String newInfo = "Course days: "+updateCourseInfo.getText().toString();
                    courseDays.setText(newInfo);
                    Toast.makeText(InstructorCourses.this,"Course days for this course modified successfully.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InstructorCourses.this,"Error: Course days not modified.",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(InstructorCourses.this,"Not assigned to this course.",Toast.LENGTH_SHORT).show();
            }
            updateCourseInfo.setText("");
        });

        editHours.setOnClickListener(view ->{
            Courses course = db.findCourseByCode(courseCode.getText().toString().substring(13));
            if(course.getInstructor()!=null&&course.getInstructor().equals(SignIn.getCurrIns())){
                if(editHours(course.getCourseName(),updateCourseInfo.getText().toString())){
                    String newInfo = "Course hours: "+updateCourseInfo.getText().toString();
                    courseHours.setText(newInfo);
                    Toast.makeText(InstructorCourses.this,"Course hours for this course modified successfully.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InstructorCourses.this,"Error: Course hours not modified.",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(InstructorCourses.this,"Not assigned to this course.",Toast.LENGTH_SHORT).show();
            }
            updateCourseInfo.setText("");
        });

        editDescription.setOnClickListener(view ->{
            Courses course = db.findCourseByCode(courseCode.getText().toString().substring(13));
            if(course.getInstructor()!=null&&course.getInstructor().equals(SignIn.getCurrIns())){
                if(editDescription(course.getCourseName(),updateCourseInfo.getText().toString())){
                    String newInfo = "Description: "+updateCourseInfo.getText().toString();
                    courseDescription.setText(newInfo);
                    Toast.makeText(InstructorCourses.this,"Course description for this course modified successfully.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InstructorCourses.this,"Error: Course description not modified.",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(InstructorCourses.this,"Not assigned to this course.",Toast.LENGTH_SHORT).show();
            }
            updateCourseInfo.setText("");
        });

        editCapacity.setOnClickListener(view -> {
            Courses course = db.findCourseByCode(courseCode.getText().toString().substring(13));
            if(course.getInstructor()==null||!course.getInstructor().equals(SignIn.getCurrIns())){
                Toast.makeText(InstructorCourses.this,"Not assigned to this course.",Toast.LENGTH_SHORT).show();
            }
            try{
                if(editCapacity(course.getCourseName(),Integer.parseInt(updateCourseInfo.getText().toString()))){
                    String newInfo = "Capacity: "+updateCourseInfo.getText().toString();
                    courseCapacity.setText(newInfo);
                    Toast.makeText(InstructorCourses.this,"Student capacity for this course modified successfully.",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(InstructorCourses.this,"Error: Student capacity not modified successfully",Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Toast.makeText(InstructorCourses.this,"Error: Text entered was not a integer.",Toast.LENGTH_SHORT).show();
            }
            updateCourseInfo.setText("");
        });

    }

    public boolean editDays(String courseName,String days){
        CourseDB db = new CourseDB(InstructorCourses.this);
        return db.setCourseDaysByName(courseName,days);
    }
    public boolean editHours(String courseName,String hours){
        CourseDB db = new CourseDB(InstructorCourses.this);
        return db.setCourseHoursByName(courseName,hours);
    }
    public boolean editDescription(String courseName,String description){
        CourseDB db = new CourseDB(InstructorCourses.this);
        return db.setCourseDescriptionByName(courseName,description);
    }
    public boolean editCapacity(String courseName,int capacity){
        CourseDB db = new CourseDB(InstructorCourses.this);
        return db.setCourseCapacityByName(courseName,capacity);
    }

    private void setCourseViewerInfo(Courses course){
        String code = "Course code: "+nullCheck(course.getCourseCode());
        String name = "Course name: "+nullCheck(course.getCourseName());
        String days = "Course days: "+nullCheck(course.getCourseDays());
        String hours = "Course hours: "+nullCheck(course.getCourseHours());
        String desc = "Description: "+nullCheck(course.getCourseDescription());
        String cap = "Capacity: "+negCheck(course.getCourseStudentCapacity());
        String ins = "Instructor: "+nullCheck(course.getInstructor());

        courseCode.setText(code);
        courseName.setText(name);
        courseDays.setText(days);
        courseHours.setText(hours);
        courseDescription.setText(desc);
        courseCapacity.setText(cap);
        courseInstructor.setText(ins);
    }

    private String nullCheck(String courseInfo){
        if(courseInfo==null){
            return "";
        }else{
            return courseInfo;
        }
    }

    private String negCheck(int cap){
        if(cap<0){
            return "";
        }else{
            return ""+cap;
        }
    }
}