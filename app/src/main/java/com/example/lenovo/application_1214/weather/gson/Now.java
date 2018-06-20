package com.example.lenovo.application_1214.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 45346 on 2017/08/03.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }

}
