package com.c1.charityworkout;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Screen3 extends Activity implements OnClickListener,
		OnTouchListener, Runnable {
	Button bStart, bStop;
	float startX, endX;
	GestureOverlayView main;
	ImageView imgView;
	private int y;
	long currentTime = 0;
	long newTime = 0;
	String counter;
	Thread timer;
	Boolean startW = false;
	public static long tijd;
	TextView workoutText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_3);

		rendering();
	}

	private void startTimer() {

		currentTime = System.currentTimeMillis();
		timer = new Thread() {
			public void run() {
				while (startW == true) {
					newTime = (System.currentTimeMillis() - currentTime) / 1000;
					counter = Long.toString(newTime);
					workoutText.post(new Runnable() {
						public void run() {
							workoutText.setText(counter + " sec.");
						}
					});
				}
			}
		};
		timer.start();
	}

	private void rendering() {
		// TODO Auto-generated method stub
		workoutText = (TextView) findViewById(R.id.textView);
		y = com.c1.charityworkout.MainActivity.x;
		imgView = (ImageView) findViewById(R.id.imageView2);
		bStart = (Button) findViewById(R.id.thankyou);
		bStart.setOnClickListener(this);
		bStop = (Button) findViewById(R.id.history);
		bStop.setOnClickListener(this);
		Drawable image2 = getResources().getDrawable(y);
		imgView.setImageDrawable(image2);
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

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.thankyou:
			if (startW != true) {
				startW = true;
				startTimer();
			}
			break;
		case R.id.history:
			if (startW != false) {
				startW = false;
			}
			break;
		}
	}

}
