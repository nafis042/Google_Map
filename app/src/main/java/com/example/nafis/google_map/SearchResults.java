package com.example.nafis.google_map;

/**
 * Created by 11050 on 3/4/2017.
 */

public class SearchResults {

    private String name = "";
    private String cityState = "";
    private String phone = "";
    public void setName(String name) {
        this.name = name;
        }
    public String getName() {
       return name;
        }
    public void setCityState(String cityState) {
        this.cityState = cityState;
        }
    public String getCityState() {
        return cityState;
        }
    public void setPhone(String phone) {
        this.phone = phone;
        }
    public String getPhone() {
        return phone;
        }
}
