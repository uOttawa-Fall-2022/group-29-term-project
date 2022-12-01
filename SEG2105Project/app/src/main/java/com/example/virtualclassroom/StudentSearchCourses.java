package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentSearchCourses extends AppCompatActivity {

    ArrayList<String> coursesList;
    ArrayAdapter adapter;
    ListView courseViewer;
    Button back, find;
    EditText codeText, nameText, dayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_courses);

        back = (Button) findViewById(R.id.studentSearchToolbarBack);
        find = (Button) findViewById(R.id.studentSearchCourse);

        codeText = (EditText) findViewById(R.id.studentSearchCourseCode);
        nameText = (EditText) findViewById(R.id.studentSearchCourseName);
        dayText = (EditText) findViewById(R.id.studentSearchCourseDay);

        courseViewer = (ListView) findViewById(R.id.courseViewer);

        CourseDB db = new CourseDB(StudentSearchCourses.this);
        coursesList = new ArrayList<>();

        back.setOnClickListener(view -> {
            Intent intent = new Intent(StudentSearchCourses.this, StudentHomepage.class);
            startActivity(intent);
        });

        find.setOnClickListener(view -> {
            try {
                Courses codeCourse = db.findCourseByCode(codeText.getText().toString());
                Courses nameCourse = db.findCourseByName(nameText.getText().toString());
                ArrayList<Courses> dayCourses = db.findCourseByDay(dayText.getText().toString());
                if (codeCourse != null) {
                    ArrayList<Courses> c = new ArrayList<>();
                    c.add(codeCourse);
                    viewCourses(c);
                } else if (nameCourse != null) {
                    ArrayList<Courses> c = new ArrayList<>();
                    c.add(nameCourse);
                    viewCourses(c);
                } else if (!dayCourses.isEmpty()) {
                    viewCourses(dayCourses);
                } else {
                    Toast.makeText(StudentSearchCourses.this, "No courses found.", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                Toast.makeText(StudentSearchCourses.this, "Error occurred while processing information.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            codeText.setText("");
            nameText.setText("");
            dayText.setText("");
        });
    }

    private void viewCourses(ArrayList<Courses> coursesArrayList){
        coursesList.clear();
        for (Courses value : coursesArrayList) {
            if(value.getInstructor()!=null) {
                coursesList.add("Code: " + value.getCourseCode() + ", Name: " + value.getCourseName()
                        + "\n Days: " + value.getCourseDays() + ", Hours: " + value.getCourseHours()
                        + "\n Description: " + value.getCourseDescription() + "\n Capacity: " + value.getCourseStudentCapacity()
                        + ", Instructor: " + value.getInstructor());
            }
        }
        adapter = new ArrayAdapter(StudentSearchCourses.this, android.R.layout.simple_list_item_1, coursesList);
        courseViewer.setAdapter(adapter);
    }

}