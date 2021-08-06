package com.example.dodamver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static com.example.dodamver2.MainScreen.MY_ACTION;

class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    Context c;
    ArrayList<Habit_getset> list = new ArrayList<Habit_getset>();
    final String[] buttonNames = {
            "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",

    };
    HabitAdapter(Context c, ArrayList<Habit_getset> list) {
        this.c = c;
        this.list = list;
    }


    @Override
    public HabitAdapter.HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_layout, parent, false);
        return new HabitAdapter.HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HabitAdapter.HabitViewHolder holder, int position) {
        holder.goal.setText(list.get(position).getGoal());
        MyGridAdapter gAdapter = new MyGridAdapter(c, buttonNames, position);
        holder.gridView.setAdapter(gAdapter);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView goal;
        GridView gridView;
        public HabitViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            goal = itemView.findViewById(R.id.goal);
            gridView = itemView.findViewById(R.id.habitTracker);

        }

    }

}