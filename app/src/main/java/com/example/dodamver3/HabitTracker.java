package com.example.dodamver3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
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
    int habitcnt = 0;
    HabitDBHelper habitDBHelper;
    SQLiteDatabase db;
    SQLiteDatabase db2;
    SQLiteDatabase db3;
    Cursor cursor, cursor2;

    String mon, day;
    Integer Year, Month, Day;
    Calendar calendar;

    String[] h_id= new String[100];
    int i =0;

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

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        habit_recycle.setLayoutManager(manager);


        adapter = new habitAdapter(view.getContext());
        habit_recycle.setAdapter(adapter);


        //ItemTouchHelper 생성
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        //RecyclerView에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(habit_recycle);

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        month_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), mDateSetListener, Year, Month, Day).show();
            }
        });


        habitDBHelper = new HabitDBHelper(view.getContext());
        db = habitDBHelper.getWritableDatabase();
        db2 = habitDBHelper.getReadableDatabase();

        HabitAchieveDB habitAchieveDB = new HabitAchieveDB(view.getContext());
        db3 = habitAchieveDB.getReadableDatabase();

        cursor2 = db3.rawQuery("select numID, date, bColor from HabitAchieve;", null);
        while (cursor2.moveToNext()){
            String id = cursor2.getString(0);
            String date = cursor2.getString(1);
            Log.e("아이디랑 날짜",id+"  "+date);
            if(date.equals(mFormat.format(mDate))){
                h_id[i]=id;
                i++;
                Log.e("아이디랑 아이값",h_id+"      "+i);
            }
        }

        cursor = db2.rawQuery("select numID, goal, date, bColor from HabitData;", null);

        while (cursor.moveToNext()) {
            Integer cnt=0;
            String id = cursor.getString(0);
            String goal = cursor.getString(1);
            String date = cursor.getString(2);
            Integer bColor = cursor.getInt(3);

            habitcnt = Integer.valueOf(id);

            for(int j=0;j<=i;j++){
                if(id.equals(h_id[j])){
                    cnt++;
                    break;
                }
            }
            if(cnt==0){
                habit_getset habit_gs = new habit_getset(id, goal, date, bColor);
                adapter.addItem(habit_gs);
            }

            //list.add(new habit_getset(id, goal, date, bColor));

        }
        //adapter = new habitAdapter(list, view);

        habit_recycle.setLayoutManager(new LinearLayoutManager(view.getContext()));
        habit_recycle.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //list.add(new habit_getset("1", "열심히 프로젝트", "2021. 10. 09"));

                new MaterialColorPickerDialog
                        .Builder(view.getContext())
                        .setTitle("색상을 선택해 주세요.")
                        .setColorShape(ColorShape.SQAURE)
                        .setPositiveButton("다음 >")
                        .setColorSwatch(ColorSwatch._300)
                        .setDefaultColor(R.color.pink1)
                        .setColorListener(new ColorListener() {
                            @Override
                            public void onColorSelected(int color, @NotNull String colorHex) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                View dialogView = getLayoutInflater().inflate(R.layout.habit_dialog, null);
                                builder.setView(dialogView);
                                Log.e("color", color + "");

                                builder.setPositiveButton("습관 등록", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int pos) {
                                        habitcnt++;
                                        EditText habit = dialogView.findViewById(R.id.hab);
                                        String requ = habit.getText().toString();

                                        if (color == -16449131) {
                                            Integer substitute_color = Color.rgb(100, 154, 209);
                                            String query = "insert into HabitData(numId, goal, date, bColor) values('" + habitcnt + "', '" + requ + "', '" + mFormat.format(mDate) + "', '" + substitute_color + "')";
                                            habit_getset habit_gs = new habit_getset(String.valueOf(habitcnt), requ, mFormat.format(mDate), color);
                                            adapter.addItem(habit_gs);
                                            //list.add(new habit_getset(String.valueOf(habitcnt), requ, mFormat.format(mDate), color));
                                            db.execSQL(query);
                                        } else {
                                            String query = "insert into HabitData(numId, goal, date, bColor) values('" + habitcnt + "', '" + requ + "', '" + mFormat.format(mDate) + "', '" + color + "')";
                                            habit_getset habit_gs = new habit_getset(String.valueOf(habitcnt), requ, mFormat.format(mDate), color);
                                            adapter.addItem(habit_gs);
                                            //list.add(new habit_getset(String.valueOf(habitcnt), requ, mFormat.format(mDate), color));
                                            db.execSQL(query);
                                        }


                                    }

                                });

                                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int pos) {

                                    }

                                });
                                builder.show();

                            }
                        })
                        .show();
                /*new ColorPickerDialog
                        .Builder(view.getContext())
                        .setTitle("Pick Theme")
                        .setColorShape(ColorShape.SQAURE)
                        .setDefaultColor(R.color.pink1)
                        .setColorListener(new ColorListener() {
                            @Override
                            public void onColorSelected(int i, @NonNull String s) {

                            }
                        }).show();*/

                //adapter = new habitAdapter(list, view);

                //habit_recycle.setLayoutManager(new LinearLayoutManager(view.getContext()));
                //habit_recycle.setAdapter(adapter);

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
        endDate.add(Calendar.MONTH, 1);
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
            public void onDateSelected(Calendar date1, int position) {
                date.setText(mFormat.format(date1.getTime()));

                adapter.clearItem();

                int k =0;
                String[] h_id2= new String[100];

                habitDBHelper = new HabitDBHelper(view.getContext());
                db = habitDBHelper.getWritableDatabase();
                db2 = habitDBHelper.getReadableDatabase();

                HabitAchieveDB habitAchieveDB = new HabitAchieveDB(view.getContext());
                db3 = habitAchieveDB.getReadableDatabase();

                cursor2 = db3.rawQuery("select numID, date, bColor from HabitAchieve;", null);
                while (cursor2.moveToNext()){
                    String id = cursor2.getString(0);
                    String datee = cursor2.getString(1);
                    Log.e("아이디랑 날짜",id+"  "+date);

                    if(datee.equals(mFormat.format(date1.getTime()))){

                        h_id2[k]=id;
                        k++;
                        Log.e("아이디랑 케이값",h_id2+"      "+k);
                    }
                }

                cursor = db2.rawQuery("select numID, goal, date, bColor from HabitData;", null);

                while (cursor.moveToNext()) {
                    Integer cnt=0;
                    String id = cursor.getString(0);
                    String goal = cursor.getString(1);
                    String date = cursor.getString(2);
                    Integer bColor = cursor.getInt(3);

                    Date date_h = null; // String -> Date
                    try {
                        date_h = mFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //Calendar cal = Calendar.getInstance();
                    //cal.setTime(date_h);  // Date -> Calendar

                    habitcnt = Integer.valueOf(id);
                    if(date_h.before(date1.getTime())||date_h.equals(date1.getTime())){
                        for(int j=0;j<=i;j++){
                            if(id.equals(h_id2[j])){
                                cnt++;
                                break;
                            }
                        }
                        if(cnt==0){
                            habit_getset habit_gs = new habit_getset(id, goal, date, bColor);
                            adapter.addItem(habit_gs);
                        }
                    }

                    //list.add(new habit_getset(id, goal, date, bColor));

                }


                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Year = year;
            Month = monthOfYear;
            Day = dayOfMonth;

            calendar.set(Calendar.YEAR, Year);
            calendar.set(Calendar.MONTH, Month);
            calendar.set(Calendar.DATE, Day);

            Calendar startDate = calendar;
            startDate.add(Calendar.MONTH, -100);

            Calendar endDate = calendar;
            endDate.add(Calendar.MONTH, 100);

            if (Integer.toString(Month + 1).length() < 2)
                mon = "0" + (Month + 1);
            else
                mon = "" + (Month + 1);
            if (Integer.toString(Day).length() < 2)
                day = "0" + Day;
            else
                day = "" + Day;
            date.setText(String.format("%d년 %s월 %s일", Year, mon, day));

        }
    };
}