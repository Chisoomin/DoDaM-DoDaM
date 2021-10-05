package com.example.dodamver2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PlayList extends YouTubeBaseActivity {
    // 1
    YouTubePlayerView playerView;
    YouTubePlayer player;
    YouTubePlayer.OnInitializedListener listener;
    LinearLayout youtubeLinearLayout, textLinearLayout;
    String happyStr, badStr, sadStr, moodNum;
    Integer happyInt, badInt, sadInt;
    TextView moodPlaylist, musicPlaylist;

    TextView numberPlaylist;
    ListView playlistCustom;

    ImageView homeImageView;

    // 유튜브 API KEY
    private static String API_KEY = "AIzaSyAbJ7W-mkjLm5aYR0BuqWSvOq9pkAISOAg";

    // 동영상 ID 변수 설정
    // ex) https://www.youtube.com/watch?v=Ys4Oq27H0Yc에서 v= 다음 부분이 videoId
    public static String videoId = "";

    // ErrorDialog 설정(초기화 설정 오류시 사용, getErrorDialog)
    private static int RQS_ErrorDialog = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_play_list );

        // 2
        playerView = (YouTubePlayerView) findViewById( R.id.youTubePlayerView );
        youtubeLinearLayout = (LinearLayout) findViewById( R.id.youtubeLinearLayout );
        textLinearLayout = (LinearLayout) findViewById( R.id.textLinearLayout );
        moodPlaylist = (TextView) findViewById( R.id.moodPlayList );
        musicPlaylist = (TextView) findViewById( R.id.musicPlayList );

        numberPlaylist = (TextView) findViewById( R.id.numberPlaylist );
        playlistCustom = (ListView) findViewById( R.id.playlistCustom );

        homeImageView = (ImageView) findViewById( R.id.homeImageView );

        // 처음 안보이게 설정
        playerView.setVisibility( View.GONE );
        numberPlaylist.setVisibility( View.GONE );

        // youtube, textView 배경색 지정, DB 설정
        DiaryDBHelper diaryDBHelper = new DiaryDBHelper( this );
        SQLiteDatabase youtubeDB = diaryDBHelper.getReadableDatabase();

        // 오늘 날짜 포맷
        Date listCurrentTime = Calendar.getInstance().getTime();
        String listDate = new SimpleDateFormat( "yyyy-MM-dd", Locale.getDefault() ).format( listCurrentTime );

        // 오늘 날짜를 DB에서 검색해서 기분 정도 알아내기
        // Cursor diaryCursor = youtubeDB.rawQuery( "select happy, bad, sad from DiaryData where date like '%"+listDate+"%';", null );

        // 테스트 용, 오늘 날짜를 DB에서 검색해서 기분 정도 알아내기
        //Cursor diaryCursor = youtubeDB.rawQuery( "select happy, bad, sad from DiaryData where date like '2021-06-01';", null );

        //날짜 바꿔보기
        Cursor diaryCursor = youtubeDB.rawQuery( "select happy, bad, sad, date from DiaryData;", null );


        while (diaryCursor.moveToNext()) {
            happyStr = diaryCursor.getString( 0 );
            badStr = diaryCursor.getString( 1 );
            sadStr = diaryCursor.getString( 2 );
        }

        youtubeDB.close();

        happyInt = Integer.parseInt( happyStr );
        badInt = Integer.parseInt( badStr );
        sadInt = Integer.parseInt( sadStr );

        // youtube, textView 배경색 지정 메소드
        youtubeBackgroundColor();

        // 나의 몇 번째 플레이리스트 이름 설정
        numberPlaylist.setText( "나의 " + happyInt + badInt + sadInt + "번째 플레이리스트" );

        // 기분 숫자
        // moodNum = happyStr + badStr + sadStr;

        // moodNum, 기분별 음악 추천 코드 메소드
        moodNumStr();

        // playlist DB 설정
        PlayListDBHelper playListDBHelper = new PlayListDBHelper( this );
        SQLiteDatabase db = playListDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery( "select mood, music, artist, videoId from PlayListData where mood like '%" + moodNum + "%'", null );

        ArrayList<PlayListItem> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            PlayListItem playListItem = new PlayListItem();
            playListItem.mood = cursor.getString( 0 );
            playListItem.music = cursor.getString( 1 );
            playListItem.artist = cursor.getString( 2 );
            playListItem.videoId = cursor.getString( 3 );
            data.add( playListItem );
        }

        db.close();

        // playlist 어댑터 연결
        PlayListAdapter playListAdapter = new PlayListAdapter( this, R.layout.custom_item_playlist, data );
        playlistCustom.setAdapter( playListAdapter );

        // 3, 홈 화면 이동 이미지 버튼 리스너
        homeImageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        } );
    }

    // youtube, textView 배경색 지정 메소드
    private void youtubeBackgroundColor() {
        if (happyInt == badInt && badInt == sadInt && sadInt == happyInt && happyInt != 0)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#FFFFFF" ) );
        if (happyInt > badInt && happyInt > sadInt)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#006400" ) );
        if (badInt > happyInt && badInt > sadInt)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#FF3232" ) );
        if (sadInt > badInt && sadInt > happyInt)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#3232FF" ) );
        if (happyInt == badInt && happyInt > sadInt)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#FFDC3C" ) );
        if (happyInt == sadInt && happyInt > badInt)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#2B7089" ) );
        if (badInt == sadInt && badInt > happyInt)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#FF3CBB" ) );
        if (happyInt == badInt && badInt == 0 && sadInt == 0)
            youtubeLinearLayout.setBackgroundColor( Color.parseColor( "#000000" ) );
    }

    // youtube 관련 메소드
    private void youtubePlayer() {
        // 유튜브 리스너
        playerView.initialize( API_KEY, listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;

                if (!b)
                    player.cueVideo( videoId );

                player.setPlayerStateChangeListener( new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onLoaded(String id) {
                        player.loadVideo( videoId );
                    }

                    @Override
                    public void onAdStarted() {
                    }

                    @Override
                    public void onVideoStarted() {
                        player.play();
                    }

                    @Override
                    public void onVideoEnded() {
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {
                    }
                } );
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError()) {
                    // 초기화 설정 오류시 사용, getErrorDialog
                    youTubeInitializationResult.getErrorDialog( PlayList.this, RQS_ErrorDialog ).show();
                } else {
                    Toast.makeText( getApplicationContext(),
                            "YouTubePlayer.onInitializationFailure(): " + youTubeInitializationResult.toString(),
                            Toast.LENGTH_LONG ).show();
                }
            }
        } );

    }

    // moodNum, 기분별 음악 추천 코드 메소드
    private void moodNumStr() {
        if (happyInt == badInt && badInt == sadInt && sadInt == happyInt && happyInt != 0)
            moodNum = "555";
        if (happyInt > badInt && happyInt > sadInt)
            moodNum = "500";
        if (badInt > happyInt && badInt > sadInt)
            moodNum = "050";
        if (sadInt > badInt && sadInt > happyInt)
            moodNum = "005";
        if (happyInt == badInt && happyInt > sadInt)
            moodNum = "550";
        if (happyInt == sadInt && happyInt > badInt)
            moodNum = "505";
        if (badInt == sadInt && badInt > happyInt)
            moodNum = "055";
        if (happyInt == badInt && badInt == 0 && sadInt == 0)
            moodNum = "000";
    }

    // playlist 어댑터
    class PlayListAdapter extends ArrayAdapter<PlayListItem> {
        Context context;
        int resource;
        ArrayList<PlayListItem> data;

        public PlayListAdapter(@NonNull Context context, int resource, ArrayList<PlayListItem> data) {
            super( context, resource );
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate( R.layout.custom_item_playlist, null );

            TextView title = (TextView) convertView.findViewById( R.id.titleTextView );
            TextView singer = (TextView) convertView.findViewById( R.id.singerTextView );
            ImageView mood = (ImageView) convertView.findViewById( R.id.playImageView );
            final PlayListItem playListItem = data.get( position );

            title.setText( playListItem.music );
            singer.setText( playListItem.artist );
            mood.setImageDrawable( ResourcesCompat.getDrawable( context.getResources(), R.drawable.playbutton, null ) );
            mood.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // textView 안보이게 설정, youtube & listView 관련 textView 보이게 설정
                    textLinearLayout.setVisibility( View.GONE );
                    playerView.setVisibility( View.VISIBLE );
                    numberPlaylist.setVisibility( View.VISIBLE );

                    // videoId 리스트마다 다르게 설정
                    videoId = playListItem.videoId;

                    // 유튜브 관련 메소드
                    youtubePlayer();
                }
            } );

            return convertView;
        }
    }
}
