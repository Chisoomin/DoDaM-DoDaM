package com.example.dodamver3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class componentGuide extends AppCompatActivity {
    // 1
    Dialog dialog;
    LinearLayout drawerComponent, homeComponent, calComponent, habitComponent, statComponent, forComponent;
    ImageView closeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_component_guide );

        // 2
        closeImage = (ImageView) findViewById( R.id.closeImage );

        drawerComponent = (LinearLayout) findViewById( R.id.drawerComponent );
        homeComponent = (LinearLayout) findViewById( R.id.homeComponent );
        calComponent = (LinearLayout) findViewById( R.id.calComponent );
        habitComponent = (LinearLayout) findViewById( R.id.habitComponent );
        statComponent = (LinearLayout) findViewById( R.id.statComponent );
        forComponent = (LinearLayout) findViewById( R.id.forComponent );

        // 커스터 다이얼로그 (설명글)
        dialog = new Dialog( componentGuide.this );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.mypage_custom_dialog );

        // 3
        closeImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        drawerComponent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerComponentF();
            }
        } );

        homeComponent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeComponentF();
            }
        } );

        calComponent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calComponentF();
            }
        } );

        habitComponent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitComponentF();
            }
        } );

        statComponent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statComponentF();
            }
        } );

        forComponent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forComponentF();
            }
        } );
    }

    private void drawerComponentF() {
        dialog.show();

        TextView componentTitle = dialog.findViewById( R.id.explanationTitle );
        componentTitle.setText( "'메뉴 화면'" );

        TextView componentExp = dialog.findViewById( R.id.explanationText );
        componentExp.setText( "플레이리스트 : \n"
                + "일기를 쓰고 난 후 연결되는 플레이리스트로\n"
                + "여기서 따로 이동할 수 있습니다.\n"
                + "\n"
                + "업적 전체보기 : \n"
                + "FOR YOU에 있던 업적을 한눈에 볼 수 있도록\n"
                + "모아놓은 곳입니다.\n"
                + "업적 아이콘과 짧은 설명으로 이루어져 있는\n"
                + "업적 전체보기는 FOR YOU와는 다르게\n"
                + "업적 아이콘을 누르면 더 자세한 설명을\n"
                + "볼 수 있으니 한번 눌러보세요!\n"
                + "\n"
                + "메뉴 설명서 : \n"
                + "하단에 있는 탭에 관련된 설명입니다.\n"
                + "각 탭이 어떠한 역할을 하는지,\n"
                + "왜 있는지를 설명해줍니다.\n"
                + "\n"
                + "구성요소설명서\n"
                + "지금 보시는 이 화면입니다.\n"
                + "메뉴들의 각 구성요소를 설명합니다.\n"
                + "더욱 세세하게 설명하고 있기 때문에,\n"
                + "짧은 설명이 부족하다고 느낀다면\n"
                + "이 설명서가 도움이 될 거예요.\n"
                + "\n"
                + "설정 : \n"
                + "다양한 설정을 담고 있습니다.\n"
                + "\n"
                + "로그아웃 : \n"
                + "로그아웃을 하게 되면\n"
                + "앱 실행 시 입력하는 비밀번호 창으로\n"
                + "넘어가게 됩니다." );

        Button componentButton = dialog.findViewById( R.id.explanationButton );
        componentButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void homeComponentF() {
        dialog.show();

        TextView componentTitle = dialog.findViewById( R.id.explanationTitle );
        componentTitle.setText( "'홈 화면'" );

        TextView componentExp = dialog.findViewById( R.id.explanationText );
        componentExp.setText( "도담 포인트 : \n"
                + "도담 포인트는 일기를 쓰거나, 습관 달성 등\n"
                + "다양한 앱 내 활동을 통해 얻을 수 있습니다.\n"
                + "이 포인트를 모아서 식물을 성장시킵니다.\n"
                + "\n"
                + "명언 말풍선 : \n"
                + "오늘의 명언이 나타납니다.\n"
                + "매일 다른 격려의 글귀를 보면서\n"
                + "힘을 얻을 수 있습니다.\n"
                + "오늘의 명언은 푸시 알림을 통해서도\n"
                + "만날 수 있습니다.\n"
                + "\n"
                + "식물 : \n"
                + "도담 포인트를 통해 성장시킬 수 있습니다.\n"
                + "자신과 함께 성장해나가는 식물의 모습을\n"
                + "기대해주세요!\n"
                + "\n"
                + "심리 진단 : \n"
                + "하루에 한 번씩 심리 상태를 진단하면서\n"
                + "자신의 현재 심리 상태를 알 수 있습니다.\n"
                + "\n"
                + "미니게임 : \n"
                + "미니게임은 자신의 감정을 해소합니다.\n"
                + "간단한 게임을 통해 생각을 비우거나,\n"
                + "스트레스가 해소되게 합니다." );

        Button componentButton = dialog.findViewById( R.id.explanationButton );
        componentButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void calComponentF() {
        dialog.show();

        TextView componentTitle = dialog.findViewById( R.id.explanationTitle );
        componentTitle.setText( "'캘린더 화면'" );

        TextView componentExp = dialog.findViewById( R.id.explanationText );
        componentExp.setText( "캘린더, 일기 보기 및 쓰기 : \n"
                + "날짜를 클릭해서 일기를 볼 수 있습니다.\n"
                + "해당 날짜를 선택하여 그날의 일기를 보거나,\n"
                + "혹시 쓰지 않았다면 일기를 쓸 수 있습니다.\n"
                + "그날의 감정에 따른 스탬프도 나타납니다.\n"
                + "\n"
                + "일기 입력 : \n"
                + "오늘의 일기를 입력할 수 있습니다.\n"
                + "만약 어떠한 주제로 일기를 써야 할지\n"
                + "감이 오지 않는다면,\n"
                + "'홈 화면 → 메뉴 → 메뉴 설명서 → 캘린더' \n"
                + "를 보고 다양한 주제의 일기를 써보세요.\n"
                + "\n"
                + "감정 입력 : \n"
                + "그날 느낀 기쁨, 화남, 슬픔의 감정을\n"
                + "새싹을 옮기면서 0-10까지의 정도로\n"
                + "나타낼 수 있습니다.\n"
                + "상세한 감정 입력을 통해\n"
                + "오늘 하루 나의 감정을 돌아보세요.\n"
                + "\n"
                + "플레이리스트\n"
                + "입력한 감정 정도에 따라\n"
                + "어울리는 노래를 추천합니다.\n"
                + "재생 버튼(▶)을 누르면 \n"
                + "유튜브를 통해 노래가 나옵니다." );

        Button componentButton = dialog.findViewById( R.id.explanationButton );
        componentButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void habitComponentF() {
        dialog.show();

        TextView componentTitle = dialog.findViewById( R.id.explanationTitle );
        componentTitle.setText( "'해빗 트래커 화면'" );

        TextView componentExp = dialog.findViewById( R.id.explanationText );
        componentExp.setText( "습관 입력 :\n"
                + "상단의 '+' 버튼을 눌러 습관을 입력합니다.\n"
                + "처음 버튼을 누르면\n"
                + "색상을 선택하는 창이 나옵니다.\n"
                + "원하는 색을 선택한 후 다음을 누르면\n"
                + "목표한 습관을 적는 곳이 나옵니다.\n"
                + "자신의 목표 습관을 적고\n"
                + "'습관 등록'을 누르면 습관이 등록됩니다.\n"
                + "\n"
                + "가로 캘린더 :\n"
                + "날짜를 선택해 설정한 습관을 볼 수 있습니다.\n"
                + "만약 지나간 날짜의 습관에 달성 표시를 \n"
                + "하지 못했다면,\n"
                + "날짜를 선택하여 다시 할 수 있습니다.\n"
                + "\n"
                + "습관 목록 : \n"
                + "습관 목록을 확인할 수 있습니다.\n"
                + "내가 설정한 습관들을 한눈에 볼 수 있습니다.\n"
                + "오늘 달성한 습관이 있다면\n"
                + "해당 습관을 오른쪽에서 왼쪽으로 밀어\n"
                + "'달성'이라는 글자가 나오면\n"
                + "그 글자를 눌러 달성을 완료하면 됩니다.\n"
                + "그러면 그 습관이 목록에서 지워지면서\n"
                + "내가 달성해야 할 다른 습관만 남게 됩니다." );

        Button componentButton = dialog.findViewById( R.id.explanationButton );
        componentButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void statComponentF() {
        dialog.show();

        TextView componentTitle = dialog.findViewById( R.id.explanationTitle );
        componentTitle.setText( "'통계 화면'" );

        TextView componentExp = dialog.findViewById( R.id.explanationText );
        componentExp.setText( "심리 진단 그래프 : \n"
                + "심리 진단 결과를\n"
                + "그래프로 확인할 수 있습니다.\n"
                + "매일 하는 심리 검사를 통해\n"
                + "이를 그래프로 나타내고\n"
                + "자신의 변화를 직관적으로 볼 수 있습니다.\n"
                + "그래프 밑에 설명이 있기 때문에\n"
                + "이 설명을 보고 자신의 상태를\n"
                + "진단할 수 있습니다.\n"
                + "\n"
                + "일기 기반 그래프 : \n"
                + "일기를 쓰면서 내가 사용하는\n"
                + "부정적인 단어의 빈도를\n"
                + "한눈에 확인할 수 있습니다.\n"
                + "그래프를 보면서\n"
                + "나의 부정적인 단어 사용 변화 추이를 \n"
                + "볼 수 있습니다." );

        Button componentButton = dialog.findViewById( R.id.explanationButton );
        componentButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }

    private void forComponentF() {
        dialog.show();

        TextView componentTitle = dialog.findViewById( R.id.explanationTitle );
        componentTitle.setText( "'마이 페이지 화면'" );

        TextView componentExp = dialog.findViewById( R.id.explanationText );
        componentExp.setText( "업적 : \n"
                + "앱을 사용하면서 얻을 수 있습니다.\n"
                + "일기를 쓰거나, 게임을 하거나 하는 등의\n"
                + "여러 가지 활동을 앱 내에서 하다 보면\n"
                + "어느샌가 늘어나 있는 업적을 볼 수 있습니다.\n"
                + "각 업적의 아이콘을 누르면\n"
                + "왜 이 업적이 생겼는지 짧은 설명이 나옵니다.\n"
                + "\n"
                + "감정에 따른 노래 추천 : \n"
                + "감정에 따른 노래들이\n"
                + "다양한 아이콘과 함께 나타납니다.\n"
                + "여기에 있는 노래들은\n"
                + "실제 유튜브로 넘어가서 재생이 됩니다.\n"
                + "아이콘들과 제목이 어떠한 연관이 있는지\n"
                + "생각해보는 것도 소소한 즐거임이 될 거예요.\n"
                + "\n"
                + "미니게임 : \n"
                + "미니게임을 할 수 있도록 연결합니다.\n"
                + "단순한 알깨기 게임, 두더지 게임을 통해\n"
                + "묵은 감정들을 해소해보세요!" );

        Button componentButton = dialog.findViewById( R.id.explanationButton );
        componentButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
    }
}