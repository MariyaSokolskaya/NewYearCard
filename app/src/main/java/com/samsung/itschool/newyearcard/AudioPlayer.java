package com.samsung.itschool.newyearcard;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
    MediaPlayer mPlayer;
    public AudioPlayer(Context context){
        //создаем плейер и загружаем файл
        mPlayer = MediaPlayer.create(context, R.raw.jingle_bell_rock);

    }

    public void play(){
        if (mPlayer != null){
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                }
            });
        }//
    }

    public void stop(){
        if(mPlayer != null){
            mPlayer.release();

        }
    }

    public void pause(){
        if(mPlayer != null){
            mPlayer.pause();
        }
    }
}
