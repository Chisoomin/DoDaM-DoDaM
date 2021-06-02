package com.example.dodam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MyGridAdapter extends BaseAdapter {
    int i = 0;

    Context context;
    String[] buttonNames;

    public MyGridAdapter(Context c, String[] buttonNames) {
        this.context = c;
        this.buttonNames = buttonNames;
    }


    public int getCount() {
        return (null != buttonNames) ? buttonNames.length : 0;
    }

    public Object getItem(int arg0) {
        return buttonNames[arg0];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    Integer[] habittrackerID = {R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before};

    public View getView(int arg0, View arg1, ViewGroup arg2) {
        final ImageView imageview = new ImageView(context);
        /*imageview.setLayoutParams(new GridView.LayoutParams(200, 200));
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
        return imageview;*/

        Button button=null;
        final int pos = arg0;

        if (null != arg1) {
            button = (Button)arg1;
        } else {
            //------------------------------------------------------------
            // 버튼을 생성하고 그것의 이름을 정합니다.
            button = new Button(context);
            button.setText(buttonNames[arg0]);
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_box));

            // 버튼 클릭에 대한 처리

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i != pos) {
                        Toast.makeText(context, "차례로 클릭해 주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        v.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_check));
                        i++;
                    }
                }
            });


        }

        return button;

    }
}
