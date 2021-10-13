package com.example.dodamver3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.internal.d;

import java.io.ByteArrayOutputStream;
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

    String name;
    Integer point;
    Integer cell;

    RecyclerView rewardRecyclerView;

    Dialog dialog;
    ImageButton rewardButton, playlistButton, gameButton;

    String happyStr, badStr, sadStr, moodNum;
    Integer happyInt, badInt, sadInt;
    RecyclerView playlistRecyclerView;

    ImageButton eggButton, moleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_for_you, container, false );

        // 2
        profileImage = (ImageView) view.findViewById( R.id.profileImage );
        profileName = (TextView) view.findViewById( R.id.profileName );

        rewardButton = (ImageButton) view.findViewById( R.id.questionMark1 );
        playlistButton = (ImageButton) view.findViewById( R.id.questionMark2 );
        gameButton = (ImageButton) view.findViewById( R.id.questionMark3 );

        rewardRecyclerView = (RecyclerView) view.findViewById( R.id.rewardRecyclerView );

        playlistRecyclerView = (RecyclerView) view.findViewById( R.id.playlistRecyclerView );

        eggButton = (ImageButton) view.findViewById( R.id.eggBtn );
        moleButton = (ImageButton) view.findViewById( R.id.moleBtn );

        // 커스텀 다이얼로그 (설명글)
        dialog = new Dialog( getContext() );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.mypage_custom_dialog );

        // DB, 프로필 이름 나타내기
        DBHelper dbHelper = new DBHelper( getContext() );
        SQLiteDatabase myDB = dbHelper.getWritableDatabase();

        Cursor nameCursor = myDB.rawQuery( "select name from Dodam", null );
        while (nameCursor.moveToNext()) {
            name = nameCursor.getString( 0 );
        }

        profileName.setText( name );

        myDB.close();

        // 업적
        rewardImage();

        RewardDBHelper rewardDBHelper = new RewardDBHelper( getContext() );
        SQLiteDatabase rewardDB = rewardDBHelper.getReadableDatabase();

        Cursor rewardCursor = rewardDB.rawQuery( "select image, exp from rewardData", null );
        ArrayList<RewardItem> rewardItemArrayList = new ArrayList<>();
        while (rewardCursor.moveToNext()) {
            RewardItem rewardItem = new RewardItem();
            rewardItem.rewardImage = rewardCursor.getBlob( 0 );
            rewardItem.rewardExp = rewardCursor.getString( 1 );
            rewardItemArrayList.add( rewardItem );
        }

        rewardDB.close();

        // 업적 어댑터 연결
        RewardAdapter rewardAdapter = new RewardAdapter( rewardItemArrayList );
        rewardRecyclerView.setAdapter( rewardAdapter );
        rewardRecyclerView.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false ) );

        // 플레이리스트
        // DB 설정
        DiaryDBHelper diaryDBHelper = new DiaryDBHelper( getContext() );
        SQLiteDatabase youtubeDB = diaryDBHelper.getReadableDatabase();

        // 기분 정도
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

        // moodNumStr(), 기분별 음악 추천 코드 메소드
        moodNumStr();

        // playlist DB 설정
        albumImage();

        PlayListDBHelper playListDBHelper = new PlayListDBHelper( getContext() );
        SQLiteDatabase db = playListDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery( "select music, artist, albumImage, videoId from PlayListData where mood like '%" + moodNum + "%'", null );
        ArrayList<ForYouItem> mList = new ArrayList<>();
        while (cursor.moveToNext()) {
            ForYouItem forYouItem = new ForYouItem();
            forYouItem.music = cursor.getString( 0 );
            forYouItem.artist = cursor.getString( 1 );
            forYouItem.albumImage = cursor.getBlob( 2 );
            forYouItem.videoId = cursor.getString( 3 );
            forYouItem.happyInte = happyInt;
            forYouItem.badInte = badInt;
            forYouItem.sadInte = sadInt;
            mList.add( forYouItem );
        }

        db.close();

        // playlist 어댑터 연결
        ForYouAdapter forYouAdapter = new ForYouAdapter( mList );
        playlistRecyclerView.setAdapter( forYouAdapter );
        playlistRecyclerView.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false ) );

        // 3
        // 게임 리스너 연결
        eggBtnListener();
        moleBtnListener();

        // 버튼: 커스텀 다이얼로그
        // 업적 커스텀 다이얼로그
        rewardButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rewardExplanation();
            }
        } );

        // 플레이리스트 커스텀 다이얼로그
        playlistButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlistExplanation();
            }
        } );

        // 게임 커스텀 다이얼로그
        gameButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameExplanation();
            }
        } );

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

    // 업적 다이얼로그 함수
    private void rewardExplanation() {
        dialog.show();

        TextView explanationTitle = dialog.findViewById( R.id.explanationTitle );
        explanationTitle.setText( "'업적'" );

        TextView explanationText = dialog.findViewById( R.id.explanationText );
        explanationText.setText( ":  앱을 사용하면서 이룬 성과입니다.\n" +
                "\n " +
                "앱을 사용할수록\n" +
                "많은 업적 스티커가 나타납니다.\n" +
                "\n " +
                "업적 스티커를 누르면\n" +
                "짧은 설명도 볼 수 있습니다.\n" +
                "\n " +
                "다양한 업적이 추가되는 것을 보며\n" +
                "성취감을 얻을 수 있습니다.\n" +
                "\n " +
                "성취감은 자존감을 높여줍니다.\n" +
                "\n " +
                "업적을 보며 성취감을 느껴보세요!" );

        Button explanationButton = dialog.findViewById( R.id.explanationButton );
        explanationButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    // 플레이리스트 다이얼로그 함수
    private void playlistExplanation() {
        dialog.show();

        TextView explanationTitle = dialog.findViewById( R.id.explanationTitle );
        explanationTitle.setText( "'감정에 따른 노래 추천'" );

        TextView explanationText = dialog.findViewById( R.id.explanationText );
        explanationText.setText( ":  감정에 따라 노래를 추천합니다\n" +
                "\n " +
                "일기를 쓸 때 기록했던 감정을\n" +
                "기반으로 하여 노래를 추천합니다.\n" +
                "\n " +
                "오늘 느꼈던 많은 감정을\n" +
                "노래로 해소할 수 있습니다.\n" +
                "\n " +
                "기분이 좋은 날엔 더 행복하게,\n" +
                "기분이 안 좋은 날엔 좋아지게,\n" +
                "우울한 날엔 힘이 되게\n" +
                "노래를 추천해드립니다.\n" +
                "\n " +
                "사진 아이콘을 누르면\n" +
                "추천 노래 유튜브로 연결됩니다.\n" +
                "\n " +
                "노래를 들으며 감정을 느껴보세요!\n" );

        Button explanationButton = dialog.findViewById( R.id.explanationButton );
        explanationButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );

    }

    // 미니게임 다이얼로그 함수
    private void gameExplanation() {
        dialog.show();

        TextView explanationTitle = dialog.findViewById( R.id.explanationTitle );
        explanationTitle.setText( "'미니 게임'" );

        TextView explanationText = dialog.findViewById( R.id.explanationText );
        explanationText.setText( ":  미니 게임을 할 수 있습니다.\n" +
                "\n " +
                "스트레스를 많이 받았거나,\n" +
                "유독 지쳤던 하루를 보낸 날,\n" +
                "아무 생각도 하고 싶지 않을 때 등\n" +
                "다양한 상황에서 벗어나고 싶을 때\n" +
                "미니 게임이 도와줄 수 있습니다.\n" +
                "\n " +
                "게임 아이콘을 누르면\n" +
                "게임을 하는 곳으로 이동합니다.\n" +
                "\n " +
                "복잡한 게임이 아닌\n" +
                "단순하게 이루어진 게임을 통해\n" +
                "오늘 하루를 정리해보세요!\n " );

        Button explanationButton = dialog.findViewById( R.id.explanationButton );
        explanationButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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

    // 알깨기 게임 리스너
    private void eggBtnListener() {
        eggButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                // 액티비티로는 넘어가는데 프래그먼트로 전달이 X
                Intent intent = new Intent(getActivity(), TestGame.class);
                startActivity(intent);
*/

               /* Intro_2 In2 = Intro_2.newInstance();
                ((IntroPage) getActivity()).replaceFragment(In2);

                MiniGame miniGame = MiniGame.newInstance();
                ((GameSelect) getActivity()).replaceFragment(miniGame);
*/

                AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );

                builder.setMessage( "알 깨기 게임을 시작할까요?" );
                builder.setPositiveButton( "확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent( getContext(), EggGame.class );
                        startActivity( intent );
                    }
                } );
                builder.setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                } );
                AlertDialog eggAlertDialog = builder.create();
                eggAlertDialog.show();
            }
        } );
    }

    // 두더지 게임 리스너
    private void moleBtnListener() {
        moleButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );

                builder.setMessage( "두더지 잡기 게임을 시작할까요?" );
                builder.setPositiveButton( "확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent( getContext(), MoleGame.class );
                        startActivity( intent );
                    }
                } );
                builder.setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                } );
                AlertDialog eggAlertDialog = builder.create();
                eggAlertDialog.show();
            }
        } );
    }

    // 업적
    //  Drawable 값을 Byte 값으로
    private byte[] getByteArrayFromDrawable(Drawable d) {
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream );
        byte[] data = stream.toByteArray();

        return data;
    }

    // 해당하는 업적이 있다면 DB에 저장
    private void rewardImage() {
        firstMeet(); // 어플 처음 설치 시 congratulation
        dodamPoint(); // 도담 포인트 불러오기
        countDiaryCell(); // 첫 일기 작성
    }

    // 해당하는 업적이 있다면 DB에 저장
    // 어플 처음 설치 시 congratulation
    private void firstMeet() {
        RewardDBHelper rewardDBHelper = new RewardDBHelper( getContext() );
        SQLiteDatabase rewardDB = rewardDBHelper.getWritableDatabase();

        Cursor okCursor = rewardDB.rawQuery( "select exp from rewardData", null );
        boolean checkDB = false;
        while (okCursor.moveToNext()) {
            if ((okCursor.getString( 0 )).equals( "첫 만남!" )) {
                checkDB = true;
                break;
            }
        }

        if (checkDB == false) {
            Drawable congratulationD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.congratulation, null );
            byte[] congratulation = getByteArrayFromDrawable( congratulationD );
            SQLiteStatement congratulationS = rewardDB.compileStatement( "INSERT INTO RewardData(image, exp, detailExp) values(?,?,?);" );
            congratulationS.bindBlob( 1, congratulation );
            congratulationS.bindString( 2, "첫 만남!" );
            congratulationS.bindString( 3, "처음 만났네요!\n만나서 반가워요." );
            congratulationS.execute();
        }

        rewardDB.close();
    }

    // 해당하는 업적이 있다면 DB에 저장
    // 도담 포인트 불러오기
    private void dodamPoint() {
        RewardDBHelper rewardDBHelper = new RewardDBHelper( getContext() );
        SQLiteDatabase rewardDB = rewardDBHelper.getWritableDatabase();

        DBHelper dbHelper = new DBHelper( getContext() );
        SQLiteDatabase myDB = dbHelper.getReadableDatabase();

        Cursor pointCursor = myDB.rawQuery( "select point from Dodam", null );
        while (pointCursor.moveToNext()) {
            point = pointCursor.getInt( 0 );
        }

        Integer pointInt = point;

        myDB.close();

        Cursor okCursor = rewardDB.rawQuery( "select exp from rewardData", null );
        boolean checkDB = false;
        while (okCursor.moveToNext()) {
            if ((okCursor.getString( 0 )).equals( "도담 포인트 첫단계 진입!" )) {
                checkDB = true;
                break;
            }
        }

        if (checkDB == false) {
            if (pointInt > 0 && pointInt < 25) {
                Drawable trophy3D = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.trophy3, null );
                byte[] trophy3 = getByteArrayFromDrawable( trophy3D );
                SQLiteStatement p = rewardDB.compileStatement( "INSERT INTO RewardData(image, exp, detailExp) values(?,?,?);" );
                p.bindBlob( 1, trophy3 );
                p.bindString( 2, "도담 포인트 첫단계 진입!" );
                p.bindString( 3, "도담 포인트의 첫단계에 진입했어요!\n포인트를 모아서 나무를 성장시켜봐요!" );
                p.execute();
            }
        }

        rewardDB.close();
    }

    // 해당하는 업적이 있다면 DB에 저장
    // 첫 일기 작성
    private void countDiaryCell() {
        RewardDBHelper rewardDBHelper = new RewardDBHelper( getContext() );
        SQLiteDatabase rewardDB = rewardDBHelper.getWritableDatabase();

        DiaryDBHelper diaryDBHelper = new DiaryDBHelper( getContext() );
        SQLiteDatabase diaryDB = diaryDBHelper.getReadableDatabase();

        Cursor cellCursor = diaryDB.rawQuery( "select count(content) from DiaryData", null );
        while (cellCursor.moveToNext()) {
            cell = cellCursor.getCount();
        }

        Integer cellInt = cell;

        diaryDB.close();

        Cursor okCursor = rewardDB.rawQuery( "select exp from rewardData", null );
        boolean checkDB = false;
        while (okCursor.moveToNext()) {
            if ((okCursor.getString( 0 )).equals( "첫 일기 작성!" )) {
                checkDB = true;
                break;
            }
        }

        if (checkDB == false) {
            if (cellInt == 1) {
                Drawable diaryD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.diary, null );
                byte[] diary = getByteArrayFromDrawable( diaryD );
                SQLiteStatement p = rewardDB.compileStatement( "INSERT INTO RewardData(image, exp, detailExp) values(?,?,?);" );
                p.bindBlob( 1, diary );
                p.bindString( 2, "첫 일기 작성!" );
                p.bindString( 3, "축하드려요!\n첫 일기를 작성하셨네요.\n\n오늘 하루 겪었던 일,\n느꼈던 감정 모두 적어 보아요.\n\n자신을 돌아보는 건 자존감에 아주 좋은 일이랍니다." );
                p.execute();
            }
        }

        rewardDB.close();
    }

    // 플레이리스트 DB 저장
    private void albumImage() {
        PlayListDBHelper playListDBHelper = new PlayListDBHelper( getContext() );
        SQLiteDatabase playlistDB = playListDBHelper.getReadableDatabase();

        Cursor okCursor = playlistDB.rawQuery( "select music from PlayListData", null );
        boolean checkDB = false;
        while (okCursor.moveToNext()) {
            if ((okCursor.getString( 0 )).equals( "Autumn Breeze (Feat. Rachel Lim)" )) {
                checkDB = true;
                break;
            }
        }

        if (checkDB == false) {
            // 555
            Drawable aD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.plasticplants, null );
            byte[] a = getByteArrayFromDrawable( aD );
            SQLiteStatement aS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            aS.bindString( 1, "555" );
            aS.bindString( 2, "Plastic Plants" );
            aS.bindString( 3, "Mahalia" );
            aS.bindBlob( 4, a );
            aS.bindString( 5, "YYYRSS-ePxQ" );
            aS.execute();

            Drawable bD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.yourtext, null );
            byte[] b = getByteArrayFromDrawable( bD );
            SQLiteStatement bS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            bS.bindString( 1, "555" );
            bS.bindString( 2, "your text" );
            bS.bindString( 3, "Sundial" );
            bS.bindBlob( 4, b );
            bS.bindString( 5, "hoHCOymHbhM" );
            bS.execute();

            Drawable cD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.stay, null );
            byte[] c = getByteArrayFromDrawable( cD );
            SQLiteStatement cS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            cS.bindString( 1, "555" );
            cS.bindString( 2, "STAY" );
            cS.bindString( 3, "The Kid LAROI, Justin Bieber" );
            cS.bindBlob( 4, c );
            cS.bindString( 5, "Ec7TN_11az8" );
            cS.execute();

            Drawable dD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.butter, null );
            byte[] d = getByteArrayFromDrawable( dD );
            SQLiteStatement dS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            dS.bindString( 1, "555" );
            dS.bindString( 2, "Butter" );
            dS.bindString( 3, "방탄소년단" );
            dS.bindBlob( 4, d );
            dS.bindString( 5, "WMweEpGlu_U" );
            dS.execute();

            // 500
            Drawable eD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.dundundance, null );
            byte[] e = getByteArrayFromDrawable( eD );
            SQLiteStatement eS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            eS.bindString( 1, "500" );
            eS.bindString( 2, "Dun Dun Dance" );
            eS.bindString( 3, "오마이걸(OH MY GIRL)" );
            eS.bindBlob( 4, e );
            eS.bindString( 5, "HzOjwL7IP_o" );
            eS.execute();

            Drawable fD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.myhand, null );
            byte[] f = getByteArrayFromDrawable( fD );
            SQLiteStatement fS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            fS.bindString( 1, "500" );
            fS.bindString( 2, "내 손을 잡아" );
            fS.bindString( 3, "아이유(IU)" );
            fS.bindBlob( 4, f );
            fS.bindString( 5, "3iM_06QeZi8" );
            fS.execute();

            Drawable gD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.trafficlight, null );
            byte[] g = getByteArrayFromDrawable( gD );
            SQLiteStatement gS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            gS.bindString( 1, "500" );
            gS.bindString( 2, "신호등" );
            gS.bindString( 3, "이무진" );
            gS.bindBlob( 4, d );
            gS.bindString( 5, "SK6Sm2Ki9tI" );
            gS.execute();

            Drawable hD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.hello, null );
            byte[] h = getByteArrayFromDrawable( hD );
            SQLiteStatement hS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            hS.bindString( 1, "500" );
            hS.bindString( 2, "안녕(Hello)" );
            hS.bindString( 3, "조이(JOY)" );
            hS.bindBlob( 4, h );
            hS.bindString( 5, "lNvBbh5jDcA" );
            hS.execute();

            // 050
            Drawable iD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.salt, null );
            byte[] i = getByteArrayFromDrawable( iD );
            SQLiteStatement iS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            iS.bindString( 1, "050" );
            iS.bindString( 2, "Salt" );
            iS.bindString( 3, "Ava Max" );
            iS.bindBlob( 4, i );
            iS.bindString( 5, "hdqjdhRep2I" );
            iS.execute();

            Drawable jD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.yourthinking, null );
            byte[] j = getByteArrayFromDrawable( jD );
            SQLiteStatement jS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            jS.bindString( 1, "050" );
            jS.bindString( 2, "그건 니 생각이고" );
            jS.bindString( 3, "장기하와 얼굴들" );
            jS.bindBlob( 4, j );
            jS.bindString( 5, "h28fhU-mjDA" );
            jS.execute();

            Drawable kD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.newrules, null );
            byte[] k = getByteArrayFromDrawable( kD );
            SQLiteStatement kS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            kS.bindString( 1, "050" );
            kS.bindString( 2, "New Rules" );
            kS.bindString( 3, "Dua Lipa" );
            kS.bindBlob( 4, k );
            kS.bindString( 5, "4l2jpzPDtuQ" );
            kS.execute();

            Drawable lD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.crystal, null );
            byte[] l = getByteArrayFromDrawable( lD );
            SQLiteStatement lS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            lS.bindString( 1, "050" );
            lS.bindString( 2, "주옥같다" );
            lS.bindString( 3, "고옥희" );
            lS.bindBlob( 4, l );
            lS.bindString( 5, "6GZkDiEqqRY" );
            lS.execute();

            //005
            Drawable mD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.thereyouare, null );
            byte[] m = getByteArrayFromDrawable( mD );
            SQLiteStatement mS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            mS.bindString( 1, "005" );
            mS.bindString( 2, "There You Are" );
            mS.bindString( 3, "ZAYN" );
            mS.bindBlob( 4, m );
            mS.bindString( 5, "oSdLaBxde-w" );
            mS.execute();

            Drawable nD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.dazedandconfused, null );
            byte[] n = getByteArrayFromDrawable( nD );
            SQLiteStatement nS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            nS.bindString( 1, "005" );
            nS.bindString( 2, "Dazed & Confused" );
            nS.bindString( 3, "Ruel" );
            nS.bindBlob( 4, n );
            nS.bindString( 5, "kkOQ6j1btpY" );
            nS.execute();

            Drawable oD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.needy, null );
            byte[] o = getByteArrayFromDrawable( oD );
            SQLiteStatement oS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            oS.bindString( 1, "005" );
            oS.bindString( 2, "needy" );
            oS.bindString( 3, "Ariana Grande" );
            oS.bindBlob( 4, o );
            oS.bindString( 5, "HkT0N5QXXiM" );
            oS.execute();

            Drawable pD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.pov, null );
            byte[] p = getByteArrayFromDrawable( pD );
            SQLiteStatement pS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            pS.bindString( 1, "005" );
            pS.bindString( 2, "pov" );
            pS.bindString( 3, "Ariana Grande" );
            pS.bindBlob( 4, p );
            pS.bindString( 5, "4aQmTTY6GFc" );
            pS.execute();

            // 550
            Drawable qD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.good4u, null );
            byte[] q = getByteArrayFromDrawable( qD );
            SQLiteStatement qS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            qS.bindString( 1, "550" );
            qS.bindString( 2, "good 4 u" );
            qS.bindString( 3, "Olivia Rodrigo" );
            qS.bindBlob( 4, q );
            qS.bindString( 5, "UyshwO7p7jw" );
            qS.execute();

            Drawable rD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.callmecruella, null );
            byte[] r = getByteArrayFromDrawable( rD );
            SQLiteStatement rS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            rS.bindString( 1, "550" );
            rS.bindString( 2, "Call me Cruella" );
            rS.bindString( 3, "Florence + the Machine" );
            rS.bindBlob( 4, r );
            rS.bindString( 5, "ljBZZmnFw_M" );
            rS.execute();

            Drawable sD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.nextlevel, null );
            byte[] s = getByteArrayFromDrawable( sD );
            SQLiteStatement sS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            sS.bindString( 1, "550" );
            sS.bindString( 2, "Next Level" );
            sS.bindString( 3, "aespa" );
            sS.bindBlob( 4, s );
            sS.bindString( 5, "ljBZZmnFw_M" );
            sS.execute();

            Drawable tD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.prettysavage, null );
            byte[] t = getByteArrayFromDrawable( tD );
            SQLiteStatement tS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            tS.bindString( 1, "550" );
            tS.bindString( 2, "Pretty Savage" );
            tS.bindString( 3, "BLACKPINK" );
            tS.bindBlob( 4, t );
            tS.bindString( 5, "F8c8f2nK82w" );
            tS.execute();

            // 505
            Drawable uD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.feelspecial, null );
            byte[] u = getByteArrayFromDrawable( uD );
            SQLiteStatement uS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            uS.bindString( 1, "505" );
            uS.bindString( 2, "Feel Special" );
            uS.bindString( 3, "TWICE(트와이스)" );
            uS.bindBlob( 4, u );
            uS.bindString( 5, "3ymwOvzhwHs" );
            uS.execute();

            Drawable vD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.airplane, null );
            byte[] v = getByteArrayFromDrawable( vD );
            SQLiteStatement vS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            vS.bindString( 1, "505" );
            vS.bindString( 2, "Airplane" );
            vS.bindString( 3, "f(x)" );
            vS.bindBlob( 4, v );
            vS.bindString( 5, "TPzN__A7yeg" );
            vS.execute();

            Drawable wD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.day1, null );
            byte[] w = getByteArrayFromDrawable( wD );
            SQLiteStatement wS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            wS.bindString( 1, "505" );
            wS.bindString( 2, "Day 1 ◑" );
            wS.bindString( 3, "HONNE" );
            wS.bindBlob( 4, w );
            wS.bindString( 5, "YUoUtJBxtxA" );
            wS.execute();

            Drawable xD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.alwaysillcare, null );
            byte[] x = getByteArrayFromDrawable( xD );
            SQLiteStatement xS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            xS.bindString( 1, "505" );
            xS.bindString( 2, "always, i''ll care" );
            xS.bindString( 3, "Jeremy Zucker" );
            xS.bindBlob( 4, x );
            xS.bindString( 5, "5Ywlkp0NkKk" );
            xS.execute();

            // 055
            Drawable yD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.inandout, null );
            byte[] y = getByteArrayFromDrawable( yD );
            SQLiteStatement yS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            yS.bindString( 1, "055" );
            yS.bindString( 2, "In & Out" );
            yS.bindString( 3, "Red Velvet(레드벨벳)" );
            yS.bindBlob( 4, y );
            yS.bindString( 5, "fh0yHa1Waxk" );
            yS.execute();

            Drawable zD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.w, null );
            byte[] z = getByteArrayFromDrawable( zD );
            SQLiteStatement zS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            zS.bindString( 1, "055" );
            zS.bindString( 2, "W(Feat. Gunna)" );
            zS.bindString( 3, "Koffee" );
            zS.bindBlob( 4, z );
            zS.bindString( 5, "X0Nn1t9INOs" );
            zS.execute();

            Drawable aaD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.savage, null );
            byte[] aa = getByteArrayFromDrawable( aaD );
            SQLiteStatement aaS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            aaS.bindString( 1, "055" );
            aaS.bindString( 2, "Savage(Nightcore Remix)" );
            aaS.bindString( 3, "Bahari" );
            aaS.bindBlob( 4, aa );
            aaS.bindString( 5, "rH28_ZanNeI" );
            aaS.execute();

            Drawable bbD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.inferno, null );
            byte[] bb = getByteArrayFromDrawable( bbD );
            SQLiteStatement bbS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            bbS.bindString( 1, "055" );
            bbS.bindString( 2, "INFERNO" );
            bbS.bindString( 3, "Sub Urban & Bella Poarch" );
            bbS.bindBlob( 4, bb );
            bbS.bindString( 5, "2qKfebB-XDc" );
            bbS.execute();

            // 000
            Drawable ccD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.congratulation, null );
            byte[] cc = getByteArrayFromDrawable( ccD );
            SQLiteStatement ccS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            ccS.bindString( 1, "000" );
            ccS.bindString( 2, "금요일에 만나요(Feat. 장이정 of HISTORY)" );
            ccS.bindString( 3, "아이유(IU)" );
            ccS.bindBlob( 4, cc );
            ccS.bindString( 5, "EiVmQZwJhsA" );
            ccS.execute();

            Drawable ddD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.friday, null );
            byte[] dd = getByteArrayFromDrawable( ddD );
            SQLiteStatement ddS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            ddS.bindString( 1, "000" );
            ddS.bindString( 2, "남이 될 수 있을까" );
            ddS.bindString( 3, "볼빨간사춘기 & 스무살" );
            ddS.bindBlob( 4, dd );
            ddS.bindString( 5, "Z1pGxkXyDvc" );
            ddS.execute();

            Drawable eeD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.dawn, null );
            byte[] ee = getByteArrayFromDrawable( eeD );
            SQLiteStatement eeS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            eeS.bindString( 1, "000" );
            eeS.bindString( 2, "또 새벽이 오면(Feat. 백현(BAEKHYUN))" );
            eeS.bindString( 3, "Colde(콜드)" );
            eeS.bindBlob( 4, ee );
            eeS.bindString( 5, "FIW8bAjcmVk" );
            eeS.execute();

            Drawable ffD = ResourcesCompat.getDrawable( getContext().getResources(), R.drawable.autumnbreeze, null );
            byte[] ff = getByteArrayFromDrawable( ffD );
            SQLiteStatement ffS = playlistDB.compileStatement( "INSERT INTO PlayListData(mood, music, artist, albumImage, videoId) values(?,?,?,?,?);" );
            ffS.bindString( 1, "000" );
            ffS.bindString( 2, "Autumn Breeze(Feat. Rachel Lim)" );
            ffS.bindString( 3, "JIDA(지다)" );
            ffS.bindBlob( 4, ff );
            ffS.bindString( 5, "EzQsoZYY470" );
            ffS.execute();
        }

        playlistDB.close();
    }
}