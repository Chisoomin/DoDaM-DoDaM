package com.example.dodam;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HabitTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HabitTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HabitTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HabitTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static HabitTwo newInstance(String param1, String param2) {
        HabitTwo fragment = new HabitTwo();
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
    View dialogView2;
    TextView goal2;
    AlertDialog.Builder dlg2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_habit_two, container,false);
        final String[] buttonNames = {
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",

        };
        goal2 = (TextView)v.findViewById(R.id.goal2);

        final GridView gv = (GridView)v.findViewById(R.id.habitTracker);
        MyGridAdapter gAdapter = new MyGridAdapter(getActivity(), buttonNames);
        gv.setAdapter(gAdapter);

        dialogView2 = getLayoutInflater().inflate(R.layout.dialog_habit, null);
        dlg2 = new AlertDialog.Builder(getActivity());


        HabitDBHelper habitDBHelper = new HabitDBHelper(getContext());
        final SQLiteDatabase db = habitDBHelper.getWritableDatabase();
        final SQLiteDatabase db2 = habitDBHelper.getReadableDatabase();

        Cursor cursor = db2.rawQuery("select numID, goal from HabitData;", null);
        while(cursor.moveToNext()) {
            // 이미 목표가 설정되어 있을 때 변경하기
            if (cursor.getString(0).equals("2")) {
                goal2.setText(cursor.getString(1));

                goal2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dialogView2.getParent()!=null){
                            ((ViewGroup)dialogView2.getParent()).removeView(dialogView2);
                        }
                        dlg2.setView(dialogView2);
                        dlg2.setTitle("습관 작성");
                        dlg2.setView(dialogView2);
                        dlg2.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText pw2 = (EditText)dialogView2.findViewById(R.id.editText);
                                goal2.setText(pw2.getText());
                                String requ2 = pw2.getText().toString();

                                db.execSQL("UPDATE HabitData" + "SET"
                                        + "goal =" + requ2 + "WHERE numId =" + "2");


                            }
                        });
                        dlg2.setNegativeButton("취소", null);
                        dlg2.show();
                    }
                });

            }
        }


        goal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogView2.getParent()!=null){
                    ((ViewGroup)dialogView2.getParent()).removeView(dialogView2);
                }
                dlg2.setView(dialogView2);
                dlg2.setTitle("습관 작성");
                dlg2.setView(dialogView2);
                dlg2.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText pw2 = (EditText)dialogView2.findViewById(R.id.editText);
                        goal2.setText(pw2.getText());
                        String requ2 = pw2.getText().toString();

                        ContentValues values = new ContentValues();
                        values.put("numId", "2");
                        values.put("goal", requ2);
                        db.insert("HabitData", null, values);

                    }
                });
                dlg2.setNegativeButton("취소", null);
                dlg2.show();
            }
        });



        return v;
    }
}
