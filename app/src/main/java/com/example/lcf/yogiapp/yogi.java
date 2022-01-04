package com.example.lcf.yogiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lcf.R;

public class yogi extends AppCompatActivity {
    Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        button1 = findViewById(R.id.startyoga1);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(yogi.this,SecondActivity.class);
                startActivity(intent);
            }
        });


    }

    public void beforeage18(View view) {

        Intent intent = new Intent(yogi.this,SecondActivity.class);
        startActivity(intent);




    }

    public void food(View view) {

        Intent intent = new Intent(yogi.this,FoodActivity.class);
        startActivity(intent);


    }
}