package com.example.dodam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DiaryCalendar extends AppCompatActivity {
    // 1
    TextView date; // 날짜 표시
    String datedata; // 날짜 값

    EditText diaryContent; // 일기 쓰기
    SeekBar happySeekBar, badSeekBar, sadSeekBar; // 감정
    ImageButton delete, save, goBack; // 버튼 세 개
    int happyVal, badVal, sadVal; // 감정 값
    String content;

     /*
        구현 남은 것 메모
        1. diarycontent 삭제, 수정, 전으로 돌아가는 버튼 기능 구현
        2. seekbar 값에 따른 스탬프 DB에 저장
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_calendar);

        date = (TextView) findViewById(R.id.date);
        diaryContent = (EditText) findViewById(R.id.diaryContent);


        happySeekBar = (SeekBar) findViewById(R.id.happySeekBar);
        badSeekBar = (SeekBar) findViewById(R.id.badSeekBar);
        sadSeekBar = (SeekBar) findViewById(R.id.sadSeekBar);


        save = (ImageButton) findViewById(R.id.saveButton);
        delete = (ImageButton) findViewById(R.id.deleteButton);
        goBack = (ImageButton) findViewById(R.id.goBackButton);

        DiaryDBHelper helper = new DiaryDBHelper(this);
        final SQLiteDatabase diaryDB = helper.getWritableDatabase();
        PlayListDBHelper playListDBHelper = new PlayListDBHelper(this);
        final SQLiteDatabase playListDB = helper.getWritableDatabase();

        final Intent intent = getIntent();
        datedata = intent.getExtras().getString("date");
        date.setText(datedata + ".");

        // 기쁨 seekBar를 움직였을 때 바뀌는 리스너
        happySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //happyPercent.setText( String.valueOf( progress ) );
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
                //badPercent.setText( String.valueOf( progress ) );
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
                //  sadPercent.setText( String.valueOf( progress ) );
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


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = diaryContent.getText().toString();
                ContentValues values = new ContentValues();
                values.put("happy", happyVal);
                values.put("bad", badVal);
                values.put("sad", sadVal);
                values.put("content", content);
                diaryDB.insert("DiaryData", null, values);

                // mood 값 넘기기

                // ContentValues values1 = new ContentValues();
                Intent intent1 = new Intent(DiaryCalendar.this, PlayList.class);
                int mood = happyVal * 100 + badVal * 10 + sadVal;
                intent1.putExtra("mood", mood);
                diaryDB.close();
                startActivity(intent1);

              /* values.put("mood", mood);
               playListDB.insert("PlayListData", null, values);*/

                diaryDB.close();
                //  playListDB.close();

               /* Intent intent = new Intent(getApplicationContext(), PlayList.class);
                startActivity(intent);*/
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiaryCalendar.this);
                builder.setMessage("일기 작성을 취소할까요?");
                builder.setTitle("종료 알림창")
                        .setCancelable(true)
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });

                /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("다이얼로그 제목")        // 제목 설정
                        .setMessage("메시지")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                            // 확인 버튼 클릭시 설정, 오른쪽 버튼입니다.
                            public void onClick(DialogInterface dialog, int whichButton){
                                //원하는 클릭 이벤트를 넣으시면 됩니다.
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                            // 취소 버튼 클릭시 설정, 왼쪽 버튼입니다.
                            public void onClick(DialogInterface dialog, int whichButton){
                                //원하는 클릭 이벤트를 넣으시면 됩니다.
                            }
                        });

                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();*/

                AlertDialog alert = builder.create();
                alert.setTitle("쓰던 일기가 사라집니다.");
                alert.show();
            }
        });


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