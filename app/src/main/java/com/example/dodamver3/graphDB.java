package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class graphDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public graphDB(@Nullable Context context) {
        super(context, "graphDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String testSQL = "create table Graph(" +
                "_id integer primary key autoincrement," +
                "date test, " + //검사 날짜
                "score text)";  // 점수

        db.execSQL(testSQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table Graph");
            onCreate(db);
        }
    }
}