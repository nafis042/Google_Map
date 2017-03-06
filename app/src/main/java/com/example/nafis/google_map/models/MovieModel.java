package com.example.nafis.google_map.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nafis on 05/03/2017.
 */

public class MovieModel {
    private String name;
    private String longitude;
    private String latitude;
    private String image;

    public MovieModel()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
