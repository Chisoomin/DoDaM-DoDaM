package com.example.dodamver3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_bonus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_bonus extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_bonus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_bonus.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_bonus newInstance(String param1, String param2) {
        Intro_bonus fragment = new Intro_bonus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Intro_bonus newInstance() {
        return new Intro_bonus();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String name, gender, birthday, pwd, hint, hint_answer;
    ImageButton before, next;
    EditText worry;
    Integer point = 0;
    DBHelper helper;
    SQLiteDatabase join;
    String depression="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro_bonus, container, false);
        before = view.findViewById(R.id.before_btn);
        next = view.findViewById(R.id.n_btn);
        worry = view.findViewById(R.id.editWorry);

        if (getArguments() != null) {
            name = getArguments().getString("name");
            gender = getArguments().getString("gender");
            birthday = getArguments().getString("birthday");
            pwd = getArguments().getString("pwd");
            hint = getArguments().getString("hint");
            hint_answer = getArguments().getString("hint_answer");
        }
        helper = new DBHelper(view.getContext());
        join = helper.getWritableDatabase();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depression = worry.getText().toString();
                Intent intent = new Intent(view.getContext(), Psychological_Test.class);
                startActivity(intent);


                String query = "insert into Dodam(name, type, pass, passHint, passHintAns, birthday, depressionreason, point) values('" + name + "', '" + gender + "', '" + pwd + "', '" + hint + "', '" + hint_answer + "','" + birthday + "', '" + depression + "', '" + point + "')";
                join.execSQL(query);
                join.close();

            }
        });
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_3 In3 = Intro_3.newInstance();
                ((IntroPage) getActivity()).replaceFragment(In3);
            }
        });
        return view;
    }
}