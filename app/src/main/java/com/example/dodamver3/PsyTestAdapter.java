package com.example.dodamver3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class PsyTestAdapter extends RecyclerView.Adapter<PsyTestAdapter.TestViewHolder> {
    ArrayList<test_getset> list = new ArrayList<test_getset>();
    Context context;

    private  callback_answer callback;

    public void setOncallback_answerListener(callback_answer callback_answerListener) {
        this.callback = callback_answerListener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public PsyTestAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_recycle, parent, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TestViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.question.setText(list.get(position).getNumid() + ". " + list.get(position).getQues());

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                callback.callback_answer(list.get(position).getNumid(), holder.day1.isChecked()+"/"+holder.day12.isChecked()+"/"+holder.day34.isChecked()+"/"+holder.day57.isChecked());
            }
        });
    }

    public void addItem(test_getset test_getset) {
        list.add(test_getset);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        RadioGroup radioGroup;
        RadioButton day1, day12, day34, day57;
        TextView question;

        public TestViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.textView15);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            day1 = itemView.findViewById(R.id.day1);
            day12 = itemView.findViewById(R.id.day12);
            day34 = itemView.findViewById(R.id.day34);
            day57 = itemView.findViewById(R.id.day57);
        }


    }
}