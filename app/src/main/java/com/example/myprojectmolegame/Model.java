package com.example.myprojectmolegame;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Random;

public class Model {
   private Element[] holes;
   private Random random=new Random();
    private String TAG = "fuck";
    public void initiateThread(Controller controller) {

        Handler h =new Handler(Looper.myLooper()){
             @Override
                     public void handleMessage(@NonNull Message msg){
                 super.handleMessage(msg);
                 controller.moleAppear();

             }
        };

        ThreadMolePopUp thread = new ThreadMolePopUp(h);
        thread.start();
    }

    public static enum Element{
        MOLE,HOLE;

    }

    public Model(int numHoles) {
        this.holes = new Element[numHoles];
        for(int i=0;i<holes.length;i++){
            holes[i]=Element.HOLE;
            generateMole();
        }
    }

    public boolean hit(int holeIndex) {
//        uses array(0-8) not id(1-9) so -1
        holeIndex=holeIndex-1;
        Log.d(TAG, "hit: "+holeIndex);
        if (holeIndex>=holes.length){
            return false;
        }else {
            if(holes[holeIndex]==Element.MOLE ){
              holes[holeIndex]=Element.HOLE;
                return true;
            }
        }
        return false;
     }
     public int generateMole(){
         int putMoleInThisHole;
         //the a**
         putMoleInThisHole= random.nextInt(holes.length);
         Log.d(TAG, "generateMole: "+putMoleInThisHole);
        //putMoleInThisHole=(putMoleInThisHole-8)*-1;
        holes[putMoleInThisHole]=Element.MOLE;
        return putMoleInThisHole;
    }


}
