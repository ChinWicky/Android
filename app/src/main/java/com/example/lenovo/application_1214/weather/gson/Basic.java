package com.example.lenovo.application_1214.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 45346 on 2017/08/03.
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }

}
