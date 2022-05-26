package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin;
    private EditText editTextForLoginName;
    private SharedPreferences sharedPref;
    private String userNameData;
    public static final String LOGIN_KEY = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        editTextForLoginName=findViewById(R.id.editTextNickname);
        sharedPref=getSharedPreferences("myPrefs", MODE_PRIVATE);
        editTextForLoginName.setText(sharedPref.getString(LOGIN_KEY,""));
        initViews();

    }

    private void initViews(){
        btnLogin=findViewById(R.id.btnLogin123);
        btnLogin.setOnClickListener(this);

}

    @Override
    public void onClick(View view) {

       userNameData=editTextForLoginName.getText().toString();
        Log.d("yo","username"+userNameData);
        Intent intent =new Intent(this, Menu.class);
        intent.putExtra(LOGIN_KEY,userNameData);
        startActivity(intent);
    }
}
/// TODO: 10/02/2022 activity for changing mode and see the score
/// TODO: 10/02/2022 score system
/// TODO: 10/02/2022 clock for the score
/// TODO: 10/02/2022 database for score
/// TODO: 10/02/2022 u can retry the game after it showes u lose
/// TODO: 10/02/2022 setings activity (or pop up window)
/// TODO: 10/02/2022 broadcast reciever when u get a call it pause the clock and the game
/// TODO: 10/02/2022 song for the game
/// TODO: 10/02/2022 sharedPref for the username