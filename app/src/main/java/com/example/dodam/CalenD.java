package com.example.dodam;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalenD extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener
{
    public static int SUNDAY        = 1;
    public static int MONDAY        = 2;
    public static int TUESDAY       = 3;
    public static int WEDNSESDAY    = 4;
    public static int THURSDAY      = 5;
    public static int FRIDAY        = 6;
    public static int SATURDAY      = 7;

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

    /*DB 영역
    DiaryDBHelper dbHelper = new DiaryDBHelper(this);
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor = db.rawQuery("select strftime('%Y-%m-%d', date), content from DiaryData;", null);
    //DB 끝*/

    /*
        구현 남은 것 메모
        1. DB에 일기가 저장된 경우 circle visible로 바꾸기 + 가능하면 색깔도 설정
        2. 버튼을 눌렀을 경우 해당 날짜의 일기가 나오도록 하기
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ImageButton bLastMonth = (ImageButton)findViewById(R.id.mleft);
        ImageButton bNextMonth = (ImageButton)findViewById(R.id.mright);

        mTvCalendarTitle = (TextView)findViewById(R.id.year);
        mGvCalendar = (GridView)findViewById(R.id.calendar_grid);

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
                startActivity(intent);


            }
        });


    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // 이번달 의 캘린더 인스턴스를 생성한다.
        mThisMonthCalendar = Calendar.getInstance();
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonthCalendar);
    }

    private void getCalendar(Calendar calendar)
    {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;

        mDayList.clear();

        // 이번달 시작일의 요일을 구한다. 시작일이 일요일인 경우 인덱스를 1(일요일)에서 8(다음주 일요일)로 바꾼다.)
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);
        Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH)+"");

        // 지난달의 마지막 일자를 구한다.
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, 1);
        Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH)+"");

        if(dayOfMonth == SUNDAY)
        {
            dayOfMonth += 7;
        }

        lastMonthStartDay -= (dayOfMonth-1)-1;


        // 캘린더 타이틀(년월 표시)을 세팅한다.
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "."
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1));

        DayInfo day;

        Log.e("DayOfMOnth", dayOfMonth+"");


        for(int i=0; i<dayOfMonth-1; i++)
        {
            int date = lastMonthStartDay+i;
            day = new DayInfo();
            day.setDay(Integer.toString(date));
            day.setInMonth(false);

            mDayList.add(day);
        }
        for(int i=1; i <= thisMonthLastDay; i++)
        {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(true);

            mDayList.add(day);
        }
        for(int i=1; i<42-(thisMonthLastDay+dayOfMonth-1)+1; i++)
        {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(false);
            mDayList.add(day);

        }

        initCalendarAdapter();
    }

    private Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + ". "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1));
        return calendar;
    }

    private Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + ". "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1));
        return calendar;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long arg3)
    {

        for (int i = 0; i <parent.getChildCount(); i++){
            parent.getChildAt(i).findViewById(R.id.flag).setVisibility(View.INVISIBLE);

        }

        v.findViewById(R.id.flag).setVisibility(View.VISIBLE);

        date = mThisMonthCalendar.get(Calendar.YEAR) + ". " +
                (mThisMonthCalendar.get(Calendar.MONTH) + 1) + ". " +
                mDayList.get(position).getDay();


        /*
            일기가 DB에 있으면 일기랑 스탬프 보여주기 !! diary.setText(~); stamp.setImage(~);
            없으면 diary.setText("작성된 글이 없습니다.");
        */

        // 이거 전달이나 다음달의 날짜를 누르게 되면 현재 달로 인식하니까 나중에 ... 구현할 것 ...

        /* 원본
            년 mThisMonthCalendar.get(Calendar.YEAR)
            월 mThisMonthCalendar.get(Calendar.MONTH) + 1
            일 mDayList.get(position).getDay()
         DB에서 해당 년,월,일의 자료를 불러왔을 때 결과 값이 있으면
         diary.setText("~~");
         stamp.setVisibility(View.VISIBLE);
         switch(스탬프):
            case ~ :
                stamp.setImageResource(R.drawable.~);
                break;
         없으면
         diary.setText("작성된 글이 없습니다.");
        stamp.setVisibility(View.INVISIBLE);
         */
        /*
        mThisMonthCalendar.get(Calendar.YEAR); // 년 위아래 둘 다 mThisMonthCalendar??
        mThisMonthCalendar.get(Calendar.MONTH) + 1;  //월 오류 나는데 원코드에서 수정은 X
        mDayList.get(position).getDay();  //일
        // DB에서 해당 년,월,일의 자료를 불러왔을 때 결과 값이 있으면
        // db 코딩해둔 것
        while(cursor.moveToNext()) {
            if (cursor.getString(0).equals(getString(Calendar.YEAR) + "-" + getString(Calendar.MONTH+1) + "-" + mDayList)) { // 날짜 정보 있을 때(오류 가능성 있음)
                if (cursor.getString(1) != null) { // 내용이 있을 때
                    diary.setText(cursor.getString(1)); //dairydb에서 content 꺼내기
                    stamp.setVisibility(View.VISIBLE);
                    switch (/*여기에 감정 어떻게 나타내는지 결정하면 db랑 같이 바꿔야함) {
                        case /*스탬프별 케이스:
                            stamp.setImageResource(R.drawable. ~); /*스탬프 그림
                            break;
                        case 스탬프별 케이스:
                            stamp.setImageResource(R.drawable. ~); /*스탬프 그림
                            break;
                    }
                }
                else { //없으면
                    diary.setText("작성된 글이 없습니다.");
                    stamp.setVisibility(View.INVISIBLE);
                }
            }
        }*/


    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
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

    private void initCalendarAdapter()
    {
        mCalendarAdapter = new CalendarAdapter(this, R.layout.item_view_calendar, mDayList);
        mGvCalendar.setAdapter(mCalendarAdapter);
    }
}


