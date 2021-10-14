package com.example.dodamver3;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllRewardAdapter extends RecyclerView.Adapter<AllRewardAdapter.ViewHolder> {
    private ArrayList<RewardItem> mList;
    Dialog dialog;

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

        dialog = new Dialog( holder.itemView.getContext() );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.mypage_custom_dialog );

        holder.allRewardImage.setImageBitmap( bitmap );
        holder.allRewardExp.setText( item.getRewardExp() );
        holder.allRewardImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                TextView explanationTitle = dialog.findViewById( R.id.explanationTitle );
                explanationTitle.setText( item.getRewardExp() );

                TextView explanationText = dialog.findViewById( R.id.explanationText );
                explanationText.setText( item.getRewardDetailExp() );

                Button explanationButton = dialog.findViewById( R.id.explanationButton );
                explanationButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                } );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout allRewardLayout;
        TextView allRewardExp;
        ImageView allRewardImage;

        public ViewHolder(View view) {
            super( view );

            allRewardExp = (TextView) view.findViewById( R.id.allRewardExp );
            allRewardImage = (ImageView) view.findViewById( R.id.allRewardImage );
        }
    }
}
