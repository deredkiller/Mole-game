package com.example.myprojectmolegame;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Model {
    // TODO - lose situation - all holes are filled - stop hits.
    // TODO boolean gameOver() method
//TODO:fart in uur fac


    private Element[] holes;
    private Random random=new Random();
    private String TAG = "lol";
    private List<Integer> holesList=new ArrayList<Integer>();
    private int streak=0, numOfHoles;



    //constructor creating an array and list that r equal to the num of holes in the board
    public Model(int numHoles) {
        numOfHoles=numHoles;
        this.holes = new Element[numHoles];


        for (int j=0;j<holes.length;j++){
            holesList.add(j);
        }

        for(int i=0;i<holes.length;i++){
            holes[i]=Element.HOLE;
        }

    }
    //cheeking if its a mole that was clicked on and then מעדכן the array
    public int hit(int holeIndex) {
        if (holeIndex>=holes.length){
            return streak;
        }else {
            if(holes[holeIndex]==Element.MOLE ){
                holes[holeIndex]=Element.HOLE;
                holesList.add(holeIndex);
                streak++;
                return streak;
            }
        }
        streak=0;
        return streak;
    }
    //    generating mole in a random hole and returning the hole pos
    public int generateMole(){
        int putMoleInThisHole;
//       i did an if Because if my board is full of moles then my app crash so if its full return -1 and the controller wont ask the view to display a mole
        if (!holesList.isEmpty()) {
            Collections.shuffle(holesList);
//            // DEBUG catch the hole
//            if (holesList.get(0) == 8 && holesList.size() > 1)
//                return generateMole();

            putMoleInThisHole = holesList.remove(0);
            holes[putMoleInThisHole] = Element.MOLE;
        }
        else{
            putMoleInThisHole=-1;
        }
        return putMoleInThisHole;
    }
}
