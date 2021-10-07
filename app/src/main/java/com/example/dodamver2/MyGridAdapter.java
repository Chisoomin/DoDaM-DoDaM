package com.example.dodamver2;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import static com.example.dodamver2.MainScreen.MY_ACTION;

class MyGridAdapter extends BaseAdapter {
    int i = 0;
    int j =0;
    Context context;
    String[] buttonNames;
    Integer position;
    Integer step;
    String numId;

    public MyGridAdapter(Context c, String[] buttonNames, int position) {
        this.context = c;
        this.buttonNames = buttonNames;
        this.position = position;
        //this.step = step;
        // this.numId = numId;
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
    int savestep;
    /*Integer[] habittrackerID = {R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before, R.drawable.before, R.drawable.before,
            R.drawable.before};*/

    public View getView(final int arg0, View arg1, ViewGroup arg2) {
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


        Button button = null;
        final int pos = arg0;

        if (null != arg1) {
            button = (Button) arg1;
        } else {
            //------------------------------------------------------------
            // 버튼을 생성하고 그것의 이름을 정합니다.
            /*button = new Button(context);
            button.setText(buttonNames[arg0]);
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_check_2));*/
            button = new Button(context);
            button.setText(buttonNames[arg0]);
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_box));

            HabitDBHelper helper3 = new HabitDBHelper(context);
            SQLiteDatabase db5 = helper3.getReadableDatabase();
            SQLiteDatabase db6 = helper3.getWritableDatabase();
            Cursor cursor3 = db5.rawQuery("select numId, goal, step from HabitData;", null);
            while (cursor3.moveToNext()) {
                if (Integer.valueOf(cursor3.getString(0)) == position + 1) {
                    savestep = cursor3.getInt(2);
                    i=savestep;
                }
            }
            if (j < savestep) {
                button.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_check_2));
                j++;
            } else {
                button.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_box));
            }


            // 버튼 클릭에 대한 처리

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i != arg0) {
                        Toast.makeText(context, "차례로 클릭해 주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        v.setBackground(ContextCompat.getDrawable(context, R.drawable.rr_habit_check_2));
                        DBHelper helper = new DBHelper(context);
                        SQLiteDatabase db = helper.getReadableDatabase();
                        SQLiteDatabase db2 = helper.getWritableDatabase();

                        Cursor cursor = db.rawQuery("select point, _id from Dodam;", null);
                        while (cursor.moveToNext()) {
                            Integer id = cursor.getInt(1);
                            if (id == 2) {
                                Integer SetPoint = cursor.getInt(0);
                                String pointUp = "UPDATE Dodam SET point=" + (SetPoint + 1);
                                db2.execSQL(pointUp);
                            }
                        }

                        Log.e("포지션이 대체 뭐라뜨른ㄴ거야", position + " ");
                        HabitDBHelper helper2 = new HabitDBHelper(context);
                        SQLiteDatabase db3 = helper2.getReadableDatabase();
                        SQLiteDatabase db4 = helper2.getWritableDatabase();
                        Cursor cursor2 = db3.rawQuery("select numId, goal, step from HabitData;", null);
                        while (cursor2.moveToNext()) {
                            if (Integer.valueOf(cursor2.getString(0)) == position + 1) {
                                int SetStep = cursor2.getInt(2);
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("step", SetStep + 1);
                                //String StepUp = "UPDATE HabitData SET step =" + (SetStep + 1);
                                //db4.update("HabitData", contentValues,"step = ? and numId = ?",new String[]{String.valueOf(SetStep+1), String.valueOf((position+1))});
                                String StepUp = "UPDATE HabitData SET step = '"+ (SetStep+1) +"' WHERE numId = '"+ (position + 1)+"'";
                                db4.execSQL(StepUp);
                            }
                        }

                        i++;
                        if (i == 1) {
                            Toast.makeText(context, "시작이 반이에요. 화이팅!", Toast.LENGTH_LONG).show();
                        }
                        if (i == 15) {
                            Toast.makeText(context, "벌써 반이나 왔어요. 힘내세요!!", Toast.LENGTH_LONG).show();
                        }
                        if (i == 30) {
                            Toast.makeText(context, "고생했어요. 30일 달성!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

        }

        return button;

    }

}
