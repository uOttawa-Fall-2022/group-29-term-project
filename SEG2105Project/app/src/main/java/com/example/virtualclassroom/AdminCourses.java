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
            Courses course2 = db.findCourseByName(courseName.getText().toString());

            if (course != null) {
                Toast.makeText(AdminCourses.this, "Course info: Course name: " +
                        course.getCourseCode() + "  Course code: " +
                        course.getCourseName(), Toast.LENGTH_SHORT).show();

                String codeString = "Course code: "+course.getCourseCode();
                String nameString = "Course name: "+course.getCourseName();
                String daysString = "Course days: "+course.getCourseDays();
                String hoursString = "Course hours: "+course.getCourseHours();
                String descriptionString = "Description: "+course.getCourseDescription();
                String capacityString = "Capacity: "+course.getCourseStudentCapacity();
                String instructorString = "Instructor: "+course.getInstructor();

                codeText.setText(codeString);
                nameText.setText(nameString);
                daysText.setText(daysString);
                hoursText.setText(hoursString);
                descriptionText.setText(descriptionString);
                capacityText.setText(capacityString);
                instructorText.setText(instructorString);

            } else if (course2 != null) {
                Toast.makeText(AdminCourses.this, "Course info: Course name: " +
                        course2.getCourseCode() + "  Course code: " +
                        course2.getCourseName(), Toast.LENGTH_SHORT).show();

                String codeString = "Course code: "+course2.getCourseCode();
                String nameString = "Course name: "+course2.getCourseName();
                String daysString = "Course days: "+course2.getCourseDays();
                String hoursString = "Course hours: "+course2.getCourseHours();
                String descriptionString = "Description: "+course2.getCourseDescription();
                String capacityString = "Capacity: "+course2.getCourseStudentCapacity();
                String instructorString = "Instructor: "+course2.getInstructor();

                codeText.setText(codeString);
                nameText.setText(nameString);
                daysText.setText(daysString);
                hoursText.setText(hoursString);
                descriptionText.setText(descriptionString);
                capacityText.setText(capacityString);
                instructorText.setText(instructorString);
            } else {
                Toast.makeText(AdminCourses.this, "Invalid course name or invalid course code.", Toast.LENGTH_SHORT).show();
            }
            courseCode.setText("");
            courseName.setText("");
        });

        addBtn.setOnClickListener(view -> {
            String code = courseCode.getText().toString();
            String name = courseName.getText().toString();

            Courses course = new Courses(code, name);
            course.setCourseInstructor("NONE");
            course.setCourseDays("NONE");
            course.setCourseHours("NONE");
            course.setCourseDescription("NONE");
            course.setCourseStudentCapacity(0);
            if(db.addCourse(course)){
                Toast.makeText(AdminCourses.this, "Course successfully added.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AdminCourses.this, "Error: Course not added.", Toast.LENGTH_SHORT).show();
            }
            courseCode.setText("");
            courseName.setText("");
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
            courseCode.setText("");
            courseName.setText("");
            editCode.setText("");
            editName.setText("");
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
            courseCode.setText("");
            courseName.setText("");

            codeText.setText(R.string.course_code);
            nameText.setText(R.string.course_name);
            daysText.setText(R.string.course_days);
            hoursText.setText(R.string.course_hours);
            descriptionText.setText(R.string.description);
            capacityText.setText(R.string.capacity);
            instructorText.setText(R.string.instructor);
        });
    }
}