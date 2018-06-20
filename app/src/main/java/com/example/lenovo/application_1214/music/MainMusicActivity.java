package com.example.lenovo.application_1214.music;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.application_1214.R;

import co.mobiwise.playerview.MusicPlayerView;

public class MainMusicActivity extends Activity {


  private MediaPlayer mPlayer;
  private TextView songName;
  private TextView singerName;
  MusicPlayerView mpv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_music_main);
    initView();
  }
   private void initView(){
       setVolumeControlStream(AudioManager.STREAM_MUSIC);
       mPlayer = MediaPlayer.create(this, R.raw.beautiful);
       songName = findViewById(R.id.textViewSong);
       songName = findViewById(R.id.textViewSinger);


       mpv = (MusicPlayerView) findViewById(R.id.mpv);
       mpv.setMaxProgress(mPlayer.getDuration()/1000);
       mpv.setCoverURL("https://upload.wikimedia.org/wikipedia/en/b/b3/MichaelsNumberOnes.JPG");

       mpv.setOnClickListener(new View.OnClickListener() {
           @Override public void onClick(View v) {
               if (mpv.isRotating()) {
                   mPlayer.stop();
                   mpv.stop();
               } else {
                   mPlayer.start();
                   mpv.start();
               }
           }
       });
   }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_app,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_exit:
                mPlayer.stop();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
