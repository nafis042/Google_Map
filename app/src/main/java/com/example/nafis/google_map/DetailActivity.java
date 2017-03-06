package com.example.nafis.google_map;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nafis.google_map.models.MovieModel;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Nafis on 05/03/2017.
 */

public class DetailActivity extends ActionBarActivity {
    //private ImageView ivMovieIcon;
    private TextView tvName;
    private TextView tvLongitude;
    private TextView tvLatitude;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // setting up text views and stuff
        setUpUIViews();

        // recovering data from MainActivity, sent via intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String json = bundle.getString("movieModel"); // getting the model from MainActivity send via extras
            MovieModel movieModel = new Gson().fromJson(json, MovieModel.class);

            // Then later, when you want to display image
            //ImageLoader.getInstance().displayImage(movieModel.getImage(), ivMovieIcon, new ImageLoadingListener() {
            //    @Override
             //   public void onLoadingStarted(String imageUri, View view) {
             //       progressBar.setVisibility(View.VISIBLE);
             //   }

             //   @Override
             //   public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
             //       progressBar.setVisibility(View.GONE);
             //   }

             //   @Override
             //   public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
             //       progressBar.setVisibility(View.GONE);
             //   }

              //  @Override
             //   public void onLoadingCancelled(String imageUri, View view) {
              //      progressBar.setVisibility(View.GONE);
             //   }
           // });

            tvName.setText(movieModel.getName());
            tvLongitude.setText("Longitude:" + movieModel.getLongitude());
            tvLatitude.setText("Latitude:" + movieModel.getLatitude());

        }

    }

    private void setUpUIViews() {
       // ivMovieIcon = (ImageView)findViewById(R.id.ivIcon);
        tvName = (TextView)findViewById(R.id.tvMovie);
        tvLongitude = (TextView)findViewById(R.id.tvDuration);
        tvLatitude = (TextView)findViewById(R.id.tvDirector);
       // progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
