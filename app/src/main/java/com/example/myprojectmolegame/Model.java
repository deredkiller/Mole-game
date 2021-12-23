package com.example.myprojectmolegame;


import java.util.Random;

public class Model {
   private Element[] holes;
   private Random random=new Random();

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
     public void generateMole(){
        //int putMoleInThisHole= random.nextInt(holes.length);
         int putMoleInThisHole=0;
        holes[putMoleInThisHole]=Element.MOLE;

    }


}
