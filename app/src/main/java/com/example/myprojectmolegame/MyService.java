package com.example.myprojectmolegame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service {
    MediaPlayer player;

    public MyService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(this,R.raw.background_music);
        player.setLooping(true);
        player.setVolume(100,100);
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        Log.d("supertag","supercomment");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        player.stop();
    }


}