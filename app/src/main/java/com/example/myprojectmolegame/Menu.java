package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;



public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button btnPlay, btnScore, btnInstructions, btnSettings;
    String userName;
    Intent musicIntent;


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        musicIntent = new Intent(this, MyService.class);
        startService(musicIntent);
        setContentView(R.layout.activity_menue);
        btnPlay = findViewById(R.id.btnPlay);
        btnScore = findViewById(R.id.btnScore);
        btnInstructions = findViewById(R.id.btnInstructions);
        btnSettings = findViewById(R.id.btnSettings);
        btnPlay.setOnClickListener(this);
        btnScore.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnInstructions.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == btnPlay) {
            Intent intent = new Intent(this, MainActivity.class);
            Intent oldIntent = getIntent();
            userName = oldIntent.getStringExtra("USERNAME");
            intent.putExtra("USERNAME", userName);
            startActivity(intent);
        }
        if (view == btnScore) {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
        }
        if (view == btnSettings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        if (view == btnInstructions) {
            Intent intent = new Intent(this, Instructions.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(musicIntent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        stopService(musicIntent);
    }
}