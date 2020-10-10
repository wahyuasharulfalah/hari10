package com.example.db_sqllite;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";

    private static final String CREATE_TABLE_STUDENTD = "CREATE TABLE" + TABLE_STUDENTS + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_FIRSTNAME + "TEXT );";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_STUDENTS
                + "("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_FIRSTNAME+" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS "+ TABLE_STUDENTS;
        db.execSQL(sql);
        onCreate(db);
    }
    public long addStudentDetail(String student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRSTNAME, student);
        long ins = db.insert(TABLE_STUDENTS,null,contentValues);
        db.close();
        return ins;
    }
    public ArrayList<String> getAllStudentsList(){
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String name = "";
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                name = cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME));
                studentsArrayList.add(name);
            } while (cursor.moveToNext());
            Log.d("arRais",studentsArrayList.toString());
        }
        return studentsArrayList;
    }

    }

