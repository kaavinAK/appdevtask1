package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent i = getIntent();
        String score = i.getStringExtra("score");
        String day = i.getStringExtra("day");
        TextView wrong  = findViewById(R.id.wrong);
        TextView dayobj = findViewById(R.id.wrongday);
        dayobj.setText("Answer Was "+day);
        wrong.setText(" FINAL SCORE : "+score);
    }
    public void goback(View v)
    {
       Button b = (Button)v;
       Intent i =new Intent(this,MainActivity.class);
       startActivity(i);
    }
}