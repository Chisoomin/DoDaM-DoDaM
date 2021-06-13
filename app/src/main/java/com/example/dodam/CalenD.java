package com.example.dodam;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalenD extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    public static int SUNDAY = 1;
    public static int MONDAY = 2;
    public static int TUESDAY = 3;
    public static int WEDNSESDAY = 4;
    public static int THURSDAY = 5;
    public static int FRIDAY = 6;
    public static int SATURDAY = 7;

    private TextView mTvCalendarTitle;
    private GridView mGvCalendar;

    private ArrayList<DayInfo> mDayList;
    private CalendarAdapter mCalendarAdapter;

    Calendar mLastMonthCalendar;
    Calendar mThisMonthCalendar;
    Calendar mNextMonthCalendar;

    TextView diary;
    ImageView stamp;

    String date; // 선택된 날짜
    String dbDate;
    String month;
    String day;

    int happyInt, badInt, sadInt;

    LinearLayout diaryContainer;


    /*
        구현 남은 것 메모
        1. DB에 일기가 저장된 경우 circle visible로 바꾸기 + 가능하면 색깔도 설정
        2. 버튼을 눌렀을 경우 해당 날짜의 일기가 나오도록 하기

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ImageButton bLastMonth = (ImageButton) findViewById(R.id.mleft);
        ImageButton bNextMonth = (ImageButton) findViewById(R.id.mright);

        mTvCalendarTitle = (TextView) findViewById(R.id.year);
        mGvCalendar = (GridView) findViewById(R.id.calendar_grid);

        diary = (TextView) findViewById(R.id.diary);
        stamp = (ImageView) findViewById(R.id.stamp);

        bLastMonth.setOnClickListener(this);
        bNextMonth.setOnClickListener(this);
        mGvCalendar.setOnItemClickListener(this);

        mDayList = new ArrayList<DayInfo>();


        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아래 일기가 눌렸을 때

                Intent intent = new Intent(getApplicationContext(), DiaryCalendar.class);
                intent.putExtra("date", date);
                intent.putExtra("dbDate", dbDate);
                startActivity(intent);


            }
        });


        diary.setText("작성된 글이 없습니다. \n터치해서 일기 쓰기  ");
        stamp.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 이번달 의 캘린더 인스턴스를 생성한다.
        mThisMonthCalendar = Calendar.getInstance();
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonthCalendar);
    }

    private void getCalendar(Calendar calendar) {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;

        mDayList.clear();

        // 이번달 시작일의 요일을 구한다. 시작일이 일요일인 경우 인덱스를 1(일요일)에서 8(다음주 일요일)로 바꾼다.)
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);
        Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH) + "");

        // 지난달의 마지막 일자를 구한다.
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, 1);
        Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH) + "");

        if (dayOfMonth == SUNDAY) {
            dayOfMonth += 7;
        }

        lastMonthStartDay -= (dayOfMonth - 1) - 1;


        // 캘린더 타이틀(년월 표시)을 세팅한다.
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "."
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1));

        DayInfo day;

        Log.e("DayOfMOnth", dayOfMonth + "");


        for (int i = 0; i < dayOfMonth - 1; i++) {
            int date = lastMonthStartDay + i;
            day = new DayInfo();
            day.setDay(Integer.toString(date));
            day.setInMonth(false);

            mDayList.add(day);
        }
        for (int i = 1; i <= thisMonthLastDay; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(true);

            mDayList.add(day);
        }
        for (int i = 1; i < 42 - (thisMonthLastDay + dayOfMonth - 1) + 1; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(false);
            mDayList.add(day);

        }

        initCalendarAdapter();
    }

    private Calendar getLastMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + ". "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1));
        return calendar;
    }

    private Calendar getNextMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + ". "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1));
        return calendar;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {

        for (int i = 0; i < parent.getChildCount(); i++) {
            parent.getChildAt(i).findViewById(R.id.flag).setVisibility(View.INVISIBLE);

        }

        v.findViewById(R.id.flag).setVisibility(View.VISIBLE);

        if ((mThisMonthCalendar.get(Calendar.MONTH) + 1) <= 9 && (mThisMonthCalendar.get(Calendar.MONTH) + 1) >= 1) {
            month = "0" + (mThisMonthCalendar.get(Calendar.MONTH) + 1);
        } else {//if ((mThisMonthCalendar.get(Calendar.MONTH) + 1) >= 10 && (mThisMonthCalendar.get(Calendar.MONTH) + 1) <= 12) {
            month = "" + (mThisMonthCalendar.get(Calendar.MONTH) + 1);
        }


        if ((Integer.parseInt(String.valueOf(mDayList.get(position).getDay()))) <= 9 && (Integer.parseInt(String.valueOf(mDayList.get(position).getDay())) >= 1)) {
            day = "0" + mDayList.get(position).getDay();
        } else {//if ((Integer.parseInt(String.valueOf(mDayList.get(position).getDay()))) >= 10 && (Integer.parseInt(String.valueOf(mDayList.get(position).getDay())) <= 31)) {
            day = "" + (Integer.parseInt(String.valueOf(mDayList.get(position).getDay())));
        }

        date = mThisMonthCalendar.get(Calendar.YEAR) + ". " +
                month + ". " +
                day;

        dbDate = mThisMonthCalendar.get(Calendar.YEAR) + "-" +
                month + "-" +
                day;

        //일기 불러오기

        //DB 영역
        DiaryDBHelper dbHelper = new DiaryDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select date, content, happy, bad, sad from DiaryData;", null);


        //감정 스탬프 받는 중


        diary.setText("작성된 글이 없습니다. \n터치해서 일기 쓰기  ");
        stamp.setVisibility(View.INVISIBLE);

        while (cursor.moveToNext()) {

            if (cursor.getString(0).equals(dbDate)) {
                diary.setText(cursor.getString(1));
                happyInt = cursor.getInt(2);
                badInt = cursor.getInt(3);
                sadInt = cursor.getInt(4);

                //스탬프 감정 변경
                if (happyInt == badInt && badInt == sadInt && happyInt != 0) // 감정 폭발(세개 다)
                    stamp.setImageResource(R.drawable.stamp_full_feel);
                if (happyInt > badInt && happyInt > sadInt) // 기쁨
                    stamp.setImageResource(R.drawable.stamp_happy);
                if (badInt > happyInt && badInt > sadInt) // 화남
                    stamp.setImageResource(R.drawable.stamp_angry);
                if (sadInt > badInt && sadInt > happyInt) //슬픔
                    stamp.setImageResource(R.drawable.stamp_sad);
                if (happyInt == badInt && happyInt > sadInt) // 기쁨+화남
                    stamp.setImageResource(R.drawable.stamp_happy_angry);
                if (happyInt == sadInt && happyInt > badInt) // 기쁨+슬픔
                    stamp.setImageResource(R.drawable.stamp_sad_happy);
                if (badInt == sadInt && badInt > happyInt) // 슬픔+화남
                    stamp.setImageResource(R.drawable.stamp_sad_angry);
                if (happyInt == badInt && badInt == 0 && sadInt == 0) // 감정 000
                    stamp.setImageResource(R.drawable.stamp_no_feel);
                //스탬프 끝
                stamp.setVisibility(View.VISIBLE);
            }


        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mleft:
                mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
            case R.id.mright:
                mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
        }
    }

    private void initCalendarAdapter() {
        mCalendarAdapter = new CalendarAdapter(this, R.layout.item_view_calendar, mDayList);
        mGvCalendar.setAdapter(mCalendarAdapter);
    }
}



