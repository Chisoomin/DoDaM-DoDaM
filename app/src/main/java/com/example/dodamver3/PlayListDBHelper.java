package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class PlayListDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

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
        db.execSQL(PlayListSQL);

        // 감정별 노래
        // 555
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('555', 'Plastic Plants', 'Mahalia', 'YYYRSS-ePxQ')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('555', 'your text', 'Sundial', 'hoHCOymHbhM')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('555', 'STAY', 'The Kid LAROI, Justin Bieber', 'Ec7TN_11az8')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('555', 'Butter', '방탄소년단', 'WMweEpGlu_U')";
        db.execSQL(PlayListSQL);


        // 500
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('500', 'Dun Dun Dance', '오마이걸 (OH MY GIRL)','HzOjwL7IP_o')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('500', '내 손을 잡아', '아이유 (IU)', '3iM_06QeZi8')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('500', '신호등', '이무진', 'SK6Sm2Ki9tI')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('500', '안녕(Hello)', '조이(JOY)', 'lNvBbh5jDcA')";
        db.execSQL(PlayListSQL);


        // 050
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('050', '그건 니 생각이고', '장기하와 얼굴들','h28fhU-mjDA')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('050', 'New Rules', 'Dua Lipa', '4l2jpzPDtuQ')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('050', 'Salt', 'Ava Max', 'hdqjdhRep2I')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('050', '주옥같다', '고옥희', '6GZkDiEqqRY')";
        db.execSQL(PlayListSQL);

        // 005
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('005', 'There You Are', 'ZAYN','oSdLaBxde-w')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('005', 'Dazed & Confused', 'Ruel', 'kkOQ6j1btpY')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('005', 'needy', 'Ariana Grande', 'HkT0N5QXXiM')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('005', 'pov', 'Ariana Grande', '4aQmTTY6GFc')";
        db.execSQL(PlayListSQL);

        // 550
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('550', 'good 4 u', 'Olivia Rodrigo','UyshwO7p7jw')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('550', 'Call me Cruella', 'Florence + the Machine', 'ljBZZmnFw_M')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('550', 'Next Level', 'aespa', 'ljBZZmnFw_M')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('550', 'Pretty Savage', 'BLACKPINK', 'F8c8f2nK82w')";
        db.execSQL(PlayListSQL);

        // 505
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('505', 'Feel Special', 'TWICE (트와이스)','3ymwOvzhwHs')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('505', 'Airplane', 'f(x)', 'TPzN__A7yeg')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('505', 'Day 1 ◑', 'HONNE', 'YUoUtJBxtxA')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('505', 'always, i''ll care', 'Jeremy Zucker', '5Ywlkp0NkKk')";
        db.execSQL(PlayListSQL);

        // 055
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('055', 'Ride', 'Lolo Zouaï','AMGU7ZzZK1o')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('055', 'W (Feat. Gunna)', 'Koffee', 'X0Nn1t9INOs')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('055', 'Savage(Nightcore Remix)', 'Bahari', 'rH28_ZanNeI')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('055', 'INFERNO', 'Sub Urban & Bella Poarch', '2qKfebB-XDc')";
        db.execSQL(PlayListSQL);

        // 000
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('000', '금요일에 만나요 (Feat. 장이정 of HISTORY)', '아이유 (IU)','EiVmQZwJhsA')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('000', '남이 될 수 있을까', '볼빨간사춘기 & 스무살', 'Z1pGxkXyDvc')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('000', '또 새벽이 오면(Feat. 백현(BAEKHYUN))', 'Colde(콜드)', 'FIW8bAjcmVk')";
        db.execSQL(PlayListSQL);
        PlayListSQL = "insert into PlayListData(mood, music, artist, videoId) values('000', 'Autumn Breeze (Feat. Rachel Lim)', 'JIDA(지다)', 'EzQsoZYY470')";
        db.execSQL(PlayListSQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table PlayListData");
            onCreate(db);
        }
    }
}
