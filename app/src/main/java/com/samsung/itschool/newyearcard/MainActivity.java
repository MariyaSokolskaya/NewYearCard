package com.samsung.itschool.newyearcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    AudioPlayer aPlayer;

    Button hohoho, bell;

    //переменные для использования SoundPool
    SoundPool sPool;
    boolean isLoad = false;
    int soundID1, soundID2;
    int streamID1, streamID2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        hohoho = findViewById(R.id.hohoho);
        bell = findViewById(R.id.bell);

        aPlayer = new AudioPlayer(this);
        aPlayer.play();

        //работам с SoundPool
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        sPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        sPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                isLoad = true;
            }
        });

        soundID1 = sPool.load(this,R.raw.hohoho, 1);
        soundID2 = sPool.load(this, R.raw.kolokol, 1);

        //if(isLoad){
            hohoho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    streamID1 = sPool.play(soundID1, 1f, 1f, 1, 0, 1f);
                }
            });
            bell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    streamID2 = sPool.play(soundID2, 1f, 1f, 1, 0, 1f);
                }
            });
        //}
    }

    @Override
    protected void onStop() {
        super.onStop();
        aPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        aPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        aPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        aPlayer.play();
    }
}
