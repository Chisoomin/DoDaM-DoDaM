package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReadDBActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readdb);

        setTitle("db 전체 보기");

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select name, type, pass, passHint, passHintAns, birthday from Dodam;", null);

        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        while(cursor.moveToNext()){
            TextView textView = new TextView(this);
            textView.setTextSize(20);
            textView.setText("이름 : " + cursor.getString(0) + " 성별 : " + cursor.getString(1)+ " 비밀번호 : " + cursor.getString(2)+ " 비밀번호 힌트 : " + cursor.getString(3)+ " 비밀번호 힌트 답 : " + cursor.getString(4) + "\n");
            linearLayout.addView(textView);
        }

        db.close();
    }
}
