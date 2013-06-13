package com.c1.charityworkout;

//import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
//import android.support.v4.view.MotionEventCompat;
//import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.support.v4.view.GestureDetectorCompat;
import android.widget.ImageView;
import android.widget.TextView;

public class Screen3 extends Activity implements OnTouchListener {
	Button thankyou, startbutton;
	float startX, endX;
	GestureOverlayView main;
	ImageView imgView;
	private int y;
	// private long tijd1, tijdpauze;
	public static long tijd;
	TextView workoutText;

	// private static final String DEBUG_TAG = "Gestures";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_3);
		rendering();
	}

	/*
	 * public void timer(View view) { startbutton = (Button) view; if
	 * (startbutton.getText().equals("Start")) { tijd1 =
	 * System.currentTimeMillis(); startbutton.setText("Pauze"); //Declare the
	 * timer Timer t = new Timer(); //Set the schedule function and rate
	 * t.scheduleAtFixedRate(new TimerTask()) {
	 * 
	 * public void run() {
	 * workoutText.setText(String.valueOf(System.currentTimeMillis()-tijd1)); }
	 * 
	 * }, //Set how long before to start calling the TimerTask (in milliseconds)
	 * 0, //Set the amount of time between each execution (in milliseconds)
	 * 1000); } else if(startbutton.getText().equals("Pauze")) { tijdpauze =
	 * System.currentTimeMillis(); startbutton.setText("Doorgaan"); } else
	 * if(startbutton.getText().equals("Doorgaan")) { tijd1 +=
	 * (System.currentTimeMillis()-tijdpauze); startbutton.setText("Pauze"); }
	 * 
	 * }
	 * 
	 * public void timerstop(View view) { thankyou = (Button) view; tijd =
	 * System.currentTimeMillis() - tijd1;
	 * workoutText.setText(String.valueOf(tijd));
	 * 
	 * }
	 */

	private void rendering() {
		// TODO Auto-generated method stub
		thankyou = (Button) findViewById(R.id.thankyou);
		// history = (Button) findViewById(R.id.history);
		workoutText = (TextView) findViewById(R.id.textView);
		y = com.c1.charityworkout.MainActivity.x;
		imgView = (ImageView) findViewById(R.id.imageView2);
		Drawable image2 = getResources().getDrawable(y);
		imgView.setImageDrawable(image2);
		thankyou.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent random = new Intent("android.c1.CharityWorkout.THANKYOU");
				startActivity(random);
			}
		});
		main = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		main.setOnTouchListener(this);
		startX = 0;
		endX = 0;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			endX = event.getX();
		}

		if (startX != 0 && endX != 0) {
			if (endX - startX > 120) {
				workoutText.setText("rechts");
			}
			if (endX - startX < -120) {
				workoutText.setText("links");
			}
		}
		return true;
	}

}
