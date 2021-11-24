package com.ourcr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "OCR";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "dataocr";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "text";
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewCourse(String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, courseName);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<CourseModal> readCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void updateCourse(String originalCourseName, String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, courseName);
        db.update(TABLE_NAME, values, "text=?", new String[]{originalCourseName});
        db.close();
    }

    public void deleteCourse(String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "text=?", new String[]{courseName});
        db.close();
    }


}