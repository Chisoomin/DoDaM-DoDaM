package com.example.dodamver3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ViewHolder> {
    private ArrayList<ForYouItem> mList;

    public ForYouAdapter(ArrayList<ForYouItem> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View view = inflater.inflate( R.layout.playlist_recycler, parent, false );
        ForYouAdapter.ViewHolder viewHolder = new ForYouAdapter.ViewHolder( view );
        return viewHolder;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForYouItem item = mList.get( position );

        if (item.happyInte == item.badInte && item.badInte == item.sadInte && item.sadInte == item.happyInte && item.happyInte != 0)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#FFFFFF" ) );
        if (item.happyInte > item.badInte && item.happyInte > item.sadInte)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#006400" ) );
        if (item.badInte > item.happyInte && item.badInte > item.sadInte)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#FF3232" ) );
        if (item.sadInte > item.badInte && item.sadInte > item.happyInte)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#3232FF" ) );
        if (item.happyInte == item.badInte && item.happyInte > item.sadInte)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#FFDC3C" ) );
        if (item.happyInte == item.sadInte && item.happyInte > item.badInte)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#2B7089" ) );
        if (item.badInte == item.sadInte && item.badInte > item.happyInte)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#FF3CBB" ) );
        if (item.happyInte == item.badInte && item.badInte == 0 && item.sadInte == 0)
            holder.thumbLayout.setBackgroundColor( Color.parseColor( "#000000" ) );

        byte[] b = item.getAlbumImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray( b, 0, b.length );

        holder.songLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "https://www.youtube.com/watch?v=" + item.getVideoId() ) );
                context.startActivity(intent);
            }
        } );
        holder.thumb.setImageBitmap( bitmap );
        holder.music.setText( item.getMusic() );
        holder.artist.setText( item.getArtist() );
    }

    @Override
    public int getItemCount() {
        // return mList == null ? 0 : mList.size();
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout songLayout;
        LinearLayout thumbLayout;
        ImageView thumb;
        TextView music;
        TextView artist;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            songLayout = (ConstraintLayout) itemView.findViewById( R.id.songLayout );
            thumbLayout = (LinearLayout) itemView.findViewById( R.id.thumbLayout );
            thumb = (ImageView) itemView.findViewById( R.id.thumbImage );
            music = (TextView) itemView.findViewById( R.id.titleText );
            artist = (TextView) itemView.findViewById( R.id.singerText );
        }
    }
}
