package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DiaryCalendar extends AppCompatActivity {
    // 1
    TextView happyPercent, badPercent, sadPercent;
    SeekBar happySeekBar, badSeekBar, sadSeekBar;
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_diary_calendar );

        // 2
        happyPercent = (TextView) findViewById( R.id.happyPercent );
        badPercent = (TextView) findViewById( R.id.badPercent );
        sadPercent = (TextView) findViewById( R.id.sadPercent );

        happySeekBar = (SeekBar) findViewById( R.id.happySeekBar );
        badSeekBar = (SeekBar) findViewById( R.id.badSeekBar );
        sadSeekBar = (SeekBar) findViewById( R.id.sadSeekBar );

        saveButton = (Button) findViewById( R.id.saveButton );

        // 3
        // 기쁨 seekBar를 움직였을 때 바뀌는 리스너
        happySeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                happyPercent.setText( String.valueOf( progress ) );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        } );

        // 화남 seekBar를 움직였을 때 바뀌는 리스너
        badSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                badPercent.setText( String.valueOf( progress ) );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        } );

        // 슬픔 seekBar를 움직였을 때 바뀌는 리스너
        sadSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sadPercent.setText( String.valueOf( progress ) );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        } );

        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayList.class);
                startActivity(intent);
            }
        } );
    }
}