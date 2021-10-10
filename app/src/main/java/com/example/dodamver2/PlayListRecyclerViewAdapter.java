package com.example.dodamver2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayListRecyclerViewAdapter extends RecyclerView.Adapter<PlayListRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<String> list;
    public OnMyTouchListener listener = null;

    public PlayListRecyclerViewAdapter(ArrayList<String> list) {
        this.list = list;
    }

    public interface OnMyTouchListener{
        void onTouch(View v, int position);
    }

    public void setOnMyTouchListener(OnMyTouchListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListRecyclerViewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView singerText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById( R.id.titleText );
            singerText = itemView.findViewById( R.id.singerText );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    int position = getAdapterPosition(); // 중요
                    // null이 아닐 때의 onTouch 리스너를 불러주는 역할
                    if(listener != null)
                        listener.onTouch(v, position);

                     */
                }
            } );
        }
    }

}
