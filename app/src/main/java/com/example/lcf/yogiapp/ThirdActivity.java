package com.example.lcf.yogiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lcf.R;

public class ThirdActivity extends AppCompatActivity {
    ProgressBar pb;
    int counter = 0;
    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private  boolean MTimeRunning;
    private long MtimeLeftinmills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        int intvalue = Integer.valueOf(buttonvalue);


        switch (intvalue){


            case 1:
                setContentView(R.layout.activity_bow);
                break;
            case 2:
                setContentView(R.layout.activity_bridge);
                break;
            case 3:
                setContentView(R.layout.activity_chair);
                break;
            case 4:
                setContentView(R.layout.activity_child);
                break;
            case 5:
                setContentView(R.layout.activity_cobbler);
                break;
            case 6:
                setContentView(R.layout.activity_cow);
                break;
            case 7:
                setContentView(R.layout.activity_playji);
                break;
            case 8:
                setContentView(R.layout.activity_pauseji);
                break;
            case 9:
                setContentView(R.layout.activity_plank);
                break;
            case 10:
                setContentView(R.layout.activity_crunches);
                break;
            case 11:
                setContentView(R.layout.activity_situp);
                break;
            case 12:
                setContentView(R.layout.activity_rotation);
                break;
            case 13:
                setContentView(R.layout.activity_twist);
                break;
            case 14:
                setContentView(R.layout.activity_windmill);
                break;
            case 15:
                setContentView(R.layout.activity_legup);
                break;
        }

        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.time);



        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MTimeRunning){
                    stoptimer();


                }
                else{
                    startTimer();

                }
            }
        });


    }

    private  void stoptimer()
    {
        countDownTimer.cancel();
        MTimeRunning=false;
        startBtn.setText("START");
    }


    private  void startTimer()
    {
        final CharSequence value1 = mtextview.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);



        final  int number = Integer.valueOf(num2) * 60+ Integer.valueOf(num3);
        MtimeLeftinmills = number*1000;


        countDownTimer = new CountDownTimer(MtimeLeftinmills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {



                MtimeLeftinmills = millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {


                int newvalue = Integer.valueOf(buttonvalue)+1;
                if (newvalue<=7){
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);
                }
                else {

                    newvalue = 1;
                    Intent intent =  new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);


                }


            }
        }.start();
        startBtn.setText("Pause");
        MTimeRunning=true;


    }



    private  void updateTimer(){
        int minutes = (int) MtimeLeftinmills/60000;
        int seconds = (int) MtimeLeftinmills%60000 /1000;




        String timeLeftText ="";
        if(minutes < 10)
            timeLeftText="0";
        timeLeftText = timeLeftText+minutes+":";
        if (seconds<10)
            timeLeftText+="0";
        timeLeftText+=seconds;
        mtextview.setText(timeLeftText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}