package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {
    Intent intent;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();


        intent = new Intent(this, MainScreen.class);
        TabHost.TabSpec tabspecmain = tabHost.newTabSpec("Main").setIndicator("메인화면");
        tabspecmain.setContent(intent);
        tabHost.addTab(tabspecmain);

        intent = new Intent(this, HabitTracker.class);
        TabHost.TabSpec tabspecHT = tabHost.newTabSpec("HabitTracker").setIndicator("해빗트렉커");
        tabspecHT.setContent(intent);
        tabHost.addTab(tabspecHT);

        intent = new Intent(this, Calendar.class);
        TabHost.TabSpec tabspecCal = tabHost.newTabSpec("Calendar").setIndicator("캘린더");
        tabspecCal.setContent(intent);
        tabHost.addTab(tabspecCal);

        intent = new Intent(this, MiniGame.class);
        TabHost.TabSpec tabspecGame = tabHost.newTabSpec("Game").setIndicator("미니게임");
        tabspecGame.setContent(intent);
        tabHost.addTab(tabspecGame);

        for(int i =0;i<tabHost.getTabWidget().getChildCount();i++){
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.GRAY);
        }

        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.YELLOW);
        tabHost.setOnTabChangedListener(this);
    }


    @Override
    public void onTabChanged(String s) {
        for(int i =0;i<tabHost.getTabWidget().getChildCount();i++){
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.GRAY);
        }

        tabHost = getTabHost();
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.YELLOW);
        //구현 필요(탭 색 안돌아옴)
    }
}