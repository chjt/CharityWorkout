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
import java.util.*;

public class Screen3 extends Activity {
    Button thankyou, startbutton;
    ImageView imgView;
    private int y;
    private long tijd1;
    private long tijdpauze;
    public static long tijd;
    // private static final String DEBUG_TAG = "Gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_3);
        rendering();
        
    }


    public void timer(View view)
    {  startbutton = (Button) view;
       if (startbutton.getText().equals("Start"))
       {
           tijd1 = System.currentTimeMillis();
           startbutton.setText("Pauze");
           //Declare the timer
           Timer t = new Timer();
//Set the schedule function and rate
           t.scheduleAtFixedRate(new TimerTask() {

               @Override
               public void run() {
                   TextView WorkoutText = (TextView) findViewById(R.id.textView);
                   WorkoutText.setText(String.valueOf(System.currentTimeMillis()-tijd1));
               }

           },
//Set how long before to start calling the TimerTask (in milliseconds)
                   0,
//Set the amount of time between each execution (in milliseconds)
                   1000);
       }
       else if(startbutton.getText().equals("Pauze"))
       {
           tijdpauze = System.currentTimeMillis();
           startbutton.setText("Doorgaan");
       }
       else if(startbutton.getText().equals("Doorgaan"))
       {
           tijd1 += (System.currentTimeMillis()-tijdpauze);
           startbutton.setText("Pauze");
       }

    }

    public void timerstop(View view)
    {   thankyou = (Button) view;
        tijd = System.currentTimeMillis()-tijd1;
        thankyou.setText(String.valueOf(tijd));

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

	private void rendering() {
		// TODO Auto-generated method stub
		thankyou = (Button) findViewById(R.id.thankyou);
        // history = (Button) findViewById(R.id.history);
		y = com.c1.charityworkout.MainActivity.x;
        imgView =(ImageView) findViewById(R.id.imageView2);
        Drawable image2 = getResources().getDrawable(y);
        imgView.setImageDrawable(image2);
        thankyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent random = new Intent("android.c1.CharityWorkout.THANKYOU");
                startActivity(random);
            }
        });
	}

    }

	
