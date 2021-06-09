package com.example.dodam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlayListDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;

    //미완성!


    public PlayListDBHelper(@Nullable Context context) {
        super(context, "playlistdb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String PlayListSQL = "create table PlayListData(" +
                "_id integer primary key autoincrement," +
                "mood text, " + // 기분
                "music text, " + // 노래
                "artist text," + // 아티스트
                "videoId text)"; // 비디오 아이디

        // 감정별 노래
        // 555
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('555', 'Plastic Plants', 'Mahalia', 'GbcHje2KvIg')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('555', 'your text', 'Sundial', 'hoHCOymHbhM')";

        // 500
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('500', 'Dun Dun Dance', '오마이걸 (OH MY GIRL)','HzOjwL7IP_o')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('500', '내 손을 잡아', '아이유 (IU)', '3iM_06QeZi8')";


        // 050
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('050', 'There You Are', 'ZAYN','oSdLaBxde-w')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('050', 'Dazed & Confused', 'Ruel', 'Nel_hT0ZQHA')";

        // 005
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('005', '품행제로', '블락비 바스타즈','L2Gsg9NVGVo')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('005', 'Look What You Made Me Do', 'Taylor Swift', '3tmd-ClpJxA')";

        // 550
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('550', 'Feel Special', 'TWICE (트와이스)','3ymwOvzhwHs')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('550', 'Airplane', 'f(x)', 'TPzN__A7yeg')";

        // 505
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('505', 'good 4 u', 'Olivia Rodrigo','UyshwO7p7jw')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('505', 'Call me Cruella', 'Florence + the Machine', 'ljBZZmnFw_M')";

        // 055
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('055', 'Ride It', 'Regard','ucVUEmjKsko')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('055', 'W (Feat. Gunna)', 'Koffee', 'XQF69A3oGjM')";

        // 000
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('000', '금요일에 만나요 (Feat. 장이정 of HISTORY)', '아이유 (IU)','EiVmQZwJhsA')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('000', '남이 될 수 있을까', '볼빨간사춘기 & 스무살', 'Z1pGxkXyDvc')";
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table PlayListData");
            onCreate(db);
        }
    }
}
