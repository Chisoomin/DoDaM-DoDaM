package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class HabitAchieveDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public HabitAchieveDB(@Nullable Context context) {
        super(context, "habitachieveDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String HabitSQL = "create table HabitAchieve(" +
                "_id integer primary key autoincrement," +
                "numId text," +
                "date text," + // 달성 날짜
                "bColor text)"; // 배경색


        db.execSQL(HabitSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table HabitAchieve");
            onCreate(db);
        }
    }
}