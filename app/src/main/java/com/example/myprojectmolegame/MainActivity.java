package com.example.myprojectmolegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO more activities:
//    TODO clear the hole list in on click find out how the game show lose after a retry becuase its showing lose when there 1 mole left
//    TODO understand why when u retry it display lose before the last mole is displayed
    // 1. sha'ar - list of game options (new game, continue, settings (maybe), instructions etc.)
    // 2. settings - (... holes in rows or in circles or something else , number of holes)
    // 3. instructions
    // 4. choose - easy, normal, hard (notice model-view-controller)
    // 5. records - after we establish a database TODO
    private TextView scoreView;
    private Controller controller;
    private ImageView imgArray[] = new ImageView[100];
    private ImageView imgPause;
    private LinearLayout linearLayoutBoard;
    private LinearLayout llMainDynamic;
    private LinearLayout LinearLayoutScore1, LinearLayoutPause;
    private ImageButton btnRetry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        dynamicLayoutConstruction();
        controller = new Controller(this);
        setOnClicks();
//        makeBackroundVideo();

    }

    private void setOnClicks() {
        btnRetry = new ImageButton(this);
        btnRetry.setImageResource(R.drawable.reloading);
        btnRetry.setBackgroundResource(R.drawable.white);
        btnRetry.setTag("btnRetry");
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMainDynamic.removeAllViews();
                for (int i = 0; i < 9; i++) {
                    imgArray[i].setImageResource(R.drawable.hole);
                }
                llMainDynamic.addView(linearLayoutBoard);
                LinearLayoutScore1.addView(scoreView);
                LinearLayoutPause.addView(imgPause);
                scoreView.setText("score");
                controller.startThread();
                controller.clearScore();
                controller.clearStreak();

            }

        });


        imgPause = findViewById(R.id.pause);
        imgPause.setTag("pause");
        imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag().equals("pause")) {
                    controller.stopThread();
                    imgPause.setTag("paused");
                } else {
                    controller.startThread();
                    imgPause.setTag("pause");
                }
            }
        });


    }


    @Override
    public void onClick(View view) {
        Log.d("hello", "onClick: " + view.getTag().toString());
        controller.moleClicked((int) view.getTag());
    }

//    private void makeBackroundVideo() {
//        VideoView videoview = (VideoView) findViewById(R.id.adsads);
//        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.diglet_loop);
//        videoview.setVideoURI(uri);
//        videoview.start();
//    }

    public void dynamicLayoutConstruction() {
        LinearLayoutPause = findViewById(R.id.linearLayoutPause);
        LinearLayoutScore1 = findViewById(R.id.linearLayoutScore1);
        LinearLayoutScore1.setOnClickListener(this);
        LinearLayoutScore1.setTag("layoutScore");
        scoreView = findViewById(R.id.scoreView);
        llMainDynamic = findViewById(R.id.llDynamic);
        llMainDynamic.setOrientation(LinearLayout.VERTICAL);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        linearLayoutBoard = new LinearLayout(this);
        linearLayoutBoard.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams BoardParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//משנה עד כמה החורים קרובים ללמעלה של המסך
        BoardParams.setMargins(0, height / 100, 0, 0);
        linearLayoutBoard.setLayoutParams(BoardParams);
        //משנה כמה moles אתה יכול לעשים בlayout   ה verticaly
        LinearLayout.LayoutParams rowLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height / 4);
//        משנה את המרג'ינס לצדדים של האוריזונטל layout
        rowLayout.setMargins(width / 5, 1, width / 5, 1);
//        משנה גודל תמונה
        LinearLayout.LayoutParams elementLayout = new LinearLayout.LayoutParams(width / 5, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout rowInboard;
        int indexRun = 0;
        for (int i = 0; i < 3; i++) {
            rowInboard = new LinearLayout(this);
            rowInboard.setLayoutParams(rowLayout);
            rowInboard.setOrientation(LinearLayout.HORIZONTAL);
            //ROWS OR width
            for (int j = 0; j < 3; j++) {
                imgArray[indexRun] = new ImageView(this);
                imgArray[indexRun].setTag(indexRun);
                imgArray[indexRun].setLayoutParams(elementLayout);
                imgArray[indexRun].setImageResource(R.drawable.hole);
                imgArray[indexRun].setOnClickListener(this);
                rowInboard.addView(imgArray[indexRun]);
                indexRun++;
            }
            linearLayoutBoard.addView(rowInboard);

        }
        llMainDynamic.addView(linearLayoutBoard);
    }


    public void displayElement(int holeNum, Element element) {
        int image = R.drawable.hole;
        if (element == Element.MOLE) image = R.drawable.mole;
        //Log.d("hollo", "num");
        imgArray[holeNum].setImageResource(image);
    }

    //todo change imagebutton to imgae view beacuase u click on the whole screen and it still retry
    public void displayLose() {
        llMainDynamic.removeView(linearLayoutBoard);
        LinearLayoutPause.removeView(imgPause);
        LinearLayoutScore1.removeView(scoreView);
        LinearLayout.LayoutParams retryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnRetry.setLayoutParams(retryParams);
        llMainDynamic.addView(btnRetry);
        displayDialog();

    }

    public void clearScore() {
        scoreView.setText("score: 0");
    }


    public void displayScore(int score) {
        scoreView.setText("score:" + score);
    }

    public void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("are you sure you want to upload this new score to the score board ")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String parts[] = (String.valueOf(scoreView.getText()).split(":"));
                        for(String part: parts) {
                        }
                        Intent oldIntent = getIntent();
                        controller.insert(oldIntent.getStringExtra("USERNAME"),Integer.parseInt(parts[1]));
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //YOUR CODE HERE
                    }
                }).setTitle("upload score?");
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
    }
}

