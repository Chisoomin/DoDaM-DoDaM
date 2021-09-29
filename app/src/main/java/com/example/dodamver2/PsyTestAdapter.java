package com.example.dodamver2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class PsyTestAdapter extends RecyclerView.Adapter<PsyTestAdapter.TestViewHolder> {
    ArrayList<test_getset> list = new ArrayList<test_getset>();

    public PsyTestAdapter(ArrayList<test_getset> listt) {
        this.list = listt;
    }


    @NonNull
    @NotNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_recycle, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TestViewHolder holder, int position) {
        holder.question.setText(list.get(position).getQues());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        TextView question;

        public TestViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.textView15);


        }


    }
}