package com.example.dodamver3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    boolean isDrawerOpened;

    TextView navViewName;
    String name;

    // Drawer이 열렸을 때 뒤로가기 누르면 닫히는 기능
    @Override
    public void onBackPressed() {
        if(isDrawerOpened) {
            drawerLayout.closeDrawer( Gravity.LEFT );
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu( menu );
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected( item );
        switch (item.getItemId()) {
            case R.id.logout:
                Intent Intro = new Intent( getApplicationContext(), IntroPage.class );
                startActivity( Intro );
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer( GravityCompat.START );
        }
        return super.onOptionsItemSelected( item );
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
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // 툴바 설정
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        drawerLayout = (DrawerLayout) findViewById( R.id.drawerLayout );
        navigationView = (NavigationView) findViewById( R.id.navigationView );

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled( false );
        actionBar.setDisplayShowCustomEnabled( true ); //커스터마이징 하기 위해 필요
        actionBar.setDisplayHomeAsUpEnabled(true); //툴바 메뉴버튼 생성
        actionBar.setHomeAsUpIndicator(R.drawable.menu); // 메뉴 버튼 모양 설정

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened( drawerView );
                isDrawerOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed( drawerView );
                isDrawerOpened = false;
            }
        };

        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBar.setDisplayHomeAsUpEnabled( true );
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.playlistBar:
                        Intent playlistIntent = new Intent(getApplicationContext(), PlayList.class);
                        startActivity( playlistIntent );
                    case R.id.rewardBar:
                        // Intent rewardIntent = new Intent(getApplicationContext(), .class);
                        // startActivity( rewardIntent );
                    case R.id.settingsBar:
                        // Intent settingsIntent = new Intent(getApplicationContext(), .class);
                        // startActivity( settingsIntent );
                }
                drawerLayout.closeDrawer( GravityCompat.START );
                return true;
            }
        } );

        // 툴바 설정, 프로필 이름 나타내기
        View header = navigationView.getHeaderView( 0 );

        navViewName = (TextView) header.findViewById( R.id.navViewName );

        DBHelper dbHelper = new DBHelper( getApplicationContext() );
        SQLiteDatabase myDB = dbHelper.getWritableDatabase();

        Cursor nameCursor = myDB.rawQuery( "select name from Dodam", null );
        while (nameCursor.moveToNext()) {
            name = nameCursor.getString( 0 );
        }

        navViewName.setText( name );

        myDB.close();


        // 탭
        TabLayout tabs = findViewById( R.id.tabs );
        tabs.addTab( tabs.newTab().setId( 0 ) );
        tabs.addTab( tabs.newTab().setId( 1 ) );
        tabs.addTab( tabs.newTab().setId( 2 ) );
        tabs.addTab( tabs.newTab().setId( 3 ) );
        tabs.addTab( tabs.newTab().setId( 4 ) );
        tabs.setTabGravity( tabs.GRAVITY_FILL );

        //tabs.getTabAt(0).view.setBackgroundResource(R.drawable.home_btn);
        tabs.getTabAt( 0 ).view.setBackgroundResource( R.drawable.calendar_btn );
        tabs.getTabAt( 1 ).view.setBackgroundResource( R.drawable.habit_btn );
        tabs.getTabAt( 2 ).view.setBackgroundResource( R.drawable.home_btn );
        tabs.getTabAt( 3 ).view.setBackgroundResource( R.drawable.statistics_btn );
        tabs.getTabAt( 4 ).view.setBackgroundResource( R.drawable.foryou_btn );

        //tabs.getTabAt(2).view.setBackgroundResource(R.drawable.open_btn);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        tabs.getTabAt( 2 ).select();
                    }
                }, 0 );


        //Adapter
        final ViewPager viewPager = (ViewPager) findViewById( R.id.viewpager );
        final AdapterforTab myPagerAdapter = new AdapterforTab( getSupportFragmentManager(), 5 );
        viewPager.setAdapter( myPagerAdapter );



        //탭 선택 이벤트
        tabs.addOnTabSelectedListener( new TabLayout.ViewPagerOnTabSelectedListener( viewPager ) );
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabs ) );
        tabs.setOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabs.getTabAt( tab.getPosition() ).view.setBackgroundResource( R.drawable.open_btn );
                myPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabs.getTabAt( 0 ).view.setBackgroundResource( R.drawable.calendar_btn );
                tabs.getTabAt( 1 ).view.setBackgroundResource( R.drawable.habit_btn );
                tabs.getTabAt( 2 ).view.setBackgroundResource( R.drawable.home_btn );
                tabs.getTabAt( 3 ).view.setBackgroundResource( R.drawable.statistics_btn );
                tabs.getTabAt( 4 ).view.setBackgroundResource( R.drawable.foryou_btn );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
    }
}