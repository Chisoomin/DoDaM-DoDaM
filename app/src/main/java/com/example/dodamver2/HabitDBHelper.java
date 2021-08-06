package com.example.dodamver2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class HabitDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    public HabitDBHelper(@Nullable Context context) {
        super(context, "habitDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String HabitSQL = "create table HabitData(" +
                "_id integer primary key autoincrement," +
                "numId text," +
                "goal text," + // 목표
                "step integer default 0)";  // 수치

        db.execSQL(HabitSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table HabitData");
            onCreate(db);
        }
    }
}
