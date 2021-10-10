package com.example.dodamver2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ForYou extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int REQUEST_CODE;
    private int RESULT_OK;

    private String mParam1;
    private String mParam2;

    public ForYou() {
        // Required empty public constructor
    }

    public static ForYou newInstance(String param1, String param2) {
        ForYou fragment = new ForYou();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    // 1
    ImageView profileImage;
    TextView profileName;

    RecyclerView playlistRecyclerView;
    ArrayList<PlayListRecyclerView> list;

    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_for_you, container, false );

        // 2
        profileImage = (ImageView) view.findViewById( R.id.profileImage );
        profileName = (TextView) view.findViewById( R.id.profileName );

        playlistRecyclerView = (RecyclerView) view.findViewById( R.id.playlistRecyclerView );

        // DB, 프로필 이름 나타내기
        DBHelper dbHelper = new DBHelper( getContext() );
        SQLiteDatabase myDB = dbHelper.getWritableDatabase();

        Cursor nameCursor = myDB.rawQuery( "select name from Dodam", null );
        while (nameCursor.moveToNext()) {
            name = nameCursor.getString( 0 );
        }

        profileName.setText( name );

        // 플레이리스트
        // DB
        PlayListDBHelper playListDBHelper = new PlayListDBHelper( getContext() );
        SQLiteDatabase playDB = playListDBHelper.getWritableDatabase();

        Cursor cursor = playDB.rawQuery( "select music, artist from PlayListData", null );

        ArrayList<PlayListRecyclerView> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            PlayListRecyclerView listItem = new PlayListRecyclerView();
            listItem.titleText = cursor.getString( 0 );
            listItem.singerText = cursor.getString( 1 );
            data.add( listItem );
        }

        playDB.close();

/*
        // 어댑터 만들기
        PlayListRecyclerViewAdapter adapter = new PlayListRecyclerViewAdapter(list);

        // 리스트뷰에 어댑터 연결
        playlistRecyclerView.setAdapter( adapter );

        // 레이아웃 설정
        playlistRecyclerView.setLayoutManager( new LinearLayoutManager( this,RecyclerView.HORIZONTAL, false ) );
 */
        // 3
        /*
        // 프로필 사진 누르면 갤러리에서 선택
        profileImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                getActivity().startActivityForResult( intent, REQUEST_CODE );
            }
        } );
        */

        return view;
    }

    // 프로필 사진 누르면 갤러리에서 선택
    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //이미지 뷰를 클릭하면 시작되는 함수

        if(requestCode== REQUEST_CODE && resultCode==RESULT_OK && data!=null) {
            //response에 getData , return data 부분 추가해주어야 한다

            selectedImage = data.getData();
            Uri photoUri = data.getData();
            Bitmap bitmap = null;
            //bitmap 이용
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //이미지뷰에 이미지 불러오기
            profileImage.setImageBitmap(bitmap);

            //아래 커서 이용해서 사진의 경로 불러오기
            Cursor cursor = getContentResolver().query( Uri.parse(selectedImage.toString()), null, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            Log.d("경로 확인 >> ", "$selectedImg  /  $absolutePath");

        }else{
            Toast.makeText(getContext(), "사진 업로드 실패", Toast.LENGTH_LONG).show();
        }
    }
    */

    class MyListAdapter extends ArrayAdapter<PlayListRecyclerView> {
        Context context;
        int resource;
        ArrayList<PlayListRecyclerView> data;

        public MyListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PlayListRecyclerView> data) {
            super( context, resource, data );
            this.context = context;
            this.resource = resource;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // 뷰 파일 inflate
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate( R.layout.playlist_recycler, null );

            ImageView thumbnail = (ImageView) convertView.findViewById( R.id.thumbImage );
            TextView title = (TextView) convertView.findViewById( R.id.titleText );
            TextView singer = (TextView) convertView.findViewById( R.id.singerText );

            PlayListRecyclerView item = data.get( position );

            // 각 뷰의 요소에 값 입력
            title.setText( item.titleText );
            singer.setText( item.singerText );

            return convertView;
        }
    }
}