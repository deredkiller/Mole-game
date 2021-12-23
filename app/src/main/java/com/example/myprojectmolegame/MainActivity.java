package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img1,img2,img3,img4;
    private Controller controller ;
    private int imgPos []= new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //WOW LOOK AT THAT GIt IS WOrkINGAGAGAGAGAG
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        controller=new Controller(this);
        img1=findViewById(R.id.imageView);
        Log.d("TSG", img1.toString());
        img2=findViewById(R.id.imageView2);
        img3=findViewById(R.id.imageView3);
        img4=findViewById(R.id.imageView4);
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
        int tag =(int)view.getTag();
        controller.moleClicked(tag);


    }


    public void displayHole(int holeNum){
        Log.d("TDG", "hole number: " + holeNum);
        Log.d("TDG", "img1: " + img1);


        switch (holeNum){
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