package com.example.a2hanj43.android5uis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 2hanj43 on 21/03/2018.
 */

public class MyHelper extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String DATABASE_NAME = "TestDB";
    static final String TABLE_NAME = "Puns";
    static final String COL_1 = "ID";
    static final String COL_2 = "Pun";
    static final String COL_3 = "Genre";
    SQLiteDatabase SQLITEDATABASE;

    public MyHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, 1); //3rd parnathese was VERSION
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL ("create TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Pun VARCHAR(255), Genre VARCHAR(255))");
        //db.execSQL ("CREATE TABLE IF NOT EXISTS Puns (Id INTEGER PRIMARY KEY, Pun VARCHAR(255), Genre VARCHAR(255))");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String Pun, String Genre)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Pun);
        contentValues.put(COL_3, Genre);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public Cursor deleteData(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("DELETE FROM " + TABLE_NAME, null);
        return res;
    }

    public Cursor getSearchData(String s){
        SQLiteDatabase db = getReadableDatabase();
        //String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " =\" " + s + "\";";
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " LIKE '%" + s + "%' ";
        Cursor res = db.rawQuery(selectQuery, null);
        return res;
    }

}