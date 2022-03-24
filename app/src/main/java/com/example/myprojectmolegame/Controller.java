package com.example.myprojectmolegame;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
public class Controller {
    // TODO (maybe in model) - count points - or num of moles killed or time of game in seconds
    private int score=0;
    private ThreadMolePopUp thread;
    private MainActivity activity ;
    public int numOfHoles=9;
    private Model model;
    private int streak=0;


    public Controller(MainActivity activity) {
        this.activity = activity;
        model=new Model(numOfHoles);
        initiateThread();
    }
    // when the mole is clicked cheek if its a mole or an hole and if true then display an hole insted of the mole
    public void moleClicked(int id){
        int hit= model.hit(id);
        if(hit >0){
            activity.displayElement(id,Element.HOLE);
            activity.displayScore(Score());
        }
    }

    public int Score (){
        streak++;
        if (streak>1){
            if (streak>5){
                score=score+5;;
            }
            else{
                score=score+streak;
            }
        }
        else score++;
        return score;
    }

    // removing hole slot from the list and asking the mainActivity to display a mole
    public void moleAppear() {
        int holeNum = model.generateMole();
//        if (holeNum == 8) {
//            Log.d("9ISPICKED", "picked");
//
//        }
        if (holeNum!=-1){
            activity.displayElement(holeNum,Element.MOLE);
        }
        else {
            lose();
        }

    }

    private void lose() {
        stopThread();
        activity.displayLose();
        model=new Model(numOfHoles);
    }

    private void initiateThread() {

        Handler handlerForMoleTimer =new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                moleAppear();


            }
        };

        thread =  new ThreadMolePopUp(handlerForMoleTimer);
        thread.start();
    }
    public void stopThread(){
        thread.setRun(false);
    }
    public void startThread(){
        initiateThread();
    }

    public void clearScore() {
        score=0;
    }
    public void clearStreak(){
        streak=0;
    }


}
