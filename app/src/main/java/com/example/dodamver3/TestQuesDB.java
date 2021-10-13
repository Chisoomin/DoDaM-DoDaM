package com.example.dodamver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class TestQuesDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public TestQuesDB(@Nullable Context context) {
        super(context, "testDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String testSQL = "create table TestQues(" +
                "_id integer primary key autoincrement," +
                "ques_numid test, " + //질문 아이디
                "questions text, " + // 질문
                "type text)";  // 질문 타입

        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('1', '평소에는 아무렇지도 않던 일들이 괴롭고 귀찮게 느껴졌다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('2', '먹고 싶지 않고 식욕이 없었다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('3', '어느 누가 도와준다 하더라도 나의 울적한 기분을 떨쳐버릴 수 없을 것 같았다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('4', '무슨 일을 하든 정신을 집중하기가 힘들었다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('5', '비교적 잘 지냈다.', 'positive')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('6', '상당히 우울했다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('7', '모든 일들이 힘들게 느껴졌다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('8', '앞일이 암담하게 느껴졌다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('9', '지금까지의 내 인생은 실패작이라는 생각이 들었다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('10', '적어도 보통 사람들만큼의 능력은 있었다고 생각한다.', 'positive')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('11', '잠을 설쳤다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('12', '두려움을 느꼈다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('13', '평소에 비해 말수가 적었다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('14', '세상에 홀로 있는 듯한 외로움을 느꼈다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('15', '큰 불만 없이 생활했다.', 'positive')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('16', '사람들이 나에게 차갑게 대하는 것 같았다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('17', '갑자기 울음이 나왔다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('18', '마음이 슬펐다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('19', '사람들이 나를 싫어하는 것 같았다.', 'negative')";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(ques_numid, questions, type) values('20', '도무지 무엇을 해 나갈 엄두가 나지 않았다.', 'negative')";
        db.execSQL(testSQL);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table TestQues");
            onCreate(db);
        }
    }
}