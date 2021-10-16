package com.example.dodamver3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserGuide extends AppCompatActivity {
    // 1
    Dialog dialog;
    LinearLayout homeLayout, calLayout, habitLayout, statLayout, forLayout;
    ImageView closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_guide );

        // 2
        closeButton = (ImageView) findViewById( R.id.closeButton );

        homeLayout = (LinearLayout) findViewById( R.id.homeGuide );
        calLayout = (LinearLayout) findViewById( R.id.calGuide );
        habitLayout = (LinearLayout) findViewById( R.id.habitGuide );
        statLayout = (LinearLayout) findViewById( R.id.statGuide );
        forLayout = (LinearLayout) findViewById( R.id.forGuide );

        // 커스텀 다이얼로그 (설명글)
        dialog = new Dialog( UserGuide.this );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.mypage_custom_dialog );

        // 3
        closeButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        } );

        homeLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeGuide();
            }
        } );

        calLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calGuide();
            }
        } );

        habitLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitGuide();
            }
        } );

        statLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statGuide();
            }
        } );

        forLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forGuide();
            }
        } );
    }

    // 홈 화면
    private void homeGuide() {
        dialog.show();

        TextView guideTitle = dialog.findViewById( R.id.explanationTitle );
        guideTitle.setText( "'홈 화면'" );

        TextView guideExp = dialog.findViewById( R.id.explanationText );
        guideExp.setText( "홈 화면은 가장 기본적인 화면입니다.\n"
        + "\n"
        + "가장 처음에 보이는 식물과\n"
        + "우리는 함께 성장해나갑니다.\n"
        + "\n"
        + "하루 한 번씩 심리 진단을 할 수 있습니다.\n"
        + "심리 검사를 통해 통계를 내고, 결과를 보면서\n"
        + "자신의 심리 상태를 점검해볼 수 있습니다.\n"
        + "\n"
        + "그 외에 도담 포인트와 오늘의 명언,\n"
        + "식물, 미니 게임을 볼 수 있습니다."
        );

        /*
        // 버튼 클릭시 홈으로 이동
        Button moveButton = dialog.findViewById( R.id.moveButton );
        moveButton.setText( "홈으로 이동" );
        moveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        } );
        */

        Button guideButton = dialog.findViewById( R.id.explanationButton );
        guideButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    // 캘린더
    private void calGuide() {
        dialog.show();

        TextView guideTitle = dialog.findViewById( R.id.explanationTitle );
        guideTitle.setText( "'캘린더 화면'" );

        TextView guideExp = dialog.findViewById( R.id.explanationText );
        guideExp.setText( "캘린더는 오늘의 일기를 쓸 수 있습니다.\n"
                + "\n"
                + "오늘 있었떤 일, 감정 등을 적어보며\n"
                + "자기 생각과 마음을 돌아볼 수 있습니다.\n"
                + "\n"
                + "혹시 어떻게 일기를 적어야 할지 모르겠다면\n"
                + "다음과 같은 일기 주제들은 어떤가요?\n"
                + "\n"
                + "감정 일기는\n"
                + "자신이 오늘 느꼈던 감정들을 적는 것입니다.\n"
                + "자존감을 키우려면 감정을 읽는\n"
                + "감성 지능이 중요합니다.\n"
                + "이 감성 지능의 향상은 우리 삶의 행복과\n"
                + "안정으로 연결된다고 합니다.\n"
                + "\n"
                + "긍정 일기는\n"
                + "자신의 성취(해빗 트래커 달성 등)나\n"
                + "스스로에 대한 긍정적인 부분에 대한 생각을\n"
                + "일기로 적어보는 것입니다.\n"
                + "물론 부정적인 생각이나 견해가\n"
                + "남아있을 수 있습니다.\n"
                + "그래도 최대한 긍정적인 부분들에\n"
                + "더 집중하고 시간을 사용하면\n"
                + "전체적으로 자신의 가치를 올리는데\n"
                + "많은 도움이 됩니다.\n"
                + "\n"
                + "감사 일기는\n"
                + "작은 일에도 감사하는 마음을 적는 것입니다.\n"
                + "감사를 하면 긍정적인 감정을 느끼게 하는\n"
                + "뇌 좌측의 전전두피질이 활성화되어\n"
                + "스트레스가 감소한다고 합니다."
        );

        /*
        // 버튼 클릭시 캘린더로 이동
        Button moveButton = dialog.findViewById( R.id.moveButton );
        moveButton.setText( "캘린더로 이동" );
        moveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        } );
        */

        Button guideButton = dialog.findViewById( R.id.explanationButton );
        guideButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void habitGuide() {
        dialog.show();

        TextView guideTitle = dialog.findViewById( R.id.explanationTitle );
        guideTitle.setText( "'해빗 트래커 화면'" );

        TextView guideExp = dialog.findViewById( R.id.explanationText );
        guideExp.setText( "해빗 트래커는 습관을 만들어갈 수 있습니다.\n"
                + "\n"
                + "일상에서 작은 것부터 성취해보고\n"
                + "성공했다는 느낌을 가져보는 것은\n"
                + "자존감을 채우는 부분에서 매우 중요합니다.\n"
                + "\n"
                + "내가 만들고자 하는 습관 목표를 세워\n"
                + "성취감을 자주 느끼는 것은\n"
                + "분명 자존감에 도움을 줍니다.\n"
                + "\n"
                + "완벽하지 않아도 좋습니다.\n"
                + "꼭 습관이 아니어도 좋습니다.\n"
                + "자신과 작은 약속을 만들어\n"
                + "달성했을 시 체크해보는 것은 어떨까요?"
        );

        /*
        // 버튼 클릭시 해빗 트래커로 이동
        Button moveButton = dialog.findViewById( R.id.moveButton );
        moveButton.setText( "해빗 트래커로\n이동" );
        moveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        } );
        */

        Button guideButton = dialog.findViewById( R.id.explanationButton );
        guideButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void statGuide() {
        dialog.show();

        TextView guideTitle = dialog.findViewById( R.id.explanationTitle );
        guideTitle.setText( "'통계 화면'" );

        TextView guideExp = dialog.findViewById( R.id.explanationText );
        guideExp.setText( "통계는 심리 검사, 일기 등 다양한 콘텐츠의\n"
                + "통계를 볼 수 있습니다.\n"
                + "\n"
                + "자신이 앱을 사용하면서 변화하는 모습을\n"
                + "직관적으로 통계를 통해 볼 수 있습니다.\n"
                + "\n"
                + "변화가 작거나\n"
                + "변화하지 않아도 괜찮습니다.\n"
                + "\n"
                + "자신이 변하기 위해서 노력했다는\n"
                + "그 자체만으로도 충분하기 때문입니다."
        );

        /*
        // 버튼 클릭시 통계로 이동
        Button moveButton = dialog.findViewById( R.id.moveButton );
        moveButton.setText( "통계로 이동" );
        moveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        } );
        */

        Button guideButton = dialog.findViewById( R.id.explanationButton );
        guideButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void forGuide() {
        dialog.show();

        TextView guideTitle = dialog.findViewById( R.id.explanationTitle );
        guideTitle.setText( "'마이 페이지 화면'" );

        TextView guideExp = dialog.findViewById( R.id.explanationText );
        guideExp.setText( "FOR YOU는 나를 위한 페이지입니다.\n"
                + "\n"
                + "업적을 통해 작은 성취를 해나갈수록\n"
                + "성취감을 느낄 수 있도록 했습니다.\n"
                + "\n"
                + "플레이리스트를 통해 나의 감정과 맞는\n"
                + "노래들을 선택하여 보여줍니다.\n"
                + "\n"
                + "미니 게임을 통해 오늘의 나쁜 감정을\n"
                + "털어버릴 수 있도록 하였습니다.\n"
                + "\n"
                + "이렇게 나를 위한 나만의 페이지를 통해\n"
                + "세상에 나 혼자가 아님을 꼭 기억해주세요."
        );

        /*
        // 버튼 클릭시 마이 페이지로 이동
        Button moveButton = dialog.findViewById( R.id.moveButton );
        moveButton.setText( "마이 페이지로\n이동" );
        moveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        } );
        */

        Button guideButton = dialog.findViewById( R.id.explanationButton );
        guideButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }
}