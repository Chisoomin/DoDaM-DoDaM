package com.example.dodamver3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GameSelect2 extends AppCompatActivity {
    ImageButton eggBtn, moleBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select2);
        eggBtn = (ImageButton) findViewById(R.id.eggBtn);
        moleBtn = (ImageButton) findViewById(R.id.moleBtn);

        //수정 요청
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                builder.setMessage("알 깨기 게임을 시작할까요?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), EggGame.class);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                builder.setMessage("두더지 잡기 게임을 시작할까요?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MoleGame.class);
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
    }
}