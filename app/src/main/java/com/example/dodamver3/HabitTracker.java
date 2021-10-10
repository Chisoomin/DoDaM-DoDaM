package com.example.dodamver3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HabitTracker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HabitTracker extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HabitTracker() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HabitTracker.
     */
    // TODO: Rename and change types and number of parameters
    public static HabitTracker newInstance(String param1, String param2) {
        HabitTracker fragment = new HabitTracker();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private HorizontalCalendar horizontalCalendar;
    ArrayList<habit_getset> list = new ArrayList<habit_getset>();
    habitAdapter adapter;
    TextView date;
    ImageButton month_pick, plus_btn, jum1_btn, jum2_btn;
    RecyclerView habit_recycle;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    ItemTouchHelper helper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_habit_tracker, container, false);
        date = view.findViewById(R.id.date);
        month_pick = view.findViewById(R.id.monthpick_btn);
        plus_btn = view.findViewById(R.id.plus_btn);
        jum1_btn = view.findViewById(R.id.jum1_btn);
        jum2_btn = view.findViewById(R.id.jum2_btn);
        habit_recycle = view.findViewById(R.id.habit_recycle);

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        date.setText(mFormat.format(mDate));

        //ItemTouchHelper 생성
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        //RecyclerView에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(habit_recycle);

        month_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        month_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이트 픽커
            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(new habit_getset("1", "열심히 프로젝트", "2021. 10. 09"));

                adapter = new habitAdapter(list);

                habit_recycle.setLayoutManager(new LinearLayoutManager(view.getContext()));
                habit_recycle.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                //색상선택
                //습관, 날짜, 습관 넘버링 저장하는 db
                //습관과 날짜가 저장되는 db(습관 넘버, 습관 달성 날짜, step)  step==30일이되면 그만하겠냐 물음.
            }
        });

        jum1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        jum2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -100);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 100);
        Calendar defaultSelectedDate;

        /* horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(6)
                .mode(HorizontalCalendar.Mode.MONTHS)
                .configure()
                .formatMiddleText("MMM")
                .formatBottomText("yyyy")
                .showTopText(false)
                .showBottomText(true)
                .textColor(Color.LTGRAY, Color.WHITE)
                .end()
                .defaultSelectedDate(Calendar.getInstance()).build();*/
        horizontalCalendar = new HorizontalCalendar.Builder(view.getRootView(), R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .build();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(getContext(), date.getTime() + " is selected!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}