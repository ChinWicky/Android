package com.example.lenovo.application_1214;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.application_1214.broadcast.BroadcastActivity;
import com.example.lenovo.application_1214.camera.CameraActivity;
import com.example.lenovo.application_1214.draw.MainDrawActivity;
import com.example.lenovo.application_1214.game.activity.GameMainActivity;
import com.example.lenovo.application_1214.loginSignin.LoginActivity;
import com.example.lenovo.application_1214.loginSignin.SigninActivity;
import com.example.lenovo.application_1214.map.MapActivity;
import com.example.lenovo.application_1214.music.MainMusicActivity;
import com.example.lenovo.application_1214.screenCapture.ScreenActivity;
import com.example.lenovo.application_1214.video.VideoActivity;
import com.example.lenovo.application_1214.weather.MainWeatherActivity;


public class MainActivity extends Activity {
    View weather ;
    View draw ;
    View game ;
    View map ;
    View video ;
    View music ;
    View camera ;
    View screen ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();

        weather.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainWeatherActivity.class));
            }
        });
        draw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainDrawActivity.class));
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameMainActivity.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainMusicActivity.class));
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            }
        });
        screen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScreenActivity.class));
            }
        });
    }

    public void initView(){

        weather = findViewById(R.id.radio_button_weather);
        draw = findViewById(R.id.radio_button_draw);
        game = findViewById(R.id.radio_button_game);
        map = findViewById(R.id.radio_button_map);
        video = findViewById(R.id.radio_button_video);
        music = findViewById(R.id.radio_button_music);
        camera = findViewById(R.id.radio_button_camera);
        screen = findViewById(R.id.radio_button_screen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("UID", null)==null){
           // System.out.println("你好");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        else{
        getMenuInflater().inflate(R.menu.menu_main_logined, menu);

            String Uid = sharedPreferences.getString("UID", null);
            //System.out.println("你不好"+Uid);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.communication:
                //finish();
                startActivity(new Intent(this,BroadcastActivity.class));
                break;
            case  R.id.menu_logout:
                finish();
                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.menu_exit:
                finish();
                break;
            case R.id.login:
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.signin:
                finish();
                startActivity(new Intent(this,SigninActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
