package com.example.dodam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {
    Intent intent;
    TabHost tabHost;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.playlist:
                Intent PL = new Intent(getApplicationContext(), PlayList.class);
                startActivity(PL);
                return true;
            case R.id.logout:
                Intent Intro = new Intent(getApplicationContext(), IntroPage.class);
                startActivity(Intro);
                return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();

        intent = new Intent(this, MainScreen.class);
        TabHost.TabSpec tabspecmain = tabHost.newTabSpec("Main").setIndicator("");
        tabspecmain.setContent(intent);
        tabHost.addTab(tabspecmain);

        intent = new Intent(this, HabitTracker.class);
        TabHost.TabSpec tabspecHT = tabHost.newTabSpec("HabitTracker").setIndicator("");
        tabspecHT.setContent(intent);
        tabHost.addTab(tabspecHT);

        intent = new Intent(this, CalenD.class);
        TabHost.TabSpec tabspecCal = tabHost.newTabSpec("Calendar").setIndicator("");
        tabspecCal.setContent(intent);
        tabHost.addTab(tabspecCal);

        intent = new Intent(this, MiniGame.class);
        TabHost.TabSpec tabspecGame = tabHost.newTabSpec("Game").setIndicator("");
        tabspecGame.setContent(intent);
        tabHost.addTab(tabspecGame);

        for(int i =0;i<tabHost.getTabWidget().getChildCount();i++){
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.GRAY);
        }

        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.home_btn);
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.habit_btn);
        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.calendar_btn);
        tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.minigame_btn);

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.open_btn);

        tabHost.setOnTabChangedListener(this);
    }


    @Override
    public void onTabChanged(String s) {
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.home_btn);
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.habit_btn);
        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.calendar_btn);
        tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.minigame_btn);
        /*for(int i =0;i<tabHost.getTabWidget().getChildCount();i++){
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.open_btn);
            //구현문제 논의
        }*/

        tabHost = getTabHost();
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.open_btn);
        //구현 필요(탭 색 안돌아옴)
    }


}