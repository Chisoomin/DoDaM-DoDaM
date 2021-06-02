package com.example.dodam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_pwd3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_pwd3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_pwd3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_pwd3.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_pwd3 newInstance(String param1, String param2) {
        Intro_pwd3 fragment = new Intro_pwd3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Intro_pwd3 newInstance() {
        return new Intro_pwd3();
    }
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    String firstpwd, secondpwd, thirdpwd, fourthpwd;
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
        View view = inflater.inflate(R.layout.fragment_intro_pwd3, container, false);
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

        if(getArguments()!=null){
            firstpwd = getArguments().getString("firstpwd");
            secondpwd = getArguments().getString("secondpwd");
            thirdpwd = getArguments().getString("thirdpwd");
        }

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "0";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "1";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "2";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "3";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "4";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "5";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "6";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "7";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "8";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourthpwd = "9";

                Intro_pwd4 In_pwd4 = Intro_pwd4.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In_pwd4);

                Bundle bundle = new Bundle();
                bundle.putString("firstpwd", firstpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("secondpwd", secondpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("thirdpwd", thirdpwd);
                In_pwd4.setArguments(bundle);
                bundle.putString("fourthpwd", fourthpwd);
                In_pwd4.setArguments(bundle);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
