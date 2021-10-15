package com.example.dodamver3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class AllReward extends AppCompatActivity {
    // 1
    RecyclerView allRewardRecyclerView;
    ImageView allRewardHome;
    ImageButton ques_reward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_reward );

        // 2
        allRewardRecyclerView = (RecyclerView) findViewById( R.id.allRewardRecyclerView );
        allRewardHome = (ImageView) findViewById( R.id.allRewardHome );
        ques_reward = findViewById(R.id.ques_reward);

        ques_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GuideView.Builder(AllReward.this)
                        .setTitle("업적 상세보기")
                        .setContentText("내가 이룬 업적의 상세 설명을 볼 수있어요/")
                        .setTargetView(allRewardRecyclerView)
                        .setContentTextSize(12)//optional
                        .setTitleTextSize(14)//optional
                        .setDismissType(DismissType.anywhere) //optional - default dismissible by TargetView
                        .setGuideListener(new GuideListener() {
                            @Override
                            public void onDismiss(View view) {

                            }
                        })
                        .build()
                        .show();
            }
        });

        RewardDBHelper rewardDBHelper = new RewardDBHelper( getApplicationContext() );
        SQLiteDatabase rewardDB = rewardDBHelper.getReadableDatabase();

        Cursor rewardCursor = rewardDB.rawQuery( "select image, exp, detailExp from rewardData", null );
        ArrayList<RewardItem> rewardItemArrayList = new ArrayList<>();
        while (rewardCursor.moveToNext()) {
            RewardItem rewardItem = new RewardItem();
            rewardItem.rewardImage = rewardCursor.getBlob( 0 );
            rewardItem.rewardExp = rewardCursor.getString( 1 );
            rewardItem.rewardDetailExp = rewardCursor.getString( 2 );
            rewardItemArrayList.add( rewardItem );
        }

        rewardDB.close();

        // 업적 어댑터 연결
        AllRewardAdapter allRewardAdapter = new AllRewardAdapter( rewardItemArrayList );
        allRewardRecyclerView.setAdapter( allRewardAdapter );
        allRewardRecyclerView.setLayoutManager( new GridLayoutManager( this, 3) );

        // 3
        allRewardHome.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
                finish();
            }
        } );
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}