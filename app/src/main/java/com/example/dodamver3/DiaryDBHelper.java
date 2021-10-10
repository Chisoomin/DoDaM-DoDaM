package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DiaryDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;

    //미완성!


    public DiaryDBHelper(@Nullable Context context) {
        super(context, "diarydb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DiarySQL = "create table DiaryData(" +
                "_id integer primary key autoincrement," +
                "happy int, " + // 기쁨 최소 0 최대 10
                "bad int, " + //화남
                "sad int, " + // 슬픔
                "date datetime default CURRENT_TIMESTAMP, " + // 날짜
                "content text)"; //내용

        db.execSQL(DiarySQL);
        DiarySQL = "insert into DiaryData(happy, bad, sad, date, content) values(5, 0, 0, '2021-10-02', '오늘은 팀 프로젝트 중이다.')";
        db.execSQL(DiarySQL);
        DiarySQL = "insert into DiaryData(happy, bad, sad, date, content) values(0, 8, 8, '2021-10-01', '과제가 많다.')";
        db.execSQL(DiarySQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table DiaryData");
            onCreate(db);
        }
    }
}
