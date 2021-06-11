package com.example.dodam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_2.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_2 newInstance(String param1, String param2) {
        Intro_2 fragment = new Intro_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Intro_2 newInstance() {
        return new Intro_2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    String name, gender;
    ImageButton male, female;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_2,container,false);
        ImageButton next = (ImageButton)view.findViewById(R.id.n_btn);
        ImageButton before = (ImageButton)view.findViewById(R.id.before_btn);
        male = (ImageButton)view.findViewById(R.id.male);
        female = (ImageButton)view.findViewById(R.id.female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setImageResource(R.drawable.male_r);
                female.setImageResource(R.drawable.female_g);
                gender = "남성";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setImageResource(R.drawable.female_r);
                male.setImageResource(R.drawable.male_g);
                gender = "여성";
            }
        });

        if(getArguments()!=null){
            name = getArguments().getString("name");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_3 In3 = Intro_3.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In3);

                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                In3.setArguments(bundle);
                bundle.putString("gender", gender);
                In3.setArguments(bundle);
            }
        });
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_1 In1 = Intro_1.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In1);

                Bundle bundle = new Bundle();
                bundle.putString("back", "1");
                In1.setArguments(bundle);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
