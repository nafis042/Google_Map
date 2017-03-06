package com.example.nafis.google_map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nafis on 06/03/2017.
 */

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle bundle=getIntent().getExtras();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<LatLng> points=new ArrayList<>();
        MainActivity ob=null;
        for(int i=0;i<1;i++)
        {
          //  points.add(new LatLng( Double.parseDouble(ob.results.get(i).getLatitude()),Double.parseDouble(ob.results.get(i).getLongitude())));
         points.add(new LatLng(32,90));
        }
        points.add(new LatLng(35,90));
        points.add(new LatLng(36,90));
        points.add(new LatLng(30,90));
        for(int i=0;i<points.size();i++)
        {
       // BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.capture);

        mMap.addMarker(new MarkerOptions().position(points.get(i)).title("vehicle"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(points.get(i)));
        }

    }
}
