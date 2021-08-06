package com.example.dodamver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
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

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setId(0));
        tabs.addTab(tabs.newTab().setId(1));
        tabs.addTab(tabs.newTab().setId(2));
        tabs.addTab(tabs.newTab().setId(3));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //tabs.getTabAt(0).view.setBackgroundResource(R.drawable.home_btn);
        tabs.getTabAt(1).view.setBackgroundResource(R.drawable.habit_btn);
        tabs.getTabAt(2).view.setBackgroundResource(R.drawable.calendar_btn);
        tabs.getTabAt(3).view.setBackgroundResource(R.drawable.minigame_btn);

        tabs.getTabAt(0).view.setBackgroundResource(R.drawable.open_btn);

        //Adapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final AdapterforTab myPagerAdapter = new AdapterforTab(getSupportFragmentManager(), 4);
        viewPager.setAdapter(myPagerAdapter);

        //탭 선택 이벤트
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabs.getTabAt(tabs.getSelectedTabPosition()).view.setBackgroundResource(R.drawable.open_btn);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabs.getTabAt(0).view.setBackgroundResource(R.drawable.home_btn);
                tabs.getTabAt(1).view.setBackgroundResource(R.drawable.habit_btn);
                tabs.getTabAt(2).view.setBackgroundResource(R.drawable.calendar_btn);
                tabs.getTabAt(3).view.setBackgroundResource(R.drawable.minigame_btn);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}