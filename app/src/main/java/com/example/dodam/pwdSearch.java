package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class pwdSearch extends AppCompatActivity {
    TextView ques;
    EditText ans;
    String answer, pwd, answer_u;
    ImageButton search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_search);

        ques = (TextView)findViewById(R.id.ques);
        ans = (EditText)findViewById(R.id.ans);
        search = (ImageButton)findViewById(R.id.search);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteDatabase db2 = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select pass, passHint, passHintAns, _id from Dodam;", null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(3);
            if (id == 2) {
                ques.setText(cursor.getString(1));
                answer = cursor.getString(2);
                pwd = cursor.getString(0);
            }
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_u = ans.getText().toString();
                if(answer_u.equals(answer)){
                    Toast.makeText(getApplicationContext(),"비밀번호 : "+pwd,Toast.LENGTH_LONG).show();
                    Intent Intro = new Intent(getApplicationContext(), IntroPage.class);
                    startActivity(Intro);
                }else{
                    Toast.makeText(getApplicationContext(),"일치하지 않습니다. 다시 입력해주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
