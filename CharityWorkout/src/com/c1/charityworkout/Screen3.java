package com.c1.charityworkout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
//import android.support.v4.view.MotionEventCompat;
//import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
//import android.support.v4.view.GestureDetectorCompat;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Screen3 extends Activity {
    Button thankyou;
    ImageView imgView;
    private int y;
    private int seconds = 55;
    private int minutes =59;
    private int hours =0;
    private String secondenstring = "";
    private String minutenstring = "";
    // private static final String DEBUG_TAG = "Gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_3);
        thankyou = (Button) findViewById(R.id.thankyou);
        // history = (Button) findViewById(R.id.history);
        thankyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent random = new Intent("android.c1.CharityWorkout.THANKYOU");
                startActivity(random);
            }
        });
        //Declare the timer
        Timer t = new Timer();
//Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                //We must use this function in order to change the text view text
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        if(seconds<10){
                            secondenstring="0"+(String.valueOf(seconds));
                        }
                        else{secondenstring=String.valueOf(seconds);
                        }
                        minutenstring=String.valueOf(minutes);
                        if (hours>0){
                            if(minutes<10){
                                minutenstring="0"+(String.valueOf(minutes));
                            }}
                        tv.setText("Tijd= "+hours+":"+minutenstring+":"+ secondenstring);
                        seconds += 1;
                        if (minutes==60){
                            hours +=1;
                            minutes = 0;
                        }if(seconds==60){
                            seconds = 0;
                            minutes +=1;
                        }
                    }

                });
            }

        },
//Set how long before to start calling the TimerTask (in milliseconds)
                0,
//Set the amount of time between each execution (in milliseconds)
                1000);

        y = com.c1.charityworkout.MainActivity.x;
        imgView =(ImageView) findViewById(R.id.imageView2);
        Drawable image2 = getResources().getDrawable(y);
        imgView.setImageDrawable(image2);



    }
    // @Override
    //    public boolean onTouchEvent (MotionEvent event){
    //  float x1, x2, y1, y2;
    //String direction;
    //    switch(event.getAction()) {
    //      case(MotionEvent.ACTION_DOWN):
    //        x1 = event.getX();
    //       y1 = event.getY();
    //      break;
    //   case(MotionEvent.ACTION_UP):
    //        x2 = event.getX();
    //        y2 = event.getY();
    //        float differenceInX = x2-x1;
    //        float differenceInY = y2-y1;

    // Use dx and dy to determine the direction
    //        if(Math.abs(differenceInX) > Math.abs(differenceInY)) {
    //            if(differenceInX > 0) direction = "right";
    //            else direction = "left";
    //        } else {
    //            if(differenceInY > 0) direction = "down";
    //           else direction = "up";
    //       }
    //       break;
    //       }
    //  }
}