package com.example.virtualclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminCourses extends AppCompatActivity {

    EditText courseCode, courseName, editCode, editName;
    Button findBtn, addBtn, editBtn, delBtn;
    TextView codeText, nameText, daysText, hoursText, descriptionText, capacityText, instructorText;

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

        codeText = (TextView) findViewById(R.id.codeRowText);
        nameText = (TextView) findViewById(R.id.nameRowText);
        daysText = (TextView) findViewById(R.id.daysRowText);
        hoursText = (TextView) findViewById(R.id.hoursRowText);
        descriptionText = (TextView) findViewById(R.id.descriptionRowText);
        capacityText = (TextView) findViewById(R.id.capacityRowText);
        instructorText = (TextView) findViewById(R.id.instructorRowText);

        CourseDB db = new CourseDB(AdminCourses.this);

        findBtn.setOnClickListener(view -> {

            Courses course = db.findCourseByCode(courseCode.getText().toString());

            //Courses course2 = db.findCourseByName(courseName.getText().toString());


            if(course!=null){
                Toast.makeText(AdminCourses.this,"Course info: Course name: " +
                        course.getCourseCode() + "  Course code: " +
                        course.getCourseName(),Toast.LENGTH_SHORT).show();
                setCourseDisplayInfo(course);
            }

/*            else if(course!=null){
                Toast.makeText(AdminCourses.this,"Course info: Course name: " +
                        course.getCourseCode() + "  Course code: " +
                        course.getCourseName(),Toast.LENGTH_SHORT).show();
            }*/


            else{
                Toast.makeText(AdminCourses.this,"Invalid course name or invalid course code.",Toast.LENGTH_SHORT).show();
            }
        });

        addBtn.setOnClickListener(view -> {

            String code = courseCode.getText().toString();
            String name = courseName.getText().toString();


            if(code.equals("")||name.equals("")){
                Toast.makeText(AdminCourses.this,"Error: Course name and code cannot be blank.",Toast.LENGTH_SHORT).show();
            }
            else if(db.findCourseByName(name) == null && db.findCourseByCode(code) == null) {
                db.addCourse(new Courses(code, name));
                Toast.makeText(AdminCourses.this, "Course successfully added.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AdminCourses.this, "Error: a course with that code or name already exists.", Toast.LENGTH_SHORT).show();
            }
        });

        editBtn.setOnClickListener(view -> {

            if(!courseCode.getText().toString().equals("") && !courseName.getText().toString().equals("")
            && !editCode.getText().toString().equals("") && !editName.getText().toString().equals("")) {

                Courses course = db.findCourseByCode(courseCode.getText().toString());
                Courses course2 = db.findCourseByName(courseName.getText().toString());

                Courses editCourse = db.findCourseByCode(editCode.getText().toString());
                Courses editCourse2 = db.findCourseByCode(editName.getText().toString());

                if (course == null || course2 == null) {
                    Toast.makeText(AdminCourses.this, "Error: course name or course code point to a nonexistent course.", Toast.LENGTH_SHORT).show();
                } else if (!course.getCourseName().equals(course2.getCourseName())) {
                    Toast.makeText(AdminCourses.this, "Error: course name and course code point to two different courses.", Toast.LENGTH_SHORT).show();
                } else if (editCourse != null || editCourse2 != null) {
                    Toast.makeText(AdminCourses.this, "Error: new name or code already used by another course.", Toast.LENGTH_SHORT).show();
                } else {
                    db.editCourse(course.getCourseCode(), course2.getCourseName(), editCode.getText().toString(), editName.getText().toString());
                    Toast.makeText(AdminCourses.this, "Course edited successfully.", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(AdminCourses.this, "Error: New course name and code cannot be blank.", Toast.LENGTH_SHORT).show();
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

    private void setCourseDisplayInfo(Courses course){
        String code = "Course code: "+nullCheck(course.getCourseCode());
        String name = "Course name: "+nullCheck(course.getCourseName());
        String days = "Course days: "+nullCheck(course.getCourseDays());
        String hours = "Course hours: "+nullCheck(course.getCourseHours());
        String desc = "Description: "+nullCheck(course.getCourseDescription());
        String cap = "Student capacity: "+negCheck(course.getCourseStudentCapacity());
        String ins = "Instructor: "+nullCheck(course.getInstructor());

        codeText.setText(code);
        nameText.setText(name);
        daysText.setText(days);
        hoursText.setText(hours);
        descriptionText.setText(desc);
        capacityText.setText(cap);
        instructorText.setText(ins);
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