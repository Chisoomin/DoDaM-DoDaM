package com.example.dodamver3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class habitAdapter extends RecyclerView.Adapter<habitAdapter.habitViewHolder> implements ItemTouchHelperListener {
    ArrayList<habit_getset> list = new ArrayList<habit_getset>();

    public habitAdapter(ArrayList<habit_getset> list) {
        this.list = list;
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
    }


    @Override
    public int getItemCount() {
        return list.size();
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
        //왼쪽 버튼 클릭시 일어날일
    }




    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        //
    }

    public class habitViewHolder extends RecyclerView.ViewHolder {
        TextView habit, habitdate;

        public habitViewHolder(@NonNull View itemView) {
            super(itemView);
            habit = itemView.findViewById(R.id.habit);
            habitdate = itemView.findViewById(R.id.habitdate);


        }
    }
}
