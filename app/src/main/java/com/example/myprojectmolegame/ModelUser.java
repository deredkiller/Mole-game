package com.example.myprojectmolegame;

public class ModelUser {

    String name;
    long id;
    int score;

    public ModelUser(String name, int score) {
        this.name = name;
        this.score = score;
    }


    public String getName (){
        return name;
    }
    public int getScore(){
        return score;
    }
    public long setId (long id){
        this.id=id;
        return id;
    }




    }


