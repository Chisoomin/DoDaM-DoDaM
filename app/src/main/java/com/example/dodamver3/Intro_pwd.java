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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_pwd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_pwd extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_pwd() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_pwd.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_pwd newInstance(String param1, String param2) {
        Intro_pwd fragment = new Intro_pwd();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static Intro_pwd newInstance() {
        return new Intro_pwd();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    String[] pwd = new String[4];
    TextView search;
    ConstraintLayout constraintLayout;
    BroadcastReceiver broadcastReceiver;
    public static final String MY_ACTION = "com.example.dodamver2.action.ACTION_MY_BROADCAST";
    int i=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro_pwd, container, false);
        btn0 = (ImageButton)view.findViewById(R.id.n0);
        btn1 = (ImageButton)view.findViewById(R.id.n1);
        btn2 = (ImageButton)view.findViewById(R.id.n2);
        btn3 = (ImageButton)view.findViewById(R.id.n3);
        btn4 = (ImageButton)view.findViewById(R.id.n4);
        btn5 = (ImageButton)view.findViewById(R.id.n5);
        btn6 = (ImageButton)view.findViewById(R.id.n6);
        btn7 = (ImageButton)view.findViewById(R.id.n7);
        btn8 = (ImageButton)view.findViewById(R.id.n8);
        btn9 = (ImageButton)view.findViewById(R.id.n9);
        search = (TextView)view.findViewById(R.id.textView7);
        constraintLayout = view.findViewById(R.id.mainB);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(getContext(), pwdSearch.class);
                startActivity(search);
            }
        });
        String userPwd = null;
        DBHelper helper = new DBHelper(view.getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id, pass from Dodam;", null);
        while(cursor.moveToNext()){
            Integer id = cursor.getInt(0);
            if(id == 2){
                userPwd = cursor.getString(1);
            }
        }
        String finalUserPwd = userPwd;

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "0";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "1";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "2";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "3";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "4";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "5";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "6";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "7";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "8";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd[i] = "9";
                i++;
                if(i==1){
                    constraintLayout.setBackgroundResource(R.drawable.pwd1);
                }else if(i==2){
                    constraintLayout.setBackgroundResource(R.drawable.pwd2);

                }else if(i==3){
                    constraintLayout.setBackgroundResource(R.drawable.pwd3);

                }else if(i==4){
                    constraintLayout.setBackgroundResource(R.drawable.pwd4);

                }

                if(i==4){
                    if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent2);
                    }else {
                        constraintLayout.setBackgroundResource(R.drawable.pwd);
                        pwd = new String[4];
                        i=0;
                    }
                }
            }
        });



        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (MY_ACTION.equals(intent.getAction())) {
                    if(i==3){
                        if (i>=3 && (pwd[0] + pwd[1] + pwd[2] + pwd[3]).equals(finalUserPwd)) {
                            Intent intent2 = new Intent(view.getContext(), MainActivity.class);
                            startActivity(intent2);
                        }else {
                            constraintLayout.setBackgroundResource(R.drawable.pwd);
                            pwd = new String[3];
                        }
                    }
                }
            }
        };


        // Inflate the layout for this fragment
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