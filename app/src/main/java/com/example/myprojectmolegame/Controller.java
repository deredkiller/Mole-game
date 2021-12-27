package com.example.myprojectmolegame;

public class Controller {
private MainActivity activity ;
public int numOfHoles=9;
private Model model=new Model(numOfHoles);

    public Controller(MainActivity activity) {
        this.activity = activity;
        initiateThread();
    }

    public void moleClicked(int tag){
       if( model.hit(tag)){
           activity.displayElement(tag,activity.HOLE);
       }
       //need to get -life, i still need to cheek if it will be with life or a timer,if it will be timer then he will get -sec


    }


    public void moleAppear() {
    int holeNum = model.generateMole();
    activity.displayElement(holeNum+1,activity.MOLE);

    }

    public void initiateThread() {
        model.initiateThread(this);
    }
}
