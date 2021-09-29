package com.example.dodamver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

public class Psychological_Test extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<test_getset> list = new ArrayList<test_getset>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_test);

        recyclerView = findViewById(R.id.recyclerView);

        TestQuesDB testQuesDB = new TestQuesDB(this);
        SQLiteDatabase db = testQuesDB.getReadableDatabase();

        Cursor cursor = db.rawQuery("select questions from TestQues;", null);


        while(cursor.moveToNext()){
            list.add(new test_getset(cursor.getString(0)));
        }
        Log.e("리스트 보여주세요", String.valueOf(list));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PsyTestAdapter(list));

        db.close();

    }
}