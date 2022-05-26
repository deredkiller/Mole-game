package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        tv=findViewById(R.id.textView2);
        tv.setText("\nהגיעה הזמן החפרפרות התחילו את הפלישה."+"\n  על מנת לעצור אותם תיצטרך ללחוץ על כל החפרפרות על המסך לפני שהמסך יתמלא והחפרפרות יפלשו לעולם," +"\nבהצלחה!");
    }
}