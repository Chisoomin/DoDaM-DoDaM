package com.example.dodamver3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    /*
       구현 남은 것 메모
       1. diarycontent 삭제, 수정, 전으로 돌아가는 버튼 기능 구현
       2. seekbar 값에 따른 스탬프 DB에 저장
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_calendar);

        date = (TextView) findViewById( R.id.date );
        diaryContent = (EditText) findViewById( R.id.diaryContent );


        Date listCurrentTime = Calendar.getInstance().getTime();
        String listDate = new SimpleDateFormat( "yyyy-MM-dd", Locale.getDefault() ).format( listCurrentTime );

        happySeekBar = (SeekBar) findViewById( R.id.happySeekBar );
        badSeekBar = (SeekBar) findViewById( R.id.badSeekBar );
        sadSeekBar = (SeekBar) findViewById( R.id.sadSeekBar );


        save = (ImageButton) findViewById( R.id.saveButton );
        delete = (ImageButton) findViewById( R.id.deleteButton );
        goBack = (ImageButton) findViewById( R.id.goBackButton );

        DiaryDBHelper helper = new DiaryDBHelper( this );
        final SQLiteDatabase diaryDBW = helper.getWritableDatabase();
        // PlayListDBHelper playListDBHelper = new PlayListDBHelper( this );
        // final SQLiteDatabase playListDB = helper.getWritableDatabase();
        DiaryDBHelper dbHelper = new DiaryDBHelper( this );
        final SQLiteDatabase diaryDBR = dbHelper.getReadableDatabase();

        final Intent intent = getIntent();
        datedata = intent.getExtras().getString( "date" );
        dbDate = intent.getExtras().getString( "dbDate" );
        if (datedata == null) {
            String date_text = new SimpleDateFormat( "yyyy.MM.dd", Locale.getDefault() ).format( listCurrentTime );
            date.setText( date_text + "." );
            dbDate = date_text;
        } else {
            date.setText( datedata + "." );
        }

        // 일기 쓰는 창으로 들어가면 전에 쓰던 일기 나오게 하기
        Cursor cursor = diaryDBR.rawQuery( "select date, content, happy, bad, sad from DiaryData;", null );

        while (cursor.moveToNext()) {
            if (cursor.getString( 1 ) != null && cursor.getString( 0 ).equals( dbDate )) {
                beforeDiaryContent = cursor.getString( 1 );
                diaryContent.setText( beforeDiaryContent );
            }
        }

        // 기쁨 seekBar를 움직였을 때 바뀌는 리스너
        happySeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
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
        } );

        // 화남 seekBar를 움직였을 때 바뀌는 리스너
        badSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
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
        } );

        // 슬픔 seekBar를 움직였을 때 바뀌는 리스너
        sadSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
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
        } );

        // 저장 버튼 눌렀을 때
        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = diaryContent.getText().toString();
                ContentValues values = new ContentValues();
                values.put( "happy", happyVal );
                values.put( "bad", badVal );
                values.put( "sad", sadVal );
                values.put( "content", content );
                values.put( "date", dbDate );
                diaryDBW.insert( "DiaryData", null, values );
                diaryDBW.close();

                Intent intent = new Intent( getApplicationContext(), PlayList.class );
                startActivity( intent );

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
        } );

        // 취소 버튼 눌렀을 때
        goBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder( DiaryCalendar.this );
                builder.setMessage( "일기 작성을 취소합니다" );
                builder.setTitle( "종료 알림창" )
                        .setCancelable( true )
                        .setPositiveButton( "확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        } )
                        .setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        } );

                AlertDialog alert = builder.create();
                alert.setTitle( "취소" );
                alert.show();
            }
        } );

        // 삭제 버튼 눌렀을 때
        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder( DiaryCalendar.this );
                builder.setMessage( "일기를 삭제합니다" );
                builder.setTitle( "삭제 알림창" )
                        .setCancelable( true )
                        .setPositiveButton( "확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // 일기 DB에서 삭제하기
                                String query = "DELETE FROM DiaryData WHERE date = '" + dbDate + "'";
                                diaryDBW.execSQL( query );
                                diaryDBW.close();
                                finish();
                            }
                        } )
                        .setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        } );

                AlertDialog alert = builder.create();
                alert.setTitle( "삭제" );
                alert.show();
            }
        } );
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