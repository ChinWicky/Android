package com.example.lenovo.application_1214.video;


import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.lenovo.application_1214.R;

public class VideoActivity extends Activity {



    private VideoView video;

          /** Called when the activity is firstcreated. */
          @Override
     public void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
                 setContentView(R.layout.video);

                 video=(VideoView) findViewById(R.id.video);
                 MediaController mc=new MediaController(VideoActivity.this);       // 创建一个MediaController对象
                 video.setMediaController(mc);       // 将VideoView与MediaController关联起来
                 video.setVideoURI(Uri.parse("android.resource://com.example.lenovo.application_1214/" + R.raw.aaa));

                  video.requestFocus();       // 设置VideoView获取焦点

            try {
                         video.start();      // 播放视频
                       }catch(Exception e) {
                         e.printStackTrace();
                      }

                 // 设置VideoView的Completion事件监听器
                 video.setOnCompletionListener(new OnCompletionListener() {
                          @Override
                         public void onCompletion(MediaPlayer mp) {
                                  Toast.makeText(VideoActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
                                  finish();
                              }
                        });


            }

}
