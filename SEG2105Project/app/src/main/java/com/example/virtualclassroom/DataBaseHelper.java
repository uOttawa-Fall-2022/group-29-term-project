package com.example.virtualclassroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String ACCOUNTS_TABLE = "ACCOUNTS_TABLE";
    public static final String COLUMN_USER_TYPE = "USER_TYPE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_PASSWORD = "COLUMN_PASSWORD";
    public static final String COLUMN_ID = "ID";

    public boolean addOne(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_TYPE, user.getType());
        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_USER_NAME, user.getUsername());
        cv.put(COLUMN_PASSWORD, user.getPassword());

        long insert = db.insert(ACCOUNTS_TABLE, null, cv);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public DataBaseHelper(@Nullable Context context) {
        super(context, "accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ACCOUNTS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_TYPE + " TEXT, " + COLUMN_NAME + " TEXT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from ACCOUNTS_TABLE where USER_NAME = ?", new String[] {username});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean userType(String username, String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from ACCOUNTS_TABLE where USER_NAME = ? and " +
                        "USER_TYPE = ?",
                new String[] {username, type});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from ACCOUNTS_TABLE where " +
                "USER_NAME = ? and COLUMN_PASSWORD = ?", new String[] {username, password});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteOne(String username) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = ("DELETE FROM " + ACCOUNTS_TABLE + " WHERE " + COLUMN_USER_NAME + "=\"" +
                username + "\";");

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}
