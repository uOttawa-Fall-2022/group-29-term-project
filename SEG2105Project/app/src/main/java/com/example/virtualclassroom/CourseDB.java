package com.example.virtualclassroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDB extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "courseDB.db";
    private static final String TABLE_COURSES = "courses";
    private static final String COLUMN_CODE = "coursecode";
    private static final String COLUMN_NAME = "coursename";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_DAYS = "coursedays";
    private static final String COLUMN_HOURS = "coursehours";
    private static final String COLUMN_DESCRIPTION = "coursedescription";
    private static final String COLUMN_CAPACITY = "columncapacity";
    private static final String COLUMN_INSTRUCTOR = "columninstructor";

    public CourseDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CODE
                + " TEXT," + COLUMN_NAME + " TEXT," + COLUMN_DAYS + " TEXT," + COLUMN_HOURS + " TEXT," + COLUMN_DESCRIPTION
                + " TEXT," + COLUMN_CAPACITY + " INTEGER," + COLUMN_INSTRUCTOR + " TEXT" + ")";
        db.execSQL(CREATE_COURSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COURSES);
        onCreate(db);
    }

    public boolean addCourse(Courses course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE, course.getCourseCode());
        values.put(COLUMN_NAME, course.getCourseName());
        values.put(COLUMN_DAYS, course.getCourseDays());
        values.put(COLUMN_HOURS, course.getCourseHours());
        values.put(COLUMN_DESCRIPTION, course.getCourseDescription());
        values.put(COLUMN_CAPACITY, course.getCourseStudentCapacity());
        values.put(COLUMN_INSTRUCTOR, course.getInstructor());

        long insert = db.insert(TABLE_COURSES, null,values);
        db.close();
        return insert != -1;
    }

    public Courses findCourseByName(String coursename){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +" = \"" + coursename + "\"";
        Cursor cursor =db.rawQuery(query,null);

        Courses course = new Courses();
        if(cursor.moveToFirst()){
            course.setCourseCode(cursor.getString(1));
            course.setCourseName(cursor.getString(2));
            course.setCourseDays(cursor.getString(3));
            course.setCourseHours(cursor.getString(4));
            course.setCourseDescription(cursor.getString(5));
            course.setCourseStudentCapacity(cursor.getInt(6));
            course.setCourseInstructor(cursor.getString(7));
        }else{
            course = null;
        }

        db.close();
        cursor.close();
        return course;
    }

    public Courses findCourseByCode(String coursecode){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_CODE +" = \"" + coursecode + "\"";
        Cursor cursor =db.rawQuery(query,null);

        Courses course = new Courses();
        if(cursor.moveToFirst()){
            course.setCourseCode(cursor.getString(1));
            course.setCourseName(cursor.getString(2));
            course.setCourseDays(cursor.getString(3));
            course.setCourseHours(cursor.getString(4));
            course.setCourseDescription(cursor.getString(5));
            course.setCourseStudentCapacity(cursor.getInt(6));
            course.setCourseInstructor(cursor.getString(7));
        }else{
            course = null;
        }

        db.close();
        cursor.close();
        return course;
    }

    public boolean deleteCourseByName(String coursename){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +" = \"" + coursename + "\"";
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_COURSES,COLUMN_ID+" = "+idStr,null);
            cursor.close();
            result=true;
        }

        db.close();
        return result;
    }

    public boolean deleteCourseByCode(String coursecode){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_CODE +" = \"" + coursecode + "\"";
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_COURSES,COLUMN_ID + " = "+idStr,null);
            cursor.close();
            result=true;
        }

        db.close();
        return result;
    }

    public boolean editCourse(String oldCode, String oldName, String newCode, String newName){
        Courses course = findCourseByCode(oldCode);
        Courses course2 = findCourseByName(oldName);

        if(course == null || course2 == null){
            return false;
        }

        if(!course.getCourseName().equals(course2.getCourseName())){
            return false;
        }

        deleteCourseByCode(oldCode);
        addCourse(new Courses(newCode,newName));
        return true;
    }

    // These are for use by the Instructor class
    public boolean setCourseInstructor(String courseName,String instructorName) {
        Courses course = findCourseByName(courseName);
        if (course == null) {
            return false;
        }
        if (deleteCourseByCode(course.getCourseCode())){
            course.setCourseInstructor(instructorName);
            addCourse(course);
            return true;
        }
        return false;
    }

    public boolean setCourseDays(String courseName, String days){
        Courses course = findCourseByName(courseName);
        if(course == null){
            return false;
        }
        if (deleteCourseByCode(course.getCourseCode())){
            course.setCourseDays(days);
            addCourse(course);
            return true;
        }
        return false;
    }

    public boolean setCourseHours(String courseName, String hours){
        Courses course = findCourseByName(courseName);
        if(course == null){
            return false;
        }
        if (deleteCourseByCode(course.getCourseCode())){
            course.setCourseHours(hours);
            addCourse(course);
            return true;
        }
        return false;
    }

    public boolean setCourseDescription(String courseName, String description){
        Courses course = findCourseByName(courseName);
        if(course == null){
            return false;
        }
        if (deleteCourseByCode(course.getCourseCode())){
            course.setCourseDescription(description);
            addCourse(course);
            return true;
        }
        return false;
    }

    public boolean setCourseCapacity(String courseName, int capacity){
        Courses course = findCourseByName(courseName);
        if(course == null){
            return false;
        }
        if (deleteCourseByCode(course.getCourseCode())){
            course.setCourseStudentCapacity(capacity);
            addCourse(course);
            return true;
        }
        return false;
    }
}
