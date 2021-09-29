package com.example.dodamver2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoleGame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoleGame extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoleGame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoleGame.
     */
    // TODO: Rename and change types and number of parameters
    public static MoleGame newInstance(String param1, String param2) {
        MoleGame fragment = new MoleGame();
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

    TextView timer, score;
    int game_score = 0; // 플레이 중 점수
    //ImageView hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9;
    Button timerBtn;
    Thread thread = null;

    final String TAG_ON = "on"; //태그용
    final String TAG_OFF = "off";

    ImageView[] img_array = new ImageView[9];
    int[] imageID = {R.id.hole1, R.id.hole2, R.id.hole3, R.id.hole4, R.id.hole5, R.id.hole6, R.id.hole7, R.id.hole8, R.id.hole9};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mole_game, container, false);

        timer = (TextView) view.findViewById(R.id.timer);
        score = (TextView) view.findViewById(R.id.score);
        timerBtn = (Button) view.findViewById(R.id.timer_button);

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
            img_array[i] = (ImageView) view.findViewById(imageID[i]);
            //img_array[i].setForeground(ContextCompat.getDrawable(getContext(), R.drawable.mole));
            // img_array[i].setImageResource(R.drawable.mole);

            img_array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((ImageView) v).getTag().toString().equals(TAG_ON)) {
                        game_score++;
                        score.setText("점수 : " + game_score + "점");
                        ((ImageView) v).setForeground(ContextCompat.getDrawable(getContext(), R.drawable.grass));
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

                thread = new Thread(new timeCheck());
                thread.start();


                //몇 개의 구멍에서 두더지가 나오는지(ex. i<4; 는 4번 구멍까지만 두더지 발생)
                for (int i = 0; i < img_array.length; i++) {
                    new Thread(new MoleTread()).start();
                }

            }
        });

        return view;
    }

    //핸들러
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
            img_array[msg.arg1].setForeground(ContextCompat.getDrawable(getContext(), R.drawable.mole));
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
                img_array[msg.arg1].setForeground(ContextCompat.getDrawable(getContext(), R.drawable.grass));
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
//
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void moleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

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
                // 확인 버튼을 누른 후 토스트 메세지
                Toast.makeText(getContext(), "홈으로 돌아갑니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog eggAlertDialog = builder.create();
        eggAlertDialog.show();


    }



/*
    //두더지 올라오게 해주는 클래스 : 여러개 나오는 것 같음
    // 사용안하고 있음
    public class DThread implements Runnable { //두더지를 올라갔다 내려갔다 해줌
       int index = 0; //두더지 번호

        DThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Message msg1 = new Message();
                    int offtime = new Random().nextInt(5000) + 1000;
                    Thread.sleep(offtime); //두더지가 내려가있는 시간

                   msg1.arg1 = index;
                    onHandler.sendMessage(msg1);

                    int ontime = new Random().nextInt(1000) + 500;
                    Thread.sleep(ontime); //두더지가 올라가있는 시간
                    Message msg2 = new Message();
                    msg2.arg1 = index;
                    offHandler.sendMessage(msg2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}
