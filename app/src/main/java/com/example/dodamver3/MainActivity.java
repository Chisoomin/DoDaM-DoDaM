package com.example.dodamver3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
        switch (item.getItemId()) {
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

    //MiniGame miniGame;


    public void changeFragment(int index) {
        switch (index) {
            case 1:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.main_container, miniGame).commit();
                break;*/

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //ft.replace(R.id.main_container, miniGame);
                ft.commit();
            case 2:
                //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment_menu).commit();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("DoDaMDoDaM");



        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setId(0));
        tabs.addTab(tabs.newTab().setId(1));
        tabs.addTab(tabs.newTab().setId(2));
        tabs.addTab(tabs.newTab().setId(3));
        tabs.addTab(tabs.newTab().setId(4));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //tabs.getTabAt(0).view.setBackgroundResource(R.drawable.home_btn);
        tabs.getTabAt(0).view.setBackgroundResource(R.drawable.calendar_btn);
        tabs.getTabAt(1).view.setBackgroundResource(R.drawable.habit_btn);
        tabs.getTabAt(2).view.setBackgroundResource(R.drawable.home_btn);
        tabs.getTabAt(3).view.setBackgroundResource(R.drawable.statistics_btn);
        tabs.getTabAt(4).view.setBackgroundResource(R.drawable.foryou_btn);

        //tabs.getTabAt(2).view.setBackgroundResource(R.drawable.open_btn);
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        tabs.getTabAt(2).select();
                    }
                }, 0);


        //Adapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final AdapterforTab myPagerAdapter = new AdapterforTab(getSupportFragmentManager(), 5);
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
                tabs.getTabAt(0).view.setBackgroundResource(R.drawable.calendar_btn);
                tabs.getTabAt(1).view.setBackgroundResource(R.drawable.habit_btn);
                tabs.getTabAt(2).view.setBackgroundResource(R.drawable.home_btn);
                tabs.getTabAt(3).view.setBackgroundResource(R.drawable.statistics_btn);
                tabs.getTabAt(4).view.setBackgroundResource(R.drawable.foryou_btn);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}