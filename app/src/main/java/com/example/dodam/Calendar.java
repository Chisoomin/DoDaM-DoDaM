package com.example.dodam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {
    // 1
    CalendarView calendarView;
    TextView dateTextView, diaryTextView;
    Button seeCalButton; // 버튼을 안보이게 하는게 나을지 아니면 레이아웃을 안보이게 하는게 나을지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // 2
        calendarView = (CalendarView) findViewById( R.id.calendarView );
        dateTextView = (TextView) findViewById( R.id.dateTextView );
        diaryTextView = (TextView) findViewById( R.id.diaryTextView );
        seeCalButton = (Button) findViewById( R.id.seeCalButton );

        dateTextView.setVisibility( View.INVISIBLE );
        diaryTextView.setVisibility( View.INVISIBLE );
        seeCalButton.setVisibility( View.INVISIBLE );

        // 3
        // 캘린더뷰 날짜 눌렀을 때
        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, final int year, final int month, final int dayOfMonth) {
                // 대화상자
                final String[] listArray = new String[] {"일기 보기", "일기 쓰기", "일기 수정하기", "일기 삭제하기"};
                AlertDialog.Builder dialog = new AlertDialog.Builder( Calendar.this );
                dialog.setTitle( "나의 일기" );
                dialog.setItems( listArray,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (listArray[which].equals( "일기 보기" )) {
                                    calendarView.setVisibility( View.GONE );
                                    dateTextView.setText(year + "년 " + (month+1) + "월 " + dayOfMonth + "일");
                                    dateTextView.setVisibility( View.VISIBLE );
                                    diaryTextView.setVisibility( View.VISIBLE );
                                    seeCalButton.setVisibility( View.VISIBLE );
                                }

                                if (listArray[which].equals( "일기 쓰기" )) {
                                    Intent intent = new Intent(getApplicationContext(), DiaryCalendar.class);
                                    startActivity(intent);
                                }

                                if (listArray[which].equals( "일기 수정하기" )) {
                                    Intent intent = new Intent(getApplicationContext(), DiaryCalendarMod.class);
                                    startActivity(intent);
                                }

                                if (listArray[which].equals( "일기 삭제하기" )) {
                                    /*
                                    AlertDialog.Builder builder = new AlertDialog.Builder( getApplicationContext());
                                    builder.setTitle( "일기 삭제하기" );
                                    builder.setMessage( "일기를 삭제하시겠습니까?" );
                                    builder.setPositiveButton( "삭제",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // 일기를 삭제하는 코드
                                                }
                                            } );
                                    builder.setNegativeButton( "취소",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // 일기 삭제를 취소하는 코드
                                                }
                                            } );
                                    builder.show();
                                    */
                                }
                            }
                        } );
                // dialog.setPositiveButton( "확인",null );
                dialog.show();
            }
        } );

        // 달력보기 버튼 눌렀을 때
        seeCalButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility( View.VISIBLE );
                dateTextView.setVisibility( View.INVISIBLE );
                diaryTextView.setVisibility( View.INVISIBLE );
                seeCalButton.setVisibility( View.INVISIBLE );
            }
        } );
    }
}