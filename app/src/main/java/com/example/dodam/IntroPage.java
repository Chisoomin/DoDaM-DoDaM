package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class IntroPage extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction ft;
    Intro intro;
    Intro_1 intro_1;
    Intro_2 intro_2;
    Intro_3 intro_3;
    Intro_4 intro_4;
    Intro_pwd intro_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        fm = getSupportFragmentManager();
        intro = new Intro();
        intro_1 = new Intro_1();
        intro_2 = new Intro_2();
        intro_3 = new Intro_3();
        intro_4 = new Intro_4();
        intro_pwd = new Intro_pwd();

        ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.container, intro_1);
        ft.commit();

    }
}
