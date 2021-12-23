package com.example.myprojectmolegame;

public class Controller {
private MainActivity activity ;
private Model model=new Model(4);

    public Controller(MainActivity activity) {
        this.activity = activity;
    }

    public void moleClicked(int tag){
       if( model.hit(tag)){
           activity.displayHole(tag);
       }
       //need to get -life, i still need to cheek if it will be with life or a timer,if it will be timer then he will get -sec


    }
    

}
