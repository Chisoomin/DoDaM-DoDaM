package com.example.dodam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MyGridAdapter extends BaseAdapter {
    int i = 0;
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
}
