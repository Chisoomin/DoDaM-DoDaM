package com.example.dodam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    Integer SetPoint;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_screen );

        c = (ConstraintLayout) findViewById( R.id.Main );
        point = (TextView) findViewById( R.id.point );
        Fsaying = (TextView) findViewById( R.id.Fsaying );
        Fpeople = (TextView) findViewById( R.id.Fpeople );

        RequestQueue requestQueue = Volley.newRequestQueue( this );

        String url = "http://192.168.56.1:8080/AndroidAppEx/saying.jsp";
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        re = response;
                        saying = re.split( "#" );
                        Fsaying.setText( "\"" + saying[0].trim() + "\"" );
                        Fpeople.setText( "- " + saying[1].trim() );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                } );
        requestQueue.add( stringRequest );

        DBHelper helper = new DBHelper( getApplicationContext() );
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery( "select point, _id from Dodam;", null );

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt( 1 );
            if (id == 2) {
                SetPoint = cursor.getInt( 0 );
            }
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                    try
                    {
                        Thread.sleep(1000); //1초 간격으로 실행
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                DBHelper helper = new DBHelper( getApplicationContext() );
                                SQLiteDatabase db = helper.getReadableDatabase();
                                cursor = db.rawQuery( "select point, _id from Dodam;", null );
                                while (cursor.moveToNext()) {
                                    Integer id = cursor.getInt( 1 );
                                    if (id == 2) {
                                        SetPoint = cursor.getInt( 0 );
                                    }
                                }
                                point.setText( " " + SetPoint );
                                MyPoint = SetPoint;
                                if (MyPoint >= 10 && MyPoint < 25) {
                                    c.setBackgroundResource( R.drawable.main_2 );
                                }
                                if (MyPoint >= 25 && MyPoint < 45) {
                                    c.setBackgroundResource( R.drawable.main_3 );
                                }
                                if (MyPoint >= 45 && MyPoint < 70) {
                                    c.setBackgroundResource( R.drawable.main_4 );
                                }
                                if (MyPoint >= 70) {
                                    c.setBackgroundResource( R.drawable.main_5 );
                                }

                            }
                        });
                    }
                    catch (InterruptedException e)
                    {
                        // error
                    }
            }
        }).start();
        //point.setText( " " + SetPoint );
        //서버DB 연결 후 가져와서 명언 불러오는 것 구현
        //포인트 상의
        /*try {
            MyPoint = SetPoint;
        } catch (
                NumberFormatException nfe) {
            System.out.println( "Could not parse " + nfe );
        }

        if (MyPoint >= 10 && MyPoint < 25) {
            c.setBackgroundResource( R.drawable.main_2 );
        }
        if (MyPoint >= 25 && MyPoint < 45) {
            c.setBackgroundResource( R.drawable.main_3 );
        }
        if (MyPoint >= 45 && MyPoint < 70) {
            c.setBackgroundResource( R.drawable.main_4 );
        }
        if (MyPoint >= 70) {
            c.setBackgroundResource( R.drawable.main_4 );
        }*/
    }
}
