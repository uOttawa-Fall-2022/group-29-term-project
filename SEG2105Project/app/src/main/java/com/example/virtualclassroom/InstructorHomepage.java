package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InstructorHomepage extends AppCompatActivity {

    Button editCourseBtn, viewAllCoursesBtn, searchCourseBtn;
    EditText searchTextBox;
    TextView codeRow, nameRow, daysRow, hoursRow, descriptionRow, capacityRow, instructorRow, title;

    ArrayList<String> coursesList;
    ArrayAdapter adapter;

    ListView allCourses;

    CourseDB db;

    String instructorName;
    Courses viewedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_homepage2);

        allCourses = (ListView) findViewById(R.id.viewAllCourses);

        db = new CourseDB(InstructorHomepage.this);
        coursesList = new ArrayList<>();

        viewAllCoursesBtn = (Button) findViewById(R.id.allCourses);
        editCourseBtn = (Button) findViewById(R.id.editCourse);
        searchCourseBtn = (Button) findViewById(R.id.searchBtn);

        searchTextBox = (EditText) findViewById(R.id.searchText);

        codeRow = (TextView) findViewById(R.id.row1);
        nameRow = (TextView) findViewById(R.id.row2);
        daysRow = (TextView) findViewById(R.id.row3);
        hoursRow = (TextView) findViewById(R.id.row4);
        descriptionRow = (TextView) findViewById(R.id.row5);
        capacityRow = (TextView) findViewById(R.id.row6);
        instructorRow = (TextView) findViewById(R.id.row7);

        title = (TextView) findViewById(R.id.instructorTitle);

        Intent intentName = getIntent();
        instructorName = intentName.getStringExtra("instructor_name");

        String titleString = "Instructor Homepage for "+instructorName;
        title.setText(titleString);

        viewAllCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllCourses();
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchForCourse();
            }
        });

        editCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewedCourse==null){
                    Toast.makeText(InstructorHomepage.this,"Error: No course viewed",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("instructor_name",instructorName);
                    intent.putExtra("course_name",viewedCourse.getCourseName());
                    startActivity(intent);
                }
            }
        });
    }

    private void viewAllCourses(){
        coursesList.clear();
        ArrayList<Courses> courses = db.getAllCourses();
        if(courses.isEmpty()){
            Toast.makeText(InstructorHomepage.this, "No courses to show.", Toast.LENGTH_SHORT).show();
        }else{
            Courses[] courseArray = (Courses[]) courses.toArray();
            for (Courses value : courseArray) {
                coursesList.add("Code: " + value.getCourseCode() + ", Name: " + value.getCourseName());
            }
        }

        adapter = new ArrayAdapter<>(InstructorHomepage.this, android.R.layout.simple_list_item_1 ,coursesList);
        allCourses.setAdapter(adapter);
    }

    private void searchForCourse(){
        String searchString = searchTextBox.getText().toString();
        Courses course1 = db.findCourseByCode(searchString);
        if(course1==null){
            Courses course2 = db.findCourseByName(searchString);
            if(course2==null){
                Toast.makeText(InstructorHomepage.this,"Error: No Such Course Exists",Toast.LENGTH_SHORT).show();
            }else{
                setCourseViewer(course2);
                viewedCourse = course2;
            }
        }else{
            setCourseViewer(course1);
            viewedCourse = course1;
        }
        searchTextBox.setText("");
    }

    private void setCourseViewer(Courses course){
        String code = "Course code: "+course.getCourseCode();
        String name = "Course name: "+course.getCourseName();
        String days = "Course days: "+course.getCourseDays();
        String hours = "Course hours: "+course.getCourseHours();
        String desc = "Description: "+course.getCourseDescription();
        String cap = "Capacity: "+course.getCourseStudentCapacity();
        String instr = "Instruction: "+course.getInstructor();

        codeRow.setText(code);
        nameRow.setText(name);
        daysRow.setText(days);
        hoursRow.setText(hours);
        descriptionRow.setText(desc);
        capacityRow.setText(cap);
        instructorRow.setText(instr);
    }
}