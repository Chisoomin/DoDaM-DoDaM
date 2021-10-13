package com.example.dodamver3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {
    private ArrayList<RewardItem> mList;

    public RewardAdapter(ArrayList<RewardItem> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View view = inflater.inflate( R.layout.reward_recycler, parent, false );
        RewardAdapter.ViewHolder viewHolder = new RewardAdapter.ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RewardItem item = mList.get( position );

        byte[] b = item.getRewardImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray( b, 0, b.length );

        holder.rewardImage.setImageBitmap( bitmap );
        holder.rewardList.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( view.getContext(), '"' + item.getRewardExp() + '"', Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout rewardList;
        ImageView rewardImage;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            rewardList = (ConstraintLayout) itemView.findViewById( R.id.rewardList );
            rewardImage = (ImageView) itemView.findViewById( R.id.rewardImage );
        }
    }
}
