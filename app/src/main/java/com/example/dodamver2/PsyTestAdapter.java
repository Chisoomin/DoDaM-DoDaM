package com.example.dodamver2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class PsyTestAdapter extends RecyclerView.Adapter<PsyTestAdapter.TestViewHolder> {
    ArrayList<test_getset> list = new ArrayList<test_getset>();
    Button btn_result;
    RadioButton day1, day12,day34, day57, week2;

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public PsyTestAdapter(ArrayList<test_getset> listt) {
        this.list = listt;
    }

    @NonNull
    @NotNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_recycle, parent, false);
        btn_result = view.findViewById(R.id.result);
        day1 = view.findViewById(R.id.day1);
        day12 = view.findViewById(R.id.day12);
        day34 = view.findViewById(R.id.day34);
        day57 = view.findViewById(R.id.day57);
        week2 = view.findViewById(R.id.week2);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TestViewHolder holder, int position) {
        holder.question.setText(list.get(position).getQues());

        //버튼 클릭할 때, 저장할 수 있도록 db 상의
        if(position==19){
            btn_result.setVisibility(View.VISIBLE);
        }
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