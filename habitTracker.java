package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    MyGridAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String[] buttonNames = {
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",

        };
        gridView = (GridView) findViewById(R.id.habitTracker);
        adapter = new MyGridAdapter(this, buttonNames);
        gridView.setAdapter(adapter);


    }
    public class MyGridAdapter extends BaseAdapter {

        Context context = null;

        String[] buttonNames = null;


        public MyGridAdapter(Context context, String[] buttonNames) {
            this.context = context;
            this.buttonNames = buttonNames;
        }


        @Override
        public int getCount() {
            return (null != buttonNames) ? buttonNames.length : 0;
        }

        @Override
        public Object getItem(int position) {
            return buttonNames[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Button button = null;

            if (null != convertView) {
                button = (Button)convertView;
            }

            else {
                //------------------------------------------------------------
                // 버튼을 생성하고 그것의 이름을 정합니다.
                button = new Button(context);
                button.setText(buttonNames[position]);
                button.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_box));

                // 버튼 클릭에 대한 처리

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_check));
                    }
                });


            }

            return button;
        }

    }


}
