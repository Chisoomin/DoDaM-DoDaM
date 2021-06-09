package com.example.dodam;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_3.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_3 newInstance(String param1, String param2) {
        Intro_3 fragment = new Intro_3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Intro_3 newInstance() {
        return new Intro_3();

    }
    View view;
    String mon, day;
    String name, gender;
    EditText birthday;
    Integer Year, Month, Day;
    Calendar calendar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_intro_3, container,false);
        ImageButton next = (ImageButton)view.findViewById(R.id.n_btn);
        ImageButton before = (ImageButton)view.findViewById(R.id.before_btn);
        birthday = (EditText)view.findViewById(R.id.edit_date);

        calendar=Calendar.getInstance();
        Year=calendar.get(Calendar.YEAR);
        Month=calendar.get(Calendar.MONTH);
        Day=calendar.get(Calendar.DAY_OF_MONTH);

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), mDateSetListener, Year, Month, Day).show();
            }
        });

        if(getArguments()!=null){
            name = getArguments().getString("name");
            gender = getArguments().getString("gender");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_4 In4 = Intro_4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In4);

                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                In4.setArguments(bundle);
                bundle.putString("gender", gender);
                In4.setArguments(bundle);
                bundle.putString("birthday", birthday.getText().toString());
                In4.setArguments(bundle);
            }
        });
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_2 In2 = Intro_2.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In2);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Year = year;
            Month = monthOfYear;
            Day = dayOfMonth;

            calendar.set(Calendar.YEAR, Year);
            calendar.set(Calendar.MONTH, Month);
            calendar.set(Calendar.DATE, Day);

            if(Integer.toString(Month+1).length()<2)
                mon = "0"+(Month+1);
            else
                mon = ""+(Month+1);
            if(Integer.toString(Day).length()<2)
                day="0"+Day;
            else
                day = ""+Day;
            birthday.setText(String.format("%d-%s-%s", Year, mon, day));
        }
    };
}
