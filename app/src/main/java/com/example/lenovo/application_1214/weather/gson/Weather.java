package com.example.lenovo.application_1214.weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 45346 on 2017/08/03.
 */

public class Weather {

    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
