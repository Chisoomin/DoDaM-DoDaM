package com.example.dodamver3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class AllReward extends AppCompatActivity {
    RecyclerView allRewardRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_reward );

        allRewardRecyclerView = (RecyclerView) findViewById( R.id.allRewardRecyclerView );

        RewardDBHelper rewardDBHelper = new RewardDBHelper( getApplicationContext() );
        SQLiteDatabase rewardDB = rewardDBHelper.getReadableDatabase();

        Cursor rewardCursor = rewardDB.rawQuery( "select image, exp from rewardData", null );
        ArrayList<RewardItem> rewardItemArrayList = new ArrayList<>();
        while (rewardCursor.moveToNext()) {
            RewardItem rewardItem = new RewardItem();
            rewardItem.rewardImage = rewardCursor.getBlob( 0 );
            rewardItem.rewardExp = rewardCursor.getString( 1 );
            rewardItemArrayList.add( rewardItem );
        }

        rewardDB.close();

        // 업적 어댑터 연결
        AllRewardAdapter allRewardAdapter = new AllRewardAdapter( rewardItemArrayList );
        allRewardRecyclerView.setAdapter( allRewardAdapter );
        allRewardRecyclerView.setLayoutManager( new GridLayoutManager( this, 3) );

    }
}