package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class TestResultDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public TestResultDB(@Nullable Context context) {
        super(context, "testresultDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String testSQL = "create table TestResult(" +
                "_id integer primary key autoincrement," +
                "date text, " + //검사 날짜
                "ques_numid text, " + //질문 아이디
                "answer text)";  // 질문 답

        db.execSQL(testSQL);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table TestResult");
            onCreate(db);
        }
    }
}