package com.example.dodamver3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static MainScreen newInstance(String param1, String param2) {
        MainScreen fragment = new MainScreen();
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

    TextView point, Fsaying, Fpeople;
    Integer MyPoint;
    ImageView plant;
    String re = "";
    String[] saying;
    ConstraintLayout c;
    Integer SetPoint;
    Cursor cursor;
    BroadcastReceiver broadcastReceiver;
    public static final String MY_ACTION = "com.example.dodamver3.action.ACTION_MY_BROADCAST";
    Button test_btn, minigame_btn;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    int i=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        c = (ConstraintLayout) view.findViewById(R.id.Main);
        plant = view.findViewById(R.id.plant);
        point = (TextView) view.findViewById(R.id.point);
        Fsaying = (TextView) view.findViewById(R.id.Fsaying);
        Fpeople = (TextView) view.findViewById(R.id.Fpeople);
        test_btn = view.findViewById(R.id.test_btn);
        minigame_btn = view.findViewById(R.id.minigame_btn);

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        String url = "http://192.168.56.1:8080/AndroidAppEx/saying.jsp";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        re = response;
                        Log.e("명언", " " + re.toString());
                        saying = re.split("#");
                        Fsaying.setText("\"" + saying[0].trim() + "\"");
                        Fpeople.setText("- " + saying[1].trim());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("오류", " " + error);

                    }
                });
        requestQueue.add(stringRequest);

        DBHelper helper = new DBHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select point, _id from Dodam;", null);

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestResultDB testResultDB = new TestResultDB(view.getContext());
                SQLiteDatabase db_r = testResultDB.getReadableDatabase();
                Cursor cursor = db_r.rawQuery("select date, answer from TestResult;", null);
                mNow = System.currentTimeMillis();
                mDate = new Date(mNow);
                String date = mFormat.format(mDate);

                while (cursor.moveToNext()) {
                    if (cursor.getString(0).equals(date)) {
                        i++;
                    }
                }
                if(i!=0){
                    Toast.makeText(view.getContext(),"오늘의 심리진단은 완료되었습니다. 잘했어요!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(view.getContext(), Psychological_Test.class);
                    startActivity(intent);
                }

            }
        });
        minigame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(view.getContext(), GameSelect2.class);
                startActivity(intent2);
            }
        });

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(1);
            if (id == 2) {
                SetPoint = cursor.getInt(0);
            }
        }
        DBHelper DBHelper = new DBHelper(getContext());
        SQLiteDatabase db_r = DBHelper.getReadableDatabase();
        cursor = db_r.rawQuery("select point, _id from Dodam;", null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(1);
            if (id == 2) {
                SetPoint = cursor.getInt(0);
            }
        }
        point.setText(String.valueOf(SetPoint));
        MyPoint = SetPoint;
        if (MyPoint >= 10 && MyPoint < 25) {
            plant.setImageResource(R.drawable.grow_33);
        }
        if (MyPoint >= 25 && MyPoint < 45) {
            plant.setImageResource(R.drawable.grow_66);
        }
        if (MyPoint >= 45 && MyPoint < 70) {
            plant.setImageResource(R.drawable.grow_99);
        }
        if (MyPoint >= 70) {
            plant.setImageResource(R.drawable.grow_perfect);
        }
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (MY_ACTION.equals(intent.getAction())) {
                    DBHelper helper = new DBHelper(getContext());
                    SQLiteDatabase db = helper.getReadableDatabase();
                    cursor = db.rawQuery("select point, _id from Dodam;", null);
                    while (cursor.moveToNext()) {
                        Integer id = cursor.getInt(1);
                        if (id == 2) {
                            SetPoint = cursor.getInt(0);
                        }
                    }
                    point.setText(String.valueOf(SetPoint));
                    MyPoint = SetPoint;
                    if (MyPoint >= 10 && MyPoint < 25) {
                        plant.setBackgroundResource(R.drawable.grow_33);
                    }
                    if (MyPoint >= 25 && MyPoint < 45) {
                        c.setBackgroundResource(R.drawable.grow_66);
                    }
                    if (MyPoint >= 45 && MyPoint < 70) {
                        c.setBackgroundResource(R.drawable.grow_99);
                    }
                    if (MyPoint >= 70) {
                        c.setBackgroundResource(R.drawable.grow_perfect);
                    }
                }
            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MY_ACTION);
        getContext().registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(broadcastReceiver);
    }
}