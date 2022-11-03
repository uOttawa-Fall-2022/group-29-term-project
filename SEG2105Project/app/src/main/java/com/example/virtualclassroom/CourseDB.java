package com.example.virtualclassroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.virtualclassroom.Courses;

public class CourseDB extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "courseDB.db";
    private static final String TABLE_COURSES = "courses";
    private static final String COLUMN_CODE = "coursecode";
    private static final String COLUMN_NAME = "coursename";
    private static String COLUMN_ID = "id";

    public CourseDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_COURSES + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CODE + " TEXT," + COLUMN_NAME+" TEXT" +  ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COURSES);
        onCreate(db);
    }

    public void addCourse(Courses course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE, course.getCourseCode());
        values.put(COLUMN_NAME, course.getCourseName());

        db.insert(TABLE_COURSES, null,values);
        db.close();
    }

    public Courses findCourseByName(String coursename){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_NAME +" = \"" + coursename + "\"";
        Cursor cursor =db.rawQuery(query,null);

        Courses course = new Courses();
        if(cursor.moveToFirst()){
            course.setCourseCode(cursor.getString(1));
            course.setCourseName(cursor.getString(2));
        }else{
            course = null;
        }

        db.close();
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
        }else{
            course = null;
        }

        db.close();
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
        addCourse(new Courses(newCode,newCode));
        return true;
    }
}
