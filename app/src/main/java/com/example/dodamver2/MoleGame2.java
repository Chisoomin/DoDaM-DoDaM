package com.example.dodamver2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


// 두더지 게임 액티비티 버전

public class MoleGame2 extends AppCompatActivity {

    TextView timer, score;
    int game_score = 0; // 플레이 중 점수
    //ImageView hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9;
    Button timerBtn;
    Thread thread;
    ConstraintLayout hole_container, game_container;

    final String TAG_ON = "on"; //태그용
    final String TAG_OFF = "off";

    ImageView[] img_array = new ImageView[9];
    int[] imageID = {R.id.hole1, R.id.hole2, R.id.hole3, R.id.hole4, R.id.hole5, R.id.hole6, R.id.hole7, R.id.hole8, R.id.hole9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mole_game2);

        timer = (TextView) findViewById(R.id.timer);
        score = (TextView) findViewById(R.id.score);
        timerBtn = (Button) findViewById(R.id.timer_button);
        hole_container = (ConstraintLayout) findViewById(R.id.hole_container);
        game_container = (ConstraintLayout) findViewById(R.id.game_container);

        hole_container.setVisibility(View.INVISIBLE);

      /*  // 9개
        hole1 = (ImageView) view.findViewById(R.id.hole1);
        hole2 = (ImageView) view.findViewById(R.id.hole2);
        hole3 = (ImageView) view.findViewById(R.id.hole3);
        hole4 = (ImageView) view.findViewById(R.id.hole4);
        hole5 = (ImageView) view.findViewById(R.id.hole5);
        hole6 = (ImageView) view.findViewById(R.id.hole6);
        hole7 = (ImageView) view.findViewById(R.id.hole7);
        hole8 = (ImageView) view.findViewById(R.id.hole8);
        hole9 = (ImageView) view.findViewById(R.id.hole9);*/

        for (int i = 0; i < img_array.length; i++) {
            /*int img_id = getResources().getIdentifier("imageView"+i+1, "id", "com.example.pc_20.molegame");*/
            img_array[i] = (ImageView) findViewById(imageID[i]);
            //img_array[i].setForeground(ContextCompat.getDrawable(getContext(), R.drawable.mole));
            // img_array[i].setImageResource(R.drawable.mole);


            img_array[i].setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {


                    if (((ImageView) v).getTag().toString().equals(TAG_ON)) {
                        //mLastClickTime = SystemClock.elapsedRealtime();
                        game_score++;
                        score.setText("점수 : " + game_score + "점");
                        ((ImageView) v).setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.grass));
                        v.setTag(TAG_OFF);

                    }
                }
            });

        }

        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                timer.setTextColor(Color.BLUE);*/
                timerBtn.setVisibility(View.INVISIBLE);
                hole_container.setVisibility(View.VISIBLE);
                thread = new Thread(new timeCheck());
                thread.start();


                //몇 개의 구멍에서 두더지가 나오는지(ex. i<4; 는 4번 구멍까지만 두더지 발생)
                for (int i = 0; i < img_array.length; i++) {
                    new Thread(new MoleTread()).start();
                }

            }
        });

    }

    // 두더지 터치 중복 -> 강종 방지
    public abstract class OnSingleClickListener implements View.OnClickListener {
        //중복클릭시간차이
        private static final long MIN_CLICK_INTERVAL = 600;

        //마지막으로 클릭한 시간
        private long mLastClickTime;

        public abstract void onSingleClick(View v);

        @Override
        public final void onClick(View v) {
            //현재 클릭한 시간
            long currentClickTime = SystemClock.uptimeMillis();
            //이전에 클릭한 시간과 현재시간의 차이
            long elapsedTime = currentClickTime - mLastClickTime;
            //마지막클릭시간 업데이트
            mLastClickTime = currentClickTime;

            //내가 정한 중복클릭시간 차이를 안넘었으면 클릭이벤트 발생못하게 return
            if (elapsedTime <= MIN_CLICK_INTERVAL)
                return;
            //중복클릭시간 아니면 이벤트 발생
            onSingleClick(v);
        }
    }

    // 핸들러
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void handleMessage(Message msg) {
            timer.setText(msg.arg1 + "초");

            if (msg.arg1 <= 10) {
                timer.setTextColor(Color.RED);
                if (msg.arg1 == 0) {
                    // 메세지 박스 띄우는 쪽으로
                    // 알을 다 깬후 나오는 AlertDialog

                    moleDialog();

                    thread.interrupt();

                }
            }


        }
    };

    @SuppressLint("HandlerLeak")
    Handler onHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg) {
            img_array[msg.arg1].setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mole));
            img_array[msg.arg1].setTag(TAG_ON); //올라오면 ON태그 달아줌
        }
    };

    @SuppressLint("HandlerLeak")
    Handler offHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg) {
            if (img_array[msg.arg1].getTag().equals(TAG_ON)) {
                //img_array[msg.arg1].setImageResource(R.drawable.grass);
                img_array[msg.arg1].setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.grass));
            }
            img_array[msg.arg1].setTag(TAG_OFF); //내려오면 OFF태그 달아줌
        }
    };

    // 타이머 메소드
    public class timeCheck implements Runnable {

        @Override
        public void run() {
            for (int i = 30; i >= 0; i--) {
                Message msg = new Message();
                msg.arg1 = i;
                handler.sendMessage(msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

        }
    }

    // 두더지 올라오게 해주는 클래스
    public class MoleTread implements Runnable {

        int position = 0;

        @Override
        public void run() {
            while (true) {
                int index = new Random().nextInt(9); // 두더지 번호
                try {
                    for (int i = 0; i < 2; i++) { // 두더지 수?
                        position = index;

                        Message msg1 = new Message();
                        int offtime = new Random().nextInt(5000) + 2000;
                        Thread.sleep(offtime); //두더지가 내려가있는 시간
                        msg1.arg1 = position;
                        onHandler.sendMessage(msg1);

                        int ontime = new Random().nextInt(1000) + 700;
                        Thread.sleep(ontime); //두더지가 올라가있는 시간
                        Message msg2 = new Message();
                        msg2.arg1 = position;
                        offHandler.sendMessage(msg2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //결과창
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void moleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MoleGame2.this);

        // 배경 터치 무력화
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //
            }
        });
        builder.setMessage("시간 종료!.\n\nPOINT : +" + game_score);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                // 확인 버튼을 누른 후 토스트 메세지
                Toast.makeText(getApplicationContext(), "홈으로 돌아갑니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog moleDialog = builder.create();
        moleDialog.show();


    }


}