package com.example.myprojectmolegame;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

public class ThreadMolePopUp extends  Thread {
    Handler handler;
    Controller controller;
    public ThreadMolePopUp(Handler handler) {
        this.handler = handler;

    }

    @Override
    public void run() {
        super.run();
        while(true){
            SystemClock.sleep(2000);


            Message msg = new Message();
            msg.obj=0;
            handler.sendMessage(msg);

        }
    }

}

