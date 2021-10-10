package com.example.dodamver3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EggGame extends AppCompatActivity {
    String eggBadStr, pointSavStr;
    Integer eggBadInt, eggBadIntM, pointInt, pointSav;

    TextView eggTextView;
    ImageView eggImageView;

    TextView pointRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_game);

        eggTextView = (TextView) findViewById(R.id.eggTextView);
        eggImageView = (ImageView) findViewById(R.id.eggImageView);

        pointRes = (TextView) findViewById(R.id.point);


        // 오늘 날짜 포맷
        Date listCurrentTime = Calendar.getInstance().getTime();
        String listDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(listCurrentTime);

        // 알깨는 횟수 DB 설정
        DiaryDBHelper diaryDBHelper = new DiaryDBHelper(getApplicationContext());
        SQLiteDatabase eggDB = diaryDBHelper.getReadableDatabase();

        // 오늘 날짜를 DB에서 검색해서 기분 정도 알아내기
        // Cursor diaryCursor = eggDB.rawQuery( "select bad from DiaryData where date like '%"+listDate+"%';", null );

        // 테스트 용, 오늘 날짜를 DB에서 검색해서 기분 정도 알아내기
        Cursor diaryCursor = eggDB.rawQuery("select bad from DiaryData where date like '2021-06-02';", null);

        while (diaryCursor.moveToNext()) {
            eggBadStr = diaryCursor.getString(0);
        }

        eggDB.close();


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

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase pointDB = dbHelper.getWritableDatabase();
        String pointSql = "UPDATE Dodam SET point=" + pointSav;
        pointDB.execSQL(pointSql);
    }

    // 알을 다 깬후 나오는 AlertDialog 메소드
    // setOnDismissListener는 api 17 이상부터 지원한다는 의미
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void eggShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EggGame.this);

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
                Toast.makeText(getApplicationContext(), "다시 시작합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog eggAlertDialog = builder.create();
        eggAlertDialog.show();


    }

}