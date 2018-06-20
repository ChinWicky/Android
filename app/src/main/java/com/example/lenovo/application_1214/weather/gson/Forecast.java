package com.example.lenovo.application_1214.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 45346 on 2017/08/03.
 */

public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature{
        public String max;
        public String min;
    }

    public class More{
        @SerializedName("txt_d")
        public String info;
    }

}
