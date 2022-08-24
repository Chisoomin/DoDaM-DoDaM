package com.example.dodamver3;

import static com.example.dodamver3.MainActivity.hope;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Statistics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Statistics extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Statistics() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Statistics.
     */
    // TODO: Rename and change types and number of parameters
    public static Statistics newInstance(String param1, String param2) {
        Statistics fragment = new Statistics();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    BarChart barChart;
    HorizontalBarChart horizontalBarChart;
    ArrayList<Integer> jsonList = new ArrayList<>(); // ArrayList 선언
    ArrayList<String> labelList = new ArrayList<>(); // ArrayList 선언
    ImageButton imageButton;
    String response;
    int negative=0;
    String clientId = "9k7yq1sy4c";             // Application Client ID";
    String clientSecret = "VNLTV1AKiGNCz6SuJMHiVcd0EWbvntrVcwCmG9Pl";     // Application Client Secret";
    int i = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        barChart = view.findViewById(R.id.BarChart_test);
        horizontalBarChart = view.findViewById(R.id.bar_negative);
        ArrayList<Entry> values = new ArrayList<>();
        imageButton = view.findViewById(R.id.questionMark1);

        graphInitSetting();       //그래프 기본 세팅

        BarChartGraph(labelList, jsonList);
        barChart.setTouchEnabled(false); //확대하지못하게 막아버림! 별로 안좋은 기능인 것 같아~
        barChart.getAxisRight().setAxisMinValue(0);
        barChart.getAxisLeft().setAxisMinValue(0);
        barChart.getAxisRight().setAxisMaxValue(3);
        barChart.getAxisLeft().setAxisMaxValue(60);


        BarData data = new BarData(getXAxisValues(), getDataSet());
        horizontalBarChart.setData(data);
        horizontalBarChart.animateXY(1000, 1000);
        horizontalBarChart.invalidate();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hope == 3) {
                    ShowIntro("심리 진단 그래프", "심리 진단 결과를 그래프로 확인할 수 있어요.", view.getRootView(), R.id.BarChart_test, 1);
                }
            }
        });


        return view;
    }

    private BarDataSet getDataSet() {
        DiaryDBHelper diaryDBHelper = new DiaryDBHelper(getContext());
        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id, negative from DiaryData;", null);
        int[] negative = new int[1000];
        int j =0;
        while (cursor.moveToNext()) {
            i = cursor.getInt(0);
            negative[j] = cursor.getInt(1);
            j++;
        }

        ArrayList<BarEntry> entries = new ArrayList();
        entries.clear();
        if (i == 1) {
            entries.add(new BarEntry(0, 0));
        }
        if (i == 2) {
            entries.add(new BarEntry(negative[1], 0));
        }
        if (i == 3) {
            entries.add(new BarEntry(negative[2], 0));
            entries.add(new BarEntry(negative[1], 1));
        }
        if (i >= 4) {
            entries.add(new BarEntry(negative[i-1], 0));
            entries.add(new BarEntry(negative[i-2], 1));
            entries.add(new BarEntry(negative[i-3], 2));
        }

        BarDataSet dataset = new BarDataSet(entries, "부정적인 문장 수");
        dataset.setColors(ColorTemplate.PASTEL_COLORS);
        return dataset;
    }

    private ArrayList<String> getXAxisValues() {
        DiaryDBHelper diaryDBHelper = new DiaryDBHelper(getContext());
        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id from DiaryData;", null);

        while (cursor.moveToNext()) {
            i = cursor.getInt(0);
        }
        int k = i - 2;
        int j = 0;

        ArrayList<String> labels = new ArrayList();
        labels.clear();
        if (i == 1) {
            labels.add("일기 미작성");
        }
        if (i == 2) {
            DiaryDBHelper diaryDBH = new DiaryDBHelper(getContext());
            SQLiteDatabase db2 = diaryDBH.getReadableDatabase();
            Cursor cursor2 = db2.rawQuery("select _id, date from DiaryData;", null);
            String[] date = new String[1];

            while (cursor2.moveToNext()) {

                if (cursor2.getInt(0) == 2) {
                    date[j] = cursor2.getString(1);
                    j++;
                }
                if (j == 1) {
                    break;
                }
            }

            labels.add(date[0]+"");
        }
        if (i == 3) {
            DiaryDBHelper diaryDBH = new DiaryDBHelper(getContext());
            SQLiteDatabase db2 = diaryDBH.getReadableDatabase();
            Cursor cursor2 = db2.rawQuery("select _id, date from DiaryData;", null);
            String[] date = new String[2];

            while (cursor2.moveToNext()) {

                if (cursor2.getInt(0) == 2) {
                    date[0] = cursor2.getString(1);
                }
                if(cursor2.getInt(0)==3){
                    date[1]=cursor2.getString(1);
                }
            }

            labels.add(date[1]+"");
            labels.add(date[0]+"");
        }
        if (i >= 4) {
            DiaryDBHelper diaryDBH = new DiaryDBHelper(getContext());
            SQLiteDatabase db2 = diaryDBH.getReadableDatabase();
            Cursor cursor2 = db2.rawQuery("select _id, date from DiaryData;", null);
            String[] date = new String[3];

            while (cursor2.moveToNext()) {

                if (cursor2.getInt(0) == k) {
                    date[j] = cursor2.getString(1);
                    j++;
                    k++;
                }
            }

            labels.add(date[2]+"");
            labels.add(date[1]+"");
            labels.add(date[0]+"");
        }


        return labels;
    }

    public void graphInitSetting() {
        labelList.clear();
        jsonList.clear();

        graphDB graph = new graphDB(getContext());
        SQLiteDatabase db_r = graph.getReadableDatabase();
        Cursor cursor = db_r.rawQuery("select date, score from Graph;", null);

        while (cursor.moveToNext()) {
            String[] date = new String[3];
            date = cursor.getString(0).split(" ");
            labelList.add(date[1] + date[2]);
            jsonList.add(Integer.valueOf(cursor.getString(1)));
        }

        /*labelList.add("10.12");
        labelList.add("10.13");
        labelList.add("10.14");
        labelList.add("10.15");
        labelList.add("10.16");


        jsonList.add(10);
        jsonList.add(20);
        jsonList.add(30);
        jsonList.add(40);
        jsonList.add(50);*/


        BarChartGraph(labelList, jsonList);
        barChart.setTouchEnabled(false); //확대하지못하게 막아버림! 별로 안좋은 기능인 것 같아~
        //barChart.setRendererLeftYAxis();
//        barChart.setMaxVisibleValueCount(50);
//        barChart.setTop(50);
//        barChart.setBottom(0);
//        barChart.setAutoScaleMinMaxEnabled(true);
        barChart.getAxisRight().setAxisMinValue(0);
        barChart.getAxisLeft().setAxisMinValue(0);
        barChart.getAxisRight().setAxisMaxValue(3);
        barChart.getAxisLeft().setAxisMaxValue(60);
    }

    /**
     * 그래프함수
     */
    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {
        // BarChart 메소드




        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.clear();
        for (int i = 0; i < valList.size(); i++) {
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        BarDataSet depenses = new BarDataSet(entries, "우울 진단 결과"); // 변수로 받아서 넣어줘도 됨
        depenses.setAxisDependency(YAxis.AxisDependency.LEFT);
        barChart.setDescription(" ");

        ArrayList<String> labels = new ArrayList<String>();
        labels.clear();
        for (int i = 0; i < labelList.size(); i++) {
            labels.add((String) labelList.get(i));
        }

        BarData data = new BarData(labels, depenses); // 라이브러리 v3.x 사용하면 에러 발생함
        depenses.setColors(ColorTemplate.PASTEL_COLORS); //

        barChart.setData(data);
        barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }

    private void ShowIntro(String title, String text, View view, int id, final int type) {

        new GuideView.Builder(getContext())
                .setTitle(title)
                .setContentText(text)
                .setTargetView(view.findViewById(id))
                .setContentTextSize(12)//optional
                .setTitleTextSize(14)//optional
                .setDismissType(DismissType.anywhere) //optional - default dismissible by TargetView
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        if (type == 1) {
                            ShowIntro("그래프 설명", "그래프에 대한 설명을 보고 자신의 상태를 진단할 수 있어요.", getView(), R.id.exp, 2);
                        } else if (type == 2) {
                            ShowIntro("일기 기반 그래프", "일기에 적힌 부정적인 단어의 빈도를 한눈에 확인 할 수 있어요.", getView(), R.id.bar_negative, 3);
                        } else if (type == 3) {

                        }
                    }
                })
                .build()
                .show();
    }
}
