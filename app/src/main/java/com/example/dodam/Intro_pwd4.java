package com.example.dodam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_pwd4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_pwd4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_pwd4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_pwd4.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_pwd4 newInstance(String param1, String param2) {
        Intro_pwd4 fragment = new Intro_pwd4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Intro_pwd4 newInstance() {
        return new Intro_pwd4();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String firstpwd, secondpwd, thirdpwd, fourthpwd, putpwd, userPwd;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_pwd4, container, false);
        if (getArguments() != null) {
            firstpwd = getArguments().getString("firstpwd");
            secondpwd = getArguments().getString("secondpwd");
            thirdpwd = getArguments().getString("thirdpwd");
            fourthpwd = getArguments().getString("fourthpwd");
        }

        putpwd = firstpwd + secondpwd + thirdpwd + fourthpwd;
        //저장한 비밀번호 불러오기

        DBHelper helper = new DBHelper(view.getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id, pass from Dodam;", null);
        while(cursor.moveToNext()){
            Integer id = cursor.getInt(0);
            if(id == 2){
                userPwd = cursor.getString(1);
            }
        }


        if (putpwd.equals(userPwd)) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        // Inflate the layout for this fragment
        }else{
            Intro_pwd0 In_pwd0 = Intro_pwd0.newInstance();
            ((IntroPage)getActivity()).replaceFragment(In_pwd0);
        }


        return view;
    }
}
