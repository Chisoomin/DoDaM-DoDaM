package com.example.dodamver3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro newInstance(String param1, String param2) {
        Intro fragment = new Intro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Intro newInstance() {
        return new Intro();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    AlphaAnimation AA;
    TextView click;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_intro, container, false);
        click = (TextView) view.findViewById(R.id.click);

        AA = new AlphaAnimation(1, 0);
        AA.setDuration(500);
        AA.setStartOffset(500);
        AA.setRepeatCount(Animation.INFINITE);
        AA.setRepeatMode(Animation.REVERSE);

        click.setAnimation(AA);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_pwd In_pwd = Intro_pwd.newInstance();
                ((IntroPage) getActivity()).replaceFragment(In_pwd);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_pwd In_pwd = Intro_pwd.newInstance();
                ((IntroPage) getActivity()).replaceFragment(In_pwd);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
