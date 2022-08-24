# DoDam


## Summary
#### 코로나 블루 등으로 최근 계속해서 높아지는 우울감을 낮추기 위해 심리 상태를 진단하고 치유하며, 내면의 부정적인 면과 스트레스의 해소가 필요하므로 이를 해결하기 위해 개발하게 되었습니다.

#### 주요 기능으로 인공지능과 데이터를 활용해 사용자의 심리 상태를 진단하고, 긍정적인 습관을 길러 자존감을 높일 수 있는 Habit Tracker가 있습니다. 그리고 감정의 정리를 돕는 감정 캘린더와 다이어리 기능이 있고, 다이어리에 입력한 감정에 따른 음악 추천 기능으로 감정을 다독일 수 있습니다. 콘텐츠 사용을 통해 포인트를 모아 식물을 길러 자신의 성장을 가시적으로 확인할 수 있습니다. 미니게임을 통해 감정 해소를 하고 인공지능으로 분석한 결과를 그래프로 확인 가능합니다. 이러한 기능을 통해 자발적이며 꾸준히 접근할 수 있는 “심리 치유” 앱입니다.

#### 첫 장기 프로젝트로 XML 코드로 Frontend를 구성하고 Java 코드로 위젯의 기능을 구현하였으며 SQLite로 LocalDB를 JDBC로 ServerDB를 개발하였습니다. 네이버 클라우드 플렛폼과 firebase를 활용하여 사용자에게 여러 기능을 제공할 수 있도록 만든 프로젝트입니다.

## Background
#### 최근 코로나로 인해 재택근무, 비대면 수업 등 언택트 상황이 지속하면서 집에 있는 시간이 많았습니다. 코로나 블루와 같은 신조어를 낳으며 우울감은 높아지고 자존감은 낮아져 현대인들의 마음의 병이 깊어져 가고 있습니다. 게다가 우리나라는 OECD 자살률 여전히 1위이고, 세계 행복 지수 61위 기록하고 있습니다. 우울증은 누구나 가질 수 있는 마음의 병이지만 병원에 가길 꺼리는 사람들이 대다수입니다. 사방을 찌르고 찔리는 날카로운 사회, 비교하고 비교당하며 줄을 세우는 사회에서 조금이나마 벗어나 내면의 부정적인 면과 스트레스의 해소가 필요하므로 이를 해결하기 위한 목적으로 애플리케이션을 제작을 결심하였습니다.
#### AI와 빅데이터 등 최근 각광받는 기술을 이용하여 사용자들의 심리를 치유하면 어떨까하는 생각에서 나온 주제이고, 직접 학습시킨 인공지능을 앱에 접목시켜볼 수 있는 좋은 기회라고 생각하였습니다. UI는 따뜻한 분위기로 디자인하지만 정확한 기술을 사용하는 것이 목적이었습니다.


## Development

<img src="https://user-images.githubusercontent.com/84059942/184656334-a6b3af22-ed06-45c7-9ecc-97a436cb7985.png"  width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184656339-d5c93d2f-1425-4b52-8a4f-5a90fe42c359.png"  width="200" height="400"/> 
#### ① 심리 상태 진단
우울 척도 검사인 CES-D를 활용하여 사용자가 우울감을 쉽게 진단할 수 있도록 합니다. 

</br></br></br><img src="https://user-images.githubusercontent.com/84059942/184656480-34514676-05b6-4ac9-b8a6-66dd3ac3c81a.png"  width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184656471-9fcd27dc-dea4-4169-82bd-1e0a9c8751a9.png"  width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184656477-e5ee9725-47af-4618-9918-121e3c3b9b43.png"  width="200" height="400"/>

#### ② Habit Tracker
   매일 긍정적인 습관을 위해 구체적인 목표를 적고 좋은 습관을 기르는 것을 통해
   자존감 상승을 꾀할 수 있도록 합니다. 사용자들 자신이 원하는 습관이나 목표를 설정하고, 1개의 목표를 달성할 때마다
1 성취 포인트 씩 적립할 수 있습니다.


</br></br></br><img src="https://user-images.githubusercontent.com/84059942/184656656-e48a96a3-8f78-4875-96e0-0b1c2e2c3a83.png"  width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184656666-7081b512-8d34-4bcc-a43e-835b1d1c8929.png"  width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184656669-b108ab7c-4e39-4877-83fb-020fc4fc3f01.png"  width="200" height="400"/> 

#### ③ 감정 캘린더와 다이어리
   감정 캘린더에서 그동안의 일기와 감정을 찾아볼 수 있고,
   날짜를 클릭하여 그날의 감정과 일기를 기록하여 감정 정리에 도움을 줄 수 있습니다.
   감정 일기에는 나의 하루와 감정을 기록할 수 있고, 또 슬픔, 기쁨, 화남의 정도를
