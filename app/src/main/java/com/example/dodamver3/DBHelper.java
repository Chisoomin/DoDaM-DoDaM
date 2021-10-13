package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, "mydb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String testSQL = "create table Dodam(" +
                "_id integer primary key autoincrement," +
                "name text, " + // 이름
                "type text, " + //성별
                "pass text, " + // 비번
                "passHint text, " + // 비번 힌트
                "passHintAns text," + // 비번 힌트 답
                "birthday datetime,"+ // 생일
                "point integer)"; // 도담 포인트

        db.execSQL(testSQL);
        testSQL = "insert into Dodam(name, type, pass, passHint, passHintAns, birthday, point) values('김명지', '여자', '1111', '1이 네 개면?', '1111', '2000-11-08', 24)";
        db.execSQL(testSQL);
        /*testSQL = "insert into Dodam(name, type, pass, passHint, passHintAns, birthday, point) values('홍길동', '남자', '1234', '비번 1234', '1234', '1999-01-07', 0)";
        db.execSQL(testSQL);*/
    }

    /*
    @Override
    public void onCreate(SQLiteDatabase db) {
        String testSQL = "create table Dodam(" +
                "_id integer primary key autoincrement," +
                "name text, " + // 이름
                "type text, " + //성별
                "pass text)";

        db.execSQL(testSQL);
        testSQL = "insert into Dodam(name, type, pass) values('김명지','하루에 물 2L 마시기', '0000')";
    }

     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table Dodam");
            onCreate(db);
        }
    }
}
