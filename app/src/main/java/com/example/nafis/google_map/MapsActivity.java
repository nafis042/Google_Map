package com.example.nafis.google_map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nafis.google_map.models.MovieModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.nearby.messages.Distance;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


   private GoogleMap mMap;
    String global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle bundle=getIntent().getExtras();
        global=bundle.getString("abc");
        Toast.makeText(MapsActivity.this,global,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        PolylineOptions lineOptions = new PolylineOptions();;
        ArrayList<LatLng> points=new ArrayList<>();
        String []token=global.split(" ");
       // LatLng temp=new LatLng(Double.parseDouble(token[1]),Double.parseDouble(token[0]));
        LatLng temp=new LatLng(23,90);

        points.add(temp);
        points.add(new LatLng(25,90));
        points.add(new LatLng(27,85));
        lineOptions.addAll(points);
        lineOptions.width(10);
        lineOptions.color(Color.RED);
        mMap.addPolyline(lineOptions);
        // Add a marker in Sydney and move the camera
      //  ArrayList <LatLng> v = new ArrayList<>();
        //v.add(new LatLng(token[1], token[0]));
      //  v.add(new LatLng(-30, 151));
      //  v.add(new LatLng(-25, 151));
      //  v.add(new LatLng(-20, 151));
      //  v.add(new LatLng(-40, 151));
       // LatLng sydney = new LatLng(-34, 151);


       // for(int i=0;i<v.size();i++)
       // {
            mMap.addMarker(new MarkerOptions().position(temp).title("vehicle"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
       // }

    }
}