시크바를 통해 0-10까지 입력할 수 있습니다.

</br></br></br><img src="https://user-images.githubusercontent.com/84059942/184656840-37c81372-481b-409e-94af-d6030a660001.png"  width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184656844-9d26658c-2122-4a85-a4a1-46143ab1503f.png"  width="200" height="400"/> 

#### ④ 감정에 따른 음악 추천
   일기에 작성한 감정을 기반으로 이를 수치화하여 감정에 따른 음악을 유튜브 API를 통해
   사용자들에게 제공하였습다. 이것으로 사용자의 감정을 다독일 수 있습니다.

</br></br></br><img src="https://user-images.githubusercontent.com/84059942/184657059-048d862f-3d69-4cb3-ac1f-41c1089c09f3.png" width="200" height="400"/>

#### ⑤ 나무 기르기
   식물은 사용자가 스스로 설정한 좋은 습관이나 목표를 성취하면 사용자와 함께
성장하기 때문에 사용자에게 동기 부여와 함께 자존감을 키울 수 있습니다. 그날의 명언과 성취 포인트 및 성취 포인트로 자라나는 식물의 모습을 볼 수 있습니다.

</br></br></br><img src="https://user-images.githubusercontent.com/84059942/184657206-c444a11a-ebcc-4088-90ee-7a2668dfb372.png" width="200" height="400"/>

#### ⑥ 심리 상태와 부정적 감정 통계
   ①의 심리 상태 진단을 통해 나온 점수를 그래프로 표현하여 사용자가 본인의 상태를 쉽게 파악할 수 있습니다.
    또 ③의 일기에 AI 자연어 분석을 도입해 일기 내용에 부정적인 단어가 쓰여 있으면 
통계 메뉴에서 일자 별 부정적인 단어가 쓰인 빈도수를 그래프로 보여줍니다.

</br></br></br><img src="https://user-images.githubusercontent.com/84059942/184657301-ce390a06-c5f4-467a-9b60-07511133349d.png" width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184657304-4d71e3a7-645d-4386-81e6-8760c8df24b0.png" width="200" height="400"/> <img src="https://user-images.githubusercontent.com/84059942/184657311-62ca89d6-ab05-4a9b-911a-fc305454e71e.png" width="200" height="400"/>

#### ⑦ 미니게임
   알 깨기 게임이나 두더지 게임과 같은 단순한 게임을 구현하여 사용자의 스트레스 완화와 감정 해소에
   도움을 줄 수 있습니다.

</br></br></br><img src="https://user-images.githubusercontent.com/84059942/184657338-a43a5316-d564-4281-b2bd-302ead623bed.jpg" width="200" height="400"/>
#### + 명언 푸시 기능
  사용자가 하루를 시작할 때, 격려를 받으며 생활할 수 있도록 명언이나
  응원 문구를 앱 푸시를 통해 보냅니다. 기능 구현을 위해 Firebase의 Cloud Messaging 기능을 사용하였습니다.



## Result
#### 개발 결과 다른 기능들은 구현을 마칠 수 있었습니다. AI 자연어 분석 기능은 간단한 AI를 학습시킬 수 있었지만 앱에 접목하는 것이 쉽지 않았습니다. 하지만 이에 포기하지 않고 서버와 연결하여 Naver Cloud Platform에서 제공하는 자연어 분석 API를 사용한 앱을 완성하였습니다. LocalDB와 ServerDB 모두 사용하고, 여러가지 플렛폼과 오픈 소스를 활용하여 앱푸시 기능, AI 일기 분석 기능, 그래프 기능 등을 제공하는 앱을 완성시킬 수 있었습니다.
#### 이 프로젝트를 진행하면서 다른사람과 협동할 수 있는 능력과 시간 활용 능력을 기를 수 있었습니다. 또 간단하지만 python을 사용하여 인공지능을 학습시키는 방법도 알 수 있었습니다. 또한 사용자를 위한, 유저친화적인 UI를 고려해 본 경험이 되었습니다. 다양한 플랫폼의 API를 활용하고, 오픈 소스코드를 활용해 볼 수 있는 좋은 프로젝트였다고 생각합니다.

</br>

### 2021 이브와 멘토링 공모전 결과 : 입선

## Technology Stack
#### Frontend : XML
#### Backend : java
#### Database : SQL
#### IDE : Eclipse(jsp, Mysql), Android Studio(Mobile App, SQLite)
#### Platform : Naver Cloud Platform, Firebase, Google Colab
