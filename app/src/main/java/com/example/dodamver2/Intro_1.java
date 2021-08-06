package com.example.dodamver2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_1 newInstance(String param1, String param2) {
        Intro_1 fragment = new Intro_1();
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

    public static Intro_1 newInstance() {
        return new Intro_1();
    }

    View view;
    EditText name;
    ImageView Introoo;
    AlphaAnimation AA;
    String i="0";
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_intro_1, container, false);
        ImageButton next = (ImageButton) view.findViewById(R.id.n_btn);
        name = view.findViewById(R.id.name);
        Introoo = (ImageView)view.findViewById(R.id.introoo);

        AA = new AlphaAnimation(1,0);
        AA.setDuration(1000);
        AA.setStartOffset(2500);

        if(getArguments()!=null){
            i = getArguments().getString("back");
        }

        if (i != null) {
            if(i.equals("1")){
                Introoo.setVisibility(View.INVISIBLE);
            }else{
                Introoo.setAnimation(AA);
                Introoo.setVisibility(View.INVISIBLE);
            }
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_2 In2 = Intro_2.newInstance();
                ((IntroPage) getActivity()).replaceFragment(In2);

                Bundle bundle = new Bundle();
                bundle.putString("name", name.getText().toString());
                In2.setArguments(bundle);
            }
        });
        // Inflate the layout for this fragment
        return view;

    }
}
