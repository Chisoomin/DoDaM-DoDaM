package com.example.dodamver3;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class habitAdapter extends RecyclerView.Adapter<habitAdapter.habitViewHolder> implements ItemTouchHelperListener {
    ArrayList<habit_getset> list = new ArrayList<habit_getset>();
    View view;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    Context context;

    /*public habitAdapter(ArrayList<habit_getset> list, View view) {
        this.list = list;
        this.view = view;
    }*/
    public habitAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public habitAdapter.habitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_layout, parent, false);
        return new habitViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull habitAdapter.habitViewHolder holder, int position) {
        holder.habit.setText(list.get(position).getHabit_num() + ". " + list.get(position).getHabit());
        holder.habitdate.setText(list.get(position).getDate() + " 시작");
        holder.habit_background.setBackgroundColor(list.get(position).getColor());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(habit_getset habit_getset) {
        list.add(habit_getset);
    }

    public void clearItem(){
        list.clear();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        habit_getset habit_gs = list.get(from_position);
        list.remove(from_position);
        list.add(to_position, habit_gs);

        notifyItemMoved(from_position, to_position);
        return true;
    }



    @Override
    public void onItemSwipe(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {
        //수정 나중 구현

    }

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        HabitAchieveDB habitAchieveDB = new HabitAchieveDB(context);
        SQLiteDatabase db = habitAchieveDB.getWritableDatabase();
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        String date = mFormat.format(mDate);
        String query = "insert into HabitAchieve(numId, date, bColor) values('" + list.get(position).getHabit_num() + "', '" + date + "', '" + list.get(position).getColor() + "')";
        db.execSQL(query);
        db.close();
        list.remove(position);
        notifyItemRemoved(position);

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db2 = dbHelper.getWritableDatabase();
        SQLiteDatabase db3 = dbHelper.getReadableDatabase();
        int point = 0;
        Cursor cursor = db3.rawQuery("select _id, point from Dodam;", null);
        while (cursor.moveToNext()){
            if(cursor.getInt(0)==2){
                point = cursor.getInt(1);
            }
        }
        String update = "UPDATE Dodam SET point = '" + (point+1) + "' WHERE _id = '" + 2 + "'";
        db2.execSQL(update);
        Log.e("d", "왜 안되냐       "+position);
    }


    public class habitViewHolder extends RecyclerView.ViewHolder {
        TextView habit, habitdate;
        ConstraintLayout habit_background;

        public habitViewHolder(@NonNull View itemView) {
            super(itemView);
            habit = itemView.findViewById(R.id.habit);
            habitdate = itemView.findViewById(R.id.habitdate);
            habit_background = itemView.findViewById(R.id.habit_background);

        }
    }
}
