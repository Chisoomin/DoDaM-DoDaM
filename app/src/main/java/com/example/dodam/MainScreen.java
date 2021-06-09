package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainScreen extends AppCompatActivity {
    TextView point, Fsaying, Fpeople;
    Integer MyPoint;
    String re = "";
    String[] saying;
    ConstraintLayout c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        c = (ConstraintLayout) findViewById(R.id.Main);
        point = (TextView) findViewById(R.id.point);
        Fsaying = (TextView) findViewById(R.id.Fsaying);
        Fpeople = (TextView) findViewById(R.id.Fpeople);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "http://172.30.1.45:8080/AndroidAppEx/saying.jsp";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        re = response;
                        saying = re.split("#");
                        Fsaying.setText("\""+saying[0].trim()+"\"");
                        Fpeople.setText("- "+saying[1].trim());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);




        point.setText("0");

        //서버DB 연결 후 가져와서 명언 불러오는 것 구현
        //포인트 상의
        try {
            MyPoint = Integer.parseInt(point.getText().toString());
        } catch (
                NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        if (MyPoint >= 10 && MyPoint < 25) {
            c.setBackgroundResource(R.drawable.main_ssak);
        }
        if (MyPoint >= 25 && MyPoint < 45) {
            c.setBackgroundResource(R.drawable.main_ssak2);
        }
        if (MyPoint >= 45 && MyPoint < 70) {
            c.setBackgroundResource(R.drawable.main_ssak3);
        }
        if (MyPoint >= 70) {
            c.setBackgroundResource(R.drawable.main_flower);
        }
    }
}
