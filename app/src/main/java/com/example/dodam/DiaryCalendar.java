package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

    TextView diaryContent;

    SeekBar happySeekBar, badSeekBar, sadSeekBar;

    ImageButton delete, save, goBack;

     /*
        구현 남은 것 메모
        1. diarycontent 삭제, 수정, 전으로 돌아가는 버튼 기능 구현
        2. seekbar 값에 따른 스탬프 DB에 저장
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_diary_calendar );

        date = (TextView) findViewById(R.id.date);

        Intent intent = getIntent();
        datedata = intent.getExtras().getString("date");
        date.setText(datedata+".");

        goBack = (ImageButton)findViewById(R.id.goback);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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