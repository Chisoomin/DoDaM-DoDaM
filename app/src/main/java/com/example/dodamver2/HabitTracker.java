package com.example.dodamver2;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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

    ViewPager2 pager;
    ArrayList<Fragment> fragments;
    FloatingActionButton plusbtn;
    View dialogView1;
    TextView goal1;
    AlertDialog.Builder dlg1;
    EditText pw1;
    HabitDBHelper habitDBHelper;
    SQLiteDatabase db;
    SQLiteDatabase db2;
    Cursor cursor;
    int habitcnt = 0;
    ArrayList<Habit_getset> list = new ArrayList<Habit_getset>();
    HabitAdapter habitAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_habit_tracker, container, false);
        pager = view.findViewById(R.id.view_pager1);
        plusbtn = view.findViewById(R.id.plusbtn);

        habitDBHelper = new HabitDBHelper(view.getContext());
        db = habitDBHelper.getWritableDatabase();
        db2 = habitDBHelper.getReadableDatabase();

        cursor = db2.rawQuery("select numID, goal, step from HabitData;", null);

        while(cursor.moveToNext()) {
            String id = cursor.getString(0);
            String goal = cursor.getString(1);
            int step = cursor.getInt(2);

            habitcnt = Integer.valueOf(id);

            list.add(new Habit_getset(id, goal,step));
        }
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView1 = getLayoutInflater().inflate(R.layout.dialog_habit, null);
                dlg1 = new AlertDialog.Builder(getActivity());
                pw1 = (EditText) dialogView1.findViewById(R.id.editText);

                dlg1.setView(dialogView1);
                dlg1.setTitle("습관 작성");
                dlg1.setView(dialogView1);
                dlg1.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        habitcnt++;

                        String requ1 = pw1.getText().toString();

                        String query = "insert into HabitData(numId, goal, step) values('" + habitcnt + "', '" + requ1 + "', '" + 0 + "')";
                        list.add(new Habit_getset(String.valueOf(habitcnt), requ1,0));

                        db.execSQL(query);
                        //db.close();

                    }
                });
                dlg1.setNegativeButton("취소", null);
                dlg1.show();
            }
        });
        habitAdapter = new HabitAdapter(view.getContext(),list);
        habitAdapter.notifyDataSetChanged();

        pager.setAdapter(habitAdapter);

        /*final GridView gv = (GridView)findViewById(R.id.grid);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);*/
        return view;
    }

}