package com.example.dodamver3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Psychological_Test extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<test_getset> list = new ArrayList<test_getset>();
    Button result_btn;
    PsyTestAdapter psyTestAdapter;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    String tsave;
    int i = 0;
    static int first = 0;
    TextView result_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_test);

        ActionBar actionBar = getSupportActionBar();
      //  actionBar.hide();

        recyclerView = findViewById(R.id.recyclerView);
        result_btn = findViewById(R.id.result);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        String date = mFormat.format(mDate);

        psyTestAdapter = new PsyTestAdapter(getApplicationContext());
        recyclerView.setAdapter(psyTestAdapter);

        TestQuesDB testQuesDB = new TestQuesDB(this);
        SQLiteDatabase db = testQuesDB.getReadableDatabase();

        Cursor cursor = db.rawQuery("select ques_numid, questions from TestQues;", null);


        while (cursor.moveToNext()) {
            test_getset test_gs = new test_getset(cursor.getString(0), cursor.getString(1));
            psyTestAdapter.addItem(test_gs);
            //list.add(new test_getset(cursor.getString(0), cursor.getString(1)));
        }
        Log.e("리스트 보여주세요", String.valueOf(list));

        TestResultDB testResultDB = new TestResultDB(Psychological_Test.this);
        SQLiteDatabase db_insert = testResultDB.getWritableDatabase();
        if (first == 0) {
            first++;
            String query1 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "1" + "', '" + "0" + "')";
            db_insert.execSQL(query1);
            String query2 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "2" + "', '" + "0" + "')";
            db_insert.execSQL(query2);
            String query3 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "3" + "', '" + "0" + "')";
            db_insert.execSQL(query3);
            String query4 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "4" + "', '" + "0" + "')";
            db_insert.execSQL(query4);
            String query5 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "5" + "', '" + "0" + "')";
            db_insert.execSQL(query5);
            String query6 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "6" + "', '" + "0" + "')";
            db_insert.execSQL(query6);
            String query7 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "7" + "', '" + "0" + "')";
            db_insert.execSQL(query7);
            String query8 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "8" + "', '" + "0" + "')";
            db_insert.execSQL(query8);
            String query9 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "9" + "', '" + "0" + "')";
            db_insert.execSQL(query9);
            String query10 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "10" + "', '" + "0" + "')";
            db_insert.execSQL(query10);
            String query11 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "11" + "', '" + "0" + "')";
            db_insert.execSQL(query11);
            String query12 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "12" + "', '" + "0" + "')";
            db_insert.execSQL(query12);
            String query13 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "13" + "', '" + "0" + "')";
            db_insert.execSQL(query13);
            String query14 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "14" + "', '" + "0" + "')";
            db_insert.execSQL(query14);
            String query15 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "15" + "', '" + "0" + "')";
            db_insert.execSQL(query15);
            String query16 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "16" + "', '" + "0" + "')";
            db_insert.execSQL(query16);
            String query17 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "17" + "', '" + "0" + "')";
            db_insert.execSQL(query17);
            String query18 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "18" + "', '" + "0" + "')";
            db_insert.execSQL(query18);
            String query19 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "19" + "', '" + "0" + "')";
            db_insert.execSQL(query19);
            String query20 = "insert into TestResult(date, ques_numid, answer) values('" + date + "', '" + "20" + "', '" + "0" + "')";
            db_insert.execSQL(query20);
        }
        db_insert.close();

        psyTestAdapter.setOncallback_answerListener(new callback_answer() {
            @Override
            public void callback_answer(String QuesId, String answer) {
                String[] ans = answer.split("/");
                for (int i = 0; i < ans.length; i++) {
                    if (ans[i].equals("true")) {
                        tsave = String.valueOf(i);
                    }
                }

                Toast.makeText(getApplicationContext(), QuesId + "번 답" + answer, Toast.LENGTH_SHORT).show();
                TestResultDB testResultDB = new TestResultDB(Psychological_Test.this);
                SQLiteDatabase db2 = testResultDB.getWritableDatabase();
                //String[] Quesid_s = new String[20];

                if (QuesId.equals("5") || QuesId.equals("10") || QuesId.equals("15")) {
                    tsave = String.valueOf(3 - Integer.parseInt(tsave));
                }
                String resultUP = "UPDATE TestResult SET answer = '" + tsave + "' WHERE date = '" + date + "'AND ques_numid = '" + QuesId + "'";
                db2.execSQL(resultUP);


                /*Cursor cursor = db_r.rawQuery("select date, ques_numid, answer from TestResult;", null);
                while (cursor.moveToNext()){
                    if(cursor.getString(0).equals(date) && cursor.getString(1).equals(QuesId)){
                        Quesid_s[i] = QuesId;
                        i++;
                    }
                }
                for(int j =0;j<i;j++){
                    String id = Quesid_s[j];

                }*/

                db2.close();
            }
        });

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(new PsyTestAdapter(getApplicationContext()));

        db.close();

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestResultDB testResultDB = new TestResultDB(Psychological_Test.this);
                SQLiteDatabase db_r = testResultDB.getReadableDatabase();
                Cursor cursor = db_r.rawQuery("select date, answer from TestResult;", null);
                Integer score = 0;
                String score_string = null;
                while (cursor.moveToNext()) {
                    if (cursor.getString(0).equals(date)) {
                        score += Integer.parseInt(cursor.getString(1));
                    }
                }
                if(score<16){
                    score_string = "정상";
                }else if(score>=16&&score<=20){
                    score_string = "약간의 우울감";
                }else if(score>=21&&score<=24){
                    score_string = "심한 우울감";
                }else if(score>=25){
                    score_string = "매우 심한 우울감";
                }

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Psychological_Test.this);
                View dialogView = getLayoutInflater().inflate(R.layout.test_dialog, null);
                result_text = dialogView.findViewById(R.id.result_text);
                result_text.setText("당신의 우울 척도 검진결과는 "+score+"점 입니다.\n결과는 "+score_string+"으로 판단됩니다.\n\n척도지 점수 및 결과, 검진기준, 그래프(검진기준 표시), 인터넷 자가검진은 간이검사이므로 진단을 대신할 수 없습니다.\n보다 자세한 사항은 정신보건전문요원 또는 전문의의 상담이 필요합니다.");
                builder.setView(dialogView);

                Integer finalScore = score;
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        graphDB graph = new graphDB(Psychological_Test.this);
                        SQLiteDatabase db_s = graph.getWritableDatabase();
                        String score_jum = String.valueOf(finalScore);
                        String query = "insert into Graph(date, score) values('" + date + "', '" + score_jum + "')";
                        db_s.execSQL(query);
                        db_s.close();
                        Intent intent = new Intent(getApplicationContext(), IntroPage.class);
                        startActivity(intent);
                    }

                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {

                    }

                });
                builder.setTitle("검사결과");
                builder.show();
                /*AlertDialog.Builder builder = new AlertDialog.Builder(Psychological_Test.this);
                builder.setTitle("결과").setMessage(score+"점 입니다.");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();*/
            }
        });

    }
}