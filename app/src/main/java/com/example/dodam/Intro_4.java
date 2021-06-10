package com.example.dodam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro_4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Intro_4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Intro_4.
     */
    // TODO: Rename and change types and number of parameters
    public static Intro_4 newInstance(String param1, String param2) {
        Intro_4 fragment = new Intro_4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    String name, gender, birthday, pwd, hint, hint_answer;
    EditText edit_pwd, edit_hint, edit_hint_answer;
    DBHelper helper;
    SQLiteDatabase join;
    View view;
    Integer point=0;
    public static Intro_4 newInstance() {
        return new Intro_4();
    }

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
        view = inflater.inflate(R.layout.fragment_intro_4,container,false);
        ImageButton next = (ImageButton)view.findViewById(R.id.n_btn);
        ImageButton before = (ImageButton)view.findViewById(R.id.before_btn);
        edit_pwd = (EditText)view.findViewById(R.id.edit_pwd);
        edit_hint = (EditText)view.findViewById(R.id.edit_q);
        edit_hint_answer = (EditText)view.findViewById(R.id.edit_a);

        if(getArguments()!=null){
            name = getArguments().getString("name");
            gender = getArguments().getString("gender");
            birthday = getArguments().getString("birthday");
        }

        edit_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                if(!b){
                    if(edit_pwd.length()<4){
                        Toast.makeText(view.getContext(), "비밀번호는 4자리로 설정해 주세요.", Toast.LENGTH_SHORT).show();
                        edit_pwd.post(new Runnable() {
                            @Override
                            public void run() {
                                edit_pwd.setFocusable(true);
                                edit_pwd.requestFocus();
                                InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(edit_pwd,0);
                            }
                        });
                    }
                }
            }
        });

        helper = new DBHelper(view.getContext());
        join = helper.getWritableDatabase();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro In = Intro.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In);

                pwd = edit_pwd.getText().toString();
                hint = edit_hint.getText().toString();
                hint_answer = edit_hint_answer.getText().toString();


                String query="insert into Dodam(name, type, pass, passHint, passHintAns, birthday, point) values('" +name + "', '" +gender+ "', '"+ pwd+"', '"+hint+"', '"+hint_answer+"','"+birthday+"','"+point+"')";

                join.execSQL(query);
                join.close();

            }
        });
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_3 In3 = Intro_3.newInstance();
                ((IntroPage)getActivity()).replaceFragment(In3);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
