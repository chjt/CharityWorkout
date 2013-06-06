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

public class Screen3 extends Activity {
    Button thankyou, history;
    ImageView imgView;
    private int y;
   // private static final String DEBUG_TAG = "Gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_3);
        thankyou = (Button) findViewById(R.id.thankyou);
        history = (Button) findViewById(R.id.history);
        thankyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent random = new Intent("android.c1.CharityWorkout.THANKYOU");
                startActivity(random);
            }
        });
                
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