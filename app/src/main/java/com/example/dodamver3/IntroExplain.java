package com.example.dodamver3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IntroExplain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntroExplain extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IntroExplain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IntroExplain.
     */
    // TODO: Rename and change types and number of parameters
    public static IntroExplain newInstance(String param1, String param2) {
        IntroExplain fragment = new IntroExplain();
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

    public static IntroExplain newInstance() {
        return new IntroExplain();
    }

    EditText name;
    ImageView Introoo;
    TextView subTitle;
    AlphaAnimation AA;
    String i = "0";
    ImageButton first_ques;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro_explain, container, false);
        ImageButton next = (ImageButton) view.findViewById(R.id.n_btn);
        name = view.findViewById(R.id.name);
        Introoo = (ImageView) view.findViewById(R.id.introoo);
        subTitle = view.findViewById(R.id.subtitle);
        first_ques = view.findViewById(R.id.first_ques);

        AA = new AlphaAnimation(1, 0);
        AA.setDuration(3000);
        AA.setStartOffset(2500);

        if (getArguments() != null) {
            i = getArguments().getString("back");
        }

        if (i != null) {
            if (i.equals("1")) {
                Introoo.setVisibility(View.INVISIBLE);
                subTitle.setVisibility(View.INVISIBLE);
            } else {
                Introoo.setAnimation(AA);
                Introoo.setVisibility(View.INVISIBLE);
                subTitle.setAnimation(AA);
                subTitle.setVisibility(View.INVISIBLE);
            }
        }

        first_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GuideView.Builder(getContext())
                        .setTitle("다음 버튼")
                        .setContentText("다음 버튼을 눌러주세요.")
                        .setTargetView(next)
                        .setContentTextSize(12)//optional
                        .setTitleTextSize(14)//optional
                        .setDismissType(DismissType.targetView) //optional - default dismissible by TargetView
                        .setGuideListener(new GuideListener() {
                            @Override
                            public void onDismiss(View view) {

                            }
                        })
                        .build()
                        .show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_1 In1 = Intro_1.newInstance();
                ((IntroPage) getActivity()).replaceFragment(In1);
            }
        });

        return view;
    }
}