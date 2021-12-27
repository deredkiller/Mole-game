package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
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
    public final int MOLE = 0;
    public final int HOLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        dynamicLayout();
        controller = new Controller(this);

    }


    @Override
    public void onClick(View view) {

        int id = (int) view.getId();
        controller.moleClicked(id);


    }

    public void dynamicLayout() {
        LinearLayout llMainDynamic = findViewById(R.id.llDynamic);


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
                ImageView fuckimage = new ImageView(this);
//                fuckimage.setTag((i *3) + (j + 1));
                fuckimage.setId((i *3) + (j + 1));
                fuckimage.setLayoutParams(elementLayout);
                fuckimage.setImageResource(R.drawable.hole);
                fuckimage.setOnClickListener(this);
                rowInboard.addView(fuckimage);

            }
            LinearLayoutBoard.addView(rowInboard);

        }
        llMainDynamic.addView(LinearLayoutBoard);

    }

    public void displayElement(int holeNum, int element) {

        int image = R.drawable.hole;
        if (element == MOLE) image = R.drawable.mole;
        ImageView imageView = findViewById(holeNum);

        imageView.setImageResource(image);
    }


}
// i need to to a dynamic layout so i can create random holes placment