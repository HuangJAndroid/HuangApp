package com.huangj.myapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.huangj.myapp.R;

import java.util.Map;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import static com.huangj.myapp.R.id.videoView;

public class VideoActivity extends AppCompatActivity {

    private String path = "http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/" +
            "hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        setContentView(R.layout.activity_video);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
//        videoView.setVideoPath(path);
//        videoView.setMediaController(new MediaController(this));
//        videoView.requestFocus();
//
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                // optional need Vitamio 4.0
//                mediaPlayer.setPlaybackSpeed(1.0f);
//            }
//        });
//        videoView.start();
//
//        if (Vitamio.initialize(this)){
//            videoView.setVideoURI(Uri.parse(path));
//            MediaController controller = new MediaController(this);
//            videoView.setMediaController(controller);
//            videoView.start();
//        }else {
//            Log.e("======","false");
//        }

        videoView.setVideoURI(Uri.parse(path)); //设置播放路径
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        // 设置video的控制器
        videoView.setMediaController(new MediaController(this));

    }
}
