package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class ForYouDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public ForYouDBHelper(@Nullable Context context) {
        super(context, "foryoudb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ForYouSQL = "create table ForYouData(" +
                "_id integer primary key autoincrement," +
                "mood text, " + // 기분
                "music text, " + // 노래
                "artist text," + // 아티스트
                "albumImage BLOB," + // 아티스트
                "videoId text)"; // 비디오 아이디
        db.execSQL(ForYouSQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table PlayListData");
            onCreate(db);
        }
    }
}
