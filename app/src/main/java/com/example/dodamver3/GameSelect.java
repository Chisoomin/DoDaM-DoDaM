package com.example.dodamver3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameSelect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameSelect extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameSelect() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameSelect.
     */
    // TODO: Rename and change types and number of parameters
    public static GameSelect newInstance(String param1, String param2) {
        GameSelect fragment = new GameSelect();
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
    ImageButton eggBtn, moleBtn;
    //MiniGame miniGame;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_select, container, false);

        eggBtn = (ImageButton) view.findViewById(R.id.eggBtn);
        moleBtn = (ImageButton) view.findViewById(R.id.moleBtn);


        /*
        * Fragment1 fragment1 = new Fragment1();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;
        *
        * */

        eggBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                // 액티비티로는 넘어가는데 프래그먼트로 전달이 X
                Intent intent = new Intent(getActivity(), TestGame.class);
                startActivity(intent);
*/

               /* Intro_2 In2 = Intro_2.newInstance();
                ((IntroPage) getActivity()).replaceFragment(In2);

                MiniGame miniGame = MiniGame.newInstance();
                ((GameSelect) getActivity()).replaceFragment(miniGame);
*/

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("알 깨기 게임을 시작할까요?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), EggGame.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog eggAlertDialog = builder.create();
                eggAlertDialog.show();


            }
        });

        moleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("두더지 잡기 게임을 시작할까요?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), MoleGame.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog eggAlertDialog = builder.create();
                eggAlertDialog.show();


            }
        });

        return view;
    }
}