package com.example.dodamver3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Hos_Map extends AppCompatActivity {
    SupportMapFragment mapFragment;
    GoogleMap map;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_map);

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        tv = findViewById(R.id.textView30);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                tv.setText("지도로드");
                map=googleMap;
                LatLng curPoint = new LatLng(37.5846,126.9252);
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 18));

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("pin2","drawable",getPackageName()));
                bitmap = Bitmap.createScaledBitmap(bitmap, 70, 100,false);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(curPoint);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                markerOptions.title("명지전문대학");
                map.addMarker(markerOptions);

                Geocoder geocoder = new Geocoder(getApplicationContext());
                try{
                    List<Address> list = geocoder.getFromLocation(37.5846,126.9252, 10);
                    if(list == null){
                        tv.setText("주소정보없음");
                    }
                    else{
                        tv.setText(list.get(0).getAddressLine(0).toString());
                    }
                }catch (Exception e){

                }
            }
        });
    }
}