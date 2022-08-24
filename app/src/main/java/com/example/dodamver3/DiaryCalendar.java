package com.example.dodamver3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class DiaryCalendar extends AppCompatActivity {
    // 1
    TextView date; // 날짜 표시
    String datedata; // 날짜 값
    String beforeDiaryContent;

    EditText diaryContent; // 일기 쓰기
    SeekBar happySeekBar, badSeekBar, sadSeekBar; // 감정
    ImageButton delete, save, goBack; // 버튼 세 개
    int happyVal = 5, badVal = 5, sadVal = 5; // 감정 값
    String content;

    String dbDate;
    String dateS;
    ImageButton question;
    LinearLayout emotion;
    LinearLayout setting;
    String clientId = "9k7yq1sy4c";             // Application Client ID";
    String clientSecret = "VNLTV1AKiGNCz6SuJMHiVcd0EWbvntrVcwCmG9Pl";     // Application Client Secret";
    /*
       구현 남은 것 메모
       1. diarycontent 삭제, 수정, 전으로 돌아가는 버튼 기능 구현
       2. seekbar 값에 따른 스탬프 DB에 저장
    */
    String response;
    NegativeHandler negativeHandler;
    ContentValues values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_calendar);

        date = (TextView) findViewById(R.id.date);
        diaryContent = (EditText) findViewById(R.id.diaryContent);
        question = findViewById(R.id.question_reverse);
        emotion = findViewById(R.id.emotion);
        setting = findViewById(R.id.setting);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowIntro("일기 쓰는 날짜", "일기를 쓰는 날짜를 확인할 수 있어요.", date, 1);
            }
        });


        Date listCurrentTime = Calendar.getInstance().getTime();
        String listDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(listCurrentTime);

        happySeekBar = (SeekBar) findViewById(R.id.happySeekBar);
        badSeekBar = (SeekBar) findViewById(R.id.badSeekBar);
        sadSeekBar = (SeekBar) findViewById(R.id.sadSeekBar);


        save = (ImageButton) findViewById(R.id.saveButton);
        delete = (ImageButton) findViewById(R.id.deleteButton);
        goBack = (ImageButton) findViewById(R.id.goBackButton);

        DiaryDBHelper helper = new DiaryDBHelper(this);
        final SQLiteDatabase diaryDBW = helper.getWritableDatabase();
        // PlayListDBHelper playListDBHelper = new PlayListDBHelper( this );
        // final SQLiteDatabase playListDB = helper.getWritableDatabase();
        DiaryDBHelper dbHelper = new DiaryDBHelper(this);
        final SQLiteDatabase diaryDBR = dbHelper.getReadableDatabase();

        final Intent intent = getIntent();
        datedata = intent.getExtras().getString("date");
        dbDate = intent.getExtras().getString("dbDate");
        if (datedata == null) {
            String date_text = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(listCurrentTime);
            date.setText(date_text + ".");
            dbDate = date_text;
        } else {
            date.setText(datedata + ".");
        }

        // 일기 쓰는 창으로 들어가면 전에 쓰던 일기 나오게 하기
        Cursor cursor = diaryDBR.rawQuery("select date, content, happy, bad, sad from DiaryData;", null);

        while (cursor.moveToNext()) {
            if (cursor.getString(1) != null && cursor.getString(0).equals(dbDate)) {
                beforeDiaryContent = cursor.getString(1);
                diaryContent.setText(beforeDiaryContent);
            }
        }

        // 기쁨 seekBar를 움직였을 때 바뀌는 리스너
        happySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                happyVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });

        // 화남 seekBar를 움직였을 때 바뀌는 리스너
        badSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                badVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });

        // 슬픔 seekBar를 움직였을 때 바뀌는 리스너
        sadSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sadVal = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });

        // 저장 버튼 눌렀을 때
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = diaryContent.getText().toString();
                //NegativeThread thread = new NegativeThread();
                Thread thread = new Thread(new Runnable() {

                    int negative = 0;

                    @Override
                    public void run() {
                        try {
                            String apiURL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
                            URL url = new URL(apiURL);
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
                            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
                            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
                            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
                            con.setRequestMethod("POST");

                            //json으로 message를 전달하고자 할 때
                            con.setRequestProperty("Content-Type", "application/json");
                            con.setDoInput(true);
                            con.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
                            con.setUseCaches(false);
                            con.setDefaultUseCaches(false);
                            //String content = "기분이 너무너무 별로다. 그치만 행복했다. 죽고싶긴하다.";
                            String requestBody = "{\"content\":\"'" + diaryContent.getText().toString().replace("\n", " ") + "'\"}";
                            //이런 방식 ok, 불러오기..?
                            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                            wr.write(requestBody); //json 형식의 message 전달
                            wr.flush();


                            StringBuilder sb = new StringBuilder();

                            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                //Stream을 처리해줘야 하는 귀찮음이 있음.
                                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                                String line;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line).append("\n");
                                }
                                br.close();
                                System.out.println("------------E---------" + sb.toString());

                                response = sb.toString();



                                JSONArray jsonArray = null;
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    jsonArray = jsonObject.getJSONArray("sentences");
                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject object = jsonArray.getJSONObject(i);
                                        String senti = object.getString("sentiment");

                                        //Toast.makeText(getContext(), senti, Toast.LENGTH_SHORT).show();
                                        System.out.println(senti + "");
                                        if (senti.equals("negative")) {
                                            negative++;
                                            System.out.println(senti + " !!!!!!!!!! " + negative);
                                        }

                                    }
                                    Message message = negativeHandler.obtainMessage();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("negative", negative);
                                    bundle.putString("dbDate", dbDate);
                                    message.setData(bundle);

                                    negativeHandler.sendMessage(message);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                System.out.println("---eeeeee---" + con.getResponseMessage());

                            }
                        } catch (
                                Exception e) {
                            e.printStackTrace();
                            System.err.println("-------------" + e.toString());

                        }


                    }

                });
                thread.start();

                values = new ContentValues();
                values.put("happy", happyVal);
                values.put("bad", badVal);
                values.put("sad", sadVal);
                values.put("content", content);
                values.put("date", dbDate);


                diaryDBW.insert("DiaryData", null, values);
                diaryDBW.close();

                Intent intent = new Intent(getApplicationContext(), PlayList.class);
                startActivity(intent);




                /*
                content = diaryContent.getText().toString();
                ContentValues values = new ContentValues();

                // 날짜 비교
                Cursor res = db.rawQuery( "SELECT '" + dbDate + "' FROM DiaryData WHERE date", null );
                while (res.moveToNext()) {
                    dateS = res.getString( 0 );

                    // 일기 DB에 넣기, 일기 쓰는 날짜에 해당하는 값이 DB에 있을 경우 업데이트
                    if (dateS == dbDate) {
                        values.put( "happy", happyVal );
                        values.put( "bad", badVal );
                        values.put( "sad", sadVal );
                        values.put( "content", content );
                        values.put( "date", dbDate );
                        diaryDB.insert( "DiaryData", null, values );
                        diaryDB.close();
                    } else {
                        String query = "UPDATE DiaryData SET happy = '" + happyVal + "', bad = '" + badVal + "', sad = '" + sadVal + "', content = '" + content + "', date = '" + dbDate + "' WHERE date = '" + dbDate + "'";
                        diaryDB.execSQL( query );
                        diaryDB.close();
                    }
                }
                */
            }
        });
        negativeHandler = new NegativeHandler();

        // 취소 버튼 눌렀을 때
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiaryCalendar.this);
                builder.setMessage("일기 작성을 취소합니다");
                builder.setTitle("종료 알림창")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.setTitle("취소");
                alert.show();
            }
        });

        // 삭제 버튼 눌렀을 때
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiaryCalendar.this);
                builder.setMessage("일기를 삭제합니다");
                builder.setTitle("삭제 알림창")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // 일기 DB에서 삭제하기
                                String query = "DELETE FROM DiaryData WHERE date = '" + dbDate + "'";
                                diaryDBW.execSQL(query);
                                diaryDBW.close();
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.setTitle("삭제");
                alert.show();
            }
        });
    }

    private void ShowIntro(String title, String text, View widget, final int type) {

        new GuideView.Builder(DiaryCalendar.this)
                .setTitle(title)
                .setContentText(text)
                .setTargetView(widget)
                .setContentTextSize(12)//optional
                .setTitleTextSize(14)//optional
                .setDismissType(DismissType.anywhere) //optional - default dismissible by TargetView
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        if (type == 1) {
                            ShowIntro("일기 입력", "이곳에 일기를 입력해 주세요.", diaryContent, 2);
                        } else if (type == 2) {
                            ShowIntro("감정 입력", "오늘 느낀 기쁨, 분노, 슬픔의 감정을 새싹을 옮겨 0-10까지 수로 표현해 주세요.", emotion, 3);
                        } else if (type == 3) {
                            ShowIntro("감정 일기 입력", "이렇게 쓴 일기를 저장, 수정, 삭제할 수 있어요.", setting, 4);
                        } else if (type == 4) {

                        }
                    }
                })
                .build()
                .show();
    }

    class NegativeThread extends Thread {
        int negative = 0;

        @Override
        public void run() {
            try {
                String apiURL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
                con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
                con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
                con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
                con.setRequestMethod("POST");

                //json으로 message를 전달하고자 할 때
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoInput(true);
                con.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
                con.setUseCaches(false);
                con.setDefaultUseCaches(false);
                //String content = "기분이 너무너무 별로다. 그치만 행복했다. 죽고싶긴하다.";
                String requestBody = "{\"content\":\"'" + diaryContent.getText().toString() + "'\"}";
                //이런 방식 ok, 불러오기..?
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(requestBody); //json 형식의 message 전달
                wr.flush();

                StringBuilder sb = new StringBuilder();

                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //Stream을 처리해줘야 하는 귀찮음이 있음.
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), "utf-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    System.out.println("----------------------" + sb.toString());
                    response = sb.toString();


                    JSONArray jsonArray = null;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        jsonArray = jsonObject.getJSONArray("sentences");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            String senti = object.getString("sentiment");

                            //Toast.makeText(getContext(), senti, Toast.LENGTH_SHORT).show();
                            System.out.println(senti + "");
                            if (senti.equals("negative")) {
                                negative++;
                                System.out.println(senti + " !!!!!!!!!! " + negative);
                            }

                        }
                        Message message = negativeHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putInt("negative", negative);
                        message.setData(bundle);

                        negativeHandler.sendMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("----------------------" + con.getResponseMessage());
                }
            } catch (
                    Exception e) {
                e.printStackTrace();
                System.err.println("-------------" + e.toString());
            }


        }
    }

    class NegativeHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            DiaryDBHelper helper = new DiaryDBHelper(DiaryCalendar.this);
            SQLiteDatabase diaryDBUP = helper.getWritableDatabase();

            Bundle bundle = msg.getData();
            int negative = bundle.getInt("negative");
            String date = bundle.getString("dbDate");
            System.out.println("이거 negative 값임 받아온거 : " + negative);
            String query = "UPDATE DiaryData SET negative = '" + negative + "' WHERE date = '" + date + "'";
            diaryDBUP.execSQL(query);
            diaryDBUP.close();
            finish();
        }

    }
}

        /*
        happySeekBar = (SeekBar) findViewById( R.id.happySeekBar );
        badSeekBar = (SeekBar) findViewById( R.id.badSeekBar );
        sadSeekBar = (SeekBar) findViewById( R.id.sadSeekBar );

        diaryContent = (EditText)findViewById(R.id.diaryContent);

        saveButton = (Button) findViewById( R.id.saveButton );


        DiaryDBHelper helper = new DiaryDBHelper(this);
        final SQLiteDatabase diaryDB = helper.getWritableDatabase();


        // 3
        // 기쁨 seekBar를 움직였을 때 바뀌는 리스너
        happySeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                happyPercent.setText( String.valueOf( progress ) );
                happyVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        } );

        // 화남 seekBar를 움직였을 때 바뀌는 리스너
        badSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                badPercent.setText( String.valueOf( progress ) );
                badVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        } );

        // 슬픔 seekBar를 움직였을 때 바뀌는 리스너
        sadSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sadPercent.setText( String.valueOf( progress ) );
                sadVal = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        } );



        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = diaryContent.getText().toString();
                ContentValues values = new ContentValues();
                values.put("happy", happyVal);
                values.put("bad", badVal);
                values.put("sad", sadVal);
                values.put("content", content);
                diaryDB.insert("DiaryData", null, values);

                diaryDB.close();

                Intent intent = new Intent(getApplicationContext(), PlayList.class);
                startActivity(intent);
            }
        } );
    }
}*/