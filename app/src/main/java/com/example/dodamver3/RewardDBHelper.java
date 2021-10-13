package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.AccessControlContext;

class RewardDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public RewardDBHelper(@Nullable Context context) {
        super(context, "rewardDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String rewardSQL = "create table rewardData(" +
                "_id integer primary key autoincrement," +
                "image BLOB, " + // 이미지 파일
                "exp text, " + // 이미지 파일
                "detailExp text)"; // 업적 설명글

        db.execSQL(rewardSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table rewardData");
            onCreate(db);
        }
    }
}
