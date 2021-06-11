package com.example.dodam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HabitDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;

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
        HabitSQL = "insert into HabitData(numId, goal, step) values('1', '하루에 물 2L 마시기', 0)";
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
