package com.example.dodamver2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class TestQuesDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public TestQuesDB(@Nullable Context context) {
        super(context, "mydb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String testSQL = "create table TestQues(" +
                "_id integer primary key autoincrement," +
                "questions text, " + // 질문
                "type text, " + // 질문 타입
                "answer text)";  // 대답

        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('1. 식욕이 없었다.', 'Appetite', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('2. 울적한 기분을 떨쳐 버릴 수 없었다.', 'Sadness', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('3. 무슨 일을 하든 정신을 집중하기가 힘들었다.', 'Thinking / concentration', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('4. 상당히 우울했다.', 'Sadness', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('5. 잠을 설쳤다(잠을 잘 이루지 못했다).', 'Sleep', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('6. 마음이 슬펐다.', 'Sadness', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('7. 도무지 뭘 해 나갈 엄두가 나지 않았다.', 'Tired', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('8. 나를 행복하게 하는 것은 아무것도 없었다.', 'Loss of Interest', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('9. 내가 나쁜 사람처럼 느껴졌다.', 'Guilt', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('10. 일상 활동에 대한 흥미를 잃었다.', 'Loss of Interest', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('11. 평소보다 훨씬 더 많이 잤다.', 'Sleep', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('12. 내 움직임이 너무 둔해진 것처럼 느껴졌다.', 'Movement', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('13. 안절부절 못했다.', 'Movement', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('14. 죽었으면 하고 바랬다.', 'Suicidal ideation', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('15. 자해하고 싶었다.', 'Suicidal ideation', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('16. 항상 피곤했다.', 'Tired', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('17. 나 자신이 싫었다.', 'Guilt', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('18. (살을 빼려고) 노력하지 않았는데, 몸무게가 많이 줄었다.', 'Appetite', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('19. 잠들기가 많이 힘들었다.', 'Sleep', null)";
        db.execSQL(testSQL);
        testSQL = "insert into TestQues(questions, type, answer) values('20. 중요한 일에 집중할 수가 없었다.', 'Thinking / concentration', null)";
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