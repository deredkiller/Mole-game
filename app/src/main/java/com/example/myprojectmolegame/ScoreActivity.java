package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    private TextView scoreList;
    private DBHelper dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreList=findViewById(R.id.scoreList);
        dataBase= new DBHelper(this);
showScores();

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    public void showScores(){

        scoreList.setText(dataBase.selectAll().toString());


    }
}