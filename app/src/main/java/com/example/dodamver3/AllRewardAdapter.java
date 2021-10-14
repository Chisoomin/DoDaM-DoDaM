package com.example.dodamver3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllRewardAdapter extends RecyclerView.Adapter<AllRewardAdapter.ViewHolder> {
    private ArrayList<RewardItem> mList;

    public AllRewardAdapter(ArrayList<RewardItem> mList) {
        this.mList = mList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View view = inflater.inflate( R.layout.all_reward_recycler, parent, false );
        AllRewardAdapter.ViewHolder viewHolder = new AllRewardAdapter.ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllRewardAdapter.ViewHolder holder, int position) {
        RewardItem item = mList.get( position );

        byte[] b = item.getRewardImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray( b, 0, b.length );

        holder.allRewardImage.setImageBitmap( bitmap );
        holder.allRewardExp.setText( item.getRewardExp() );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView allRewardExp;
        ImageView allRewardImage;

        public ViewHolder(View view) {
            super(view);

            allRewardExp = (TextView) view.findViewById( R.id.allRewardExp );
            allRewardImage = (ImageView) view.findViewById( R.id.allRewardImage );
        }
    }
}
