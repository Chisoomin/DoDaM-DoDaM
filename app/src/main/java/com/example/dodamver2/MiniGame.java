package com.example.dodamver2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiniGame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiniGame extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiniGame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiniGame.
     */
    // TODO: Rename and change types and number of parameters
    public static MiniGame newInstance(String param1, String param2) {
        MiniGame fragment = new MiniGame();
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

    String eggBadStr, pointSavStr;
    Integer eggBadInt, eggBadIntM, pointInt, pointSav;

    TextView eggTextView;
    ImageView eggImageView;

    TextView pointRes;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mini_game, container, false);
        eggTextView = (TextView) view.findViewById(R.id.eggTextView);
        eggImageView = (ImageView) view.findViewById(R.id.eggImageView);

        pointRes = (TextView) view.findViewById(R.id.point);

        // 오늘 날짜 포맷
        Date listCurrentTime = Calendar.getInstance().getTime();
        String listDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(listCurrentTime);

        // 알깨는 횟수 DB 설정
        DiaryDBHelper diaryDBHelper = new DiaryDBHelper(view.getContext());
        SQLiteDatabase eggDB = diaryDBHelper.getReadableDatabase();

        // 오늘 날짜를 DB에서 검색해서 기분 정도 알아내기
        // Cursor diaryCursor = eggDB.rawQuery( "select bad from DiaryData where date like '%"+listDate+"%';", null );

        // 테스트 용, 오늘 날짜를 DB에서 검색해서 기분 정도 알아내기
        Cursor diaryCursor = eggDB.rawQuery("select bad from DiaryData where date like '2021-06-02';", null);

        while (diaryCursor.moveToNext()) {
            eggBadStr = diaryCursor.getString(0);
        }

        eggDB.close();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    try {
                        Thread.sleep(1000); //1초 간격으로 실행
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DBHelper dbHelper = new DBHelper(MiniGame.this);
                                SQLiteDatabase pointSavDB = dbHelper.getReadableDatabase();

                                Cursor pointSavCursor = pointSavDB.rawQuery("select point from Dodam", null);

                                while (pointSavCursor.moveToNext()) {
                                    pointSav = pointSavCursor.getInt(0);
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        // error
                    }
            }
        }).start();*/


        // 알깨는 횟수 = 나쁨 감정 * 10 or 100, 알깨는 횟수 다른 변수에 저장
        if (eggBadStr.equals("0"))
            eggBadInt = 100;
        else
            eggBadInt = Integer.parseInt(eggBadStr) * 10;

        eggBadIntM = eggBadInt;

        // 포인트 = 나쁨 감정 or 10
        if (eggBadStr.equals("0"))
            pointInt = 10;
        else
            pointInt = Integer.parseInt(eggBadStr);

        // 상단에 위치하는 TextView 설정
        eggTextView.setText("알을 깨기까지 " + eggBadInt + " 번");

        // 3
        // 알깨기 터치 이벤트
        eggImageView.setOnTouchListener(new eggListener());

        return view;
    }

    // 알깨기 터치 이벤트 메소드
    class eggListener implements View.OnTouchListener {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                // 알을 눌렀을 때 크기가 작아지도록 설정 (0.9배)
                Matrix matrixDown = new Matrix();
                matrixDown.setScale(0.98f, 0.98f);
                eggImageView.setImageMatrix(matrixDown);

                // 누를때 알깨는 횟수 1씩 줄어들도록 설정
                eggBadInt--;
                eggTextView.setText("알을 깨기까지 " + eggBadInt + " 번");

                // 누를때 알깨는 모양 설정 (%에 따라서)
                if (eggBadInt == eggBadIntM)
                    eggImageView.setImageResource(R.drawable.egg0);

                if (eggBadInt == (eggBadIntM * 0.8))
                    eggImageView.setImageResource(R.drawable.egg20);

                if (eggBadInt == (eggBadIntM * 0.6))
                    eggImageView.setImageResource(R.drawable.egg40);

                if (eggBadInt == (eggBadIntM * 0.4))
                    eggImageView.setImageResource(R.drawable.egg60);

                if (eggBadInt == (eggBadIntM * 0.2))
                    eggImageView.setImageResource(R.drawable.egg80);

                if (eggBadInt == 0) {
                    eggImageView.setImageResource(R.drawable.egg100_v1);

                    // 알깨는 횟수 다시 설정
                    eggBadInt = eggBadIntM + 1;

                    // point DB 값 수정
                    pointDB();

                    // 알을 다 깬후 나오는 AlertDialog
                    eggShowDialog();
                }
            }

            if (action == MotionEvent.ACTION_UP) {
                // 알을 눌렀다가 뗐을 때 크기가 커지도록 설정 (1배)
                Matrix matrixDown = new Matrix();
                matrixDown.setScale(1.0f, 1.0f);
                eggImageView.setImageMatrix(matrixDown);
            }
            return true;
        }
    }

    // point 값 수정 DB 설정,
    private void pointDB() {
        pointSav = pointSav + pointInt;
        pointSavStr = String.valueOf(pointSav);

        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase pointDB = dbHelper.getWritableDatabase();
        String pointSql = "UPDATE Dodam SET point=" + pointSav;
        pointDB.execSQL(pointSql);
    }

    // 알을 다 깬후 나오는 AlertDialog 메소드
    // setOnDismissListener는 api 17 이상부터 지원한다는 의미
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void eggShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // 배경 터치 무력화
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //
            }
        });
        builder.setMessage("알이 깨졌습니다.\n\nPOINT : +" + pointInt);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 확인 버튼을 누른 후 토스트 메세지
                Toast.makeText(getContext(), "다시 시작합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog eggAlertDialog = builder.create();
        eggAlertDialog.show();


    }
}