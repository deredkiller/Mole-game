package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img1, img2, img3, img4;
    private Controller controller;
    private int imgPos[] = new int[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        controller = new Controller(this);
        initiateImageViws();
        dynamicLayout();


    }

    private void initiateImageViws() {
        img1 = findViewById(R.id.imageView);
        Log.d("TSG", img1.toString());
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
//i need to do an array for the img
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

        img1.setTag(0);
        img2.setTag(1);
        img3.setTag(2);
        img4.setTag(3);
    }

    @Override
    public void onClick(View view) {
        Log.d("ON CLICK", "img1: " + img1);
        int tag = (int) view.getTag();
        controller.moleClicked(tag);


    }

    public void dynamicLayout() {
        LinearLayout llMainDynamic = findViewById(R.id.llDynamic);
        Log.d("TDG", "hole number: " + llMainDynamic);

        llMainDynamic.setOrientation(LinearLayout.VERTICAL);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        LinearLayout LinearLayoutBoard = new LinearLayout(this);
        LinearLayoutBoard.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams BoardParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //IF THE MARGINS ARE EFFED UP CHANGE THE "height/3"
        BoardParams.setMargins(0, height / 4, 0, 0);
        LinearLayoutBoard.setLayoutParams(BoardParams);
        LinearLayout.LayoutParams rowLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height / 6);
        //switch size with. the height
        rowLayout.setMargins(width / 5, 1, width / 5, 1);
        LinearLayout.LayoutParams elementLayout = new LinearLayout.LayoutParams(width / 5, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout rowInboard;
        for (int i = 0; i < 3; i++) {
            rowInboard = new LinearLayout(this);
            rowInboard.setLayoutParams(rowLayout);
            rowInboard.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < 3; j++) {
//                Button b =new Button(this);
//                b.setLayoutParams(elementLayout);
//                b.setText(i+"hole"+j);
//                rowInboard.addView(b);
                ImageView img1 = new ImageView(this);
                img1.setLayoutParams(elementLayout);
                img1.setImageResource(R.drawable.hole);
                rowInboard.addView(img1);

            }
            LinearLayoutBoard.addView(rowInboard);

        }
        llMainDynamic.addView(LinearLayoutBoard);

    }


    public void displayHole(int holeNum) {
        Log.d("TDG", "hole number: " + holeNum);
        Log.d("TDG", "img1: " + img1);


        switch (holeNum) {
            case 0:
                img1.setImageResource(R.drawable.hole);
                break;
            case 1:
                img2.setImageResource(R.drawable.hole);
                break;
            case 2:
                img3.setImageResource(R.drawable.hole);
                break;
            case 3:
                img4.setImageResource(R.drawable.hole);
                break;

        }
    }


}
// i need to to a dynamic layout so i can create random holes placment