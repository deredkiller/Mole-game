package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
    private DBHelper dataBase=new DBHelper(this) ;
    private static final String DATABASENAME ="result.db" ;
    private static final int DATABASEVERSION = 1;
    private static final String TABLE_RECORD = "tblresult";
    private static final String COLUMN_ID ="_id" ;
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SCORE = "score";
    private static final  String[] allColumns ={COLUMN_ID,COLUMN_NAME,COLUMN_SCORE};
    private static final String CREATE_TABLE_USER = "CREATE TABLE  IF NOT  EXISTS " +  TABLE_RECORD
            +"("+ COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+"TEXT,"+COLUMN_SCORE+" INTEGER );";
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        dynamicLayoutConstruction();
        controller = new Controller(this);
        insert();
//        makeBackroundVideo();

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
        imgPause = findViewById(R.id.pause);

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

    @Override
    public void onClick(View view) {
        Log.d("hello", "onClick: " + view.getTag().toString());
        if (view.getTag().equals("btnRetry")) {
            llMainDynamic.removeAllViews();
            for (int i = 0; i < 9; i++) {
                imgArray[i].setImageResource(R.drawable.hole);
            }
            llMainDynamic.addView(linearLayoutBoard);
            LinearLayoutScore1.addView(scoreView);
            scoreView.setText("score");
            controller.startThread();
        } else {
            if (view.getTag().equals("pause")) {
                controller.clearStreak();
                controller.clearScore();
            } else {
                controller.moleClicked((int) view.getTag());
            }
        }

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
        btnRetry = new ImageButton(this);
        btnRetry.setImageResource(R.drawable.reloading);
        btnRetry.setBackgroundResource(R.drawable.white);
        btnRetry.setOnClickListener(this);
        btnRetry.setTag("btnRetry");
        LinearLayout.LayoutParams retryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnRetry.setLayoutParams(retryParams);
        llMainDynamic.addView(btnRetry);
    }
    public void  insert(){
        dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,"ETHAN");
        values.put(COLUMN_SCORE,120);
        database.close();
    }

    public void displayScore(int score) {scoreView.setText("score:" + score);
    }


}
