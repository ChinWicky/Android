package com.example.lenovo.application_1214.weather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 45346 on 2017/08/03.
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

}
