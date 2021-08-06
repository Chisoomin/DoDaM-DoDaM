package com.example.dodamver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WriteDBActivity extends AppCompatActivity {
    EditText editName, editType, editPass;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writedb);

        editName = (EditText)findViewById(R.id.editName);
        editType = (EditText)findViewById(R.id.editType);
        editType = (EditText)findViewById(R.id.editPass);
        btnInsert = (Button)findViewById(R.id.btnInsert);

        DBHelper dbHelper = new DBHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "Insert into Dodam(name, type, pass, passHint, passHintAns, birthday) values ('이명전', '여자', '2222', '2가 네 개', '2222', '1998-11-05')";
                db.execSQL(query);
                db.close();
            }
        });
    }
}
