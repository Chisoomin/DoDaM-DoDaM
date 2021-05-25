package com.example.dodam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class HabitTracker extends AppCompatActivity {
    //int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);

        ViewPager pager = (ViewPager)findViewById(R.id.view_pager1);
        MyPagerAdpater pagerAdapter = new MyPagerAdpater(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        /*final GridView gv = (GridView)findViewById(R.id.grid);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);*/
    }
    class MyPagerAdpater extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments;

        public MyPagerAdpater(@NonNull FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragments.add(new HabitOne());
            fragments.add(new HabitTwo());
            fragments.add(new HabitThree());
        }

        @Override
        public int getCount() {
            return 3;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
    /*public class MyGridAdapter extends BaseAdapter{
        Context context;
        public MyGridAdapter(Context c){
            context = c;
        }
        public int getCount(){
            return habittrackerID.length;
        }
        public Object getItem(int arg0){
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        Integer[] habittrackerID = { R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before, R.drawable.before, R.drawable.before,
                R.drawable.before };
        public View getView(int arg0, View arg1, ViewGroup arg2){
            final ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);


            final int pos = arg0;
            imageview.setImageResource(habittrackerID[pos]);


            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(i !=pos){
                        Toast.makeText(context,"차례로 클릭해 주세요", Toast.LENGTH_SHORT).show();
                    }else{
                        imageview.setImageResource(R.drawable.after);
                        i++;
                    }
                }
            });
            return imageview;
        }
    }*/
}
