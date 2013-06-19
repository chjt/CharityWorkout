package com.c1.charityworkout;

import com.c1.charityworkout.R;
import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Screen3 extends Activity implements OnClickListener,
		OnTouchListener {

	// Variables for Swipe Gesture
	float startX, endX;
	GestureOverlayView main;

	// Variables for Timer
	long currentTime = 0, newTime = 0, pauseTime = 0, secondsCalc = 0;
	int minTimer = 0;
	Button bStart, bStop;
	String seconds = "00", minutes = "00", pauseMessage, stopWarningMsg,
			stopMessage, timerText;
	Thread timer;
	static Boolean startW = false;
	TextView timerView, distanceView, amountView;
	Bundle data;

	// Variables unsorted
	ImageView imgView;
	private int y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_3);
		rendering();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		while (secondsCalc / 60 > 1) {
			minTimer++;
		}

	}

	private void startTimer() {
		timer = new Thread() {
			public void run() {
				currentTime = System.currentTimeMillis();
				while (startW == true) {
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						newTime = ((System.currentTimeMillis() - currentTime) / 1000)
								+ pauseTime;
						secondsCalc = newTime - (60 * minTimer);
						if (secondsCalc / 60 >= 1) {
							minTimer++;
							minutes = Integer.toString(minTimer);
							if (minutes.length() == 1) {
								minutes = "0" + minutes;
								secondsCalc = secondsCalc - 60;
							}
						}
						seconds = Long.toString(secondsCalc);
						if (seconds.length() == 1) {
							seconds = "0" + seconds;
						}
						timerView.post(new Runnable() {
							public void run() {
								if (startW == true) {
									timerText = minutes + ":" + seconds;
									timerView.setText(timerText);
								}
							}
						});
						distanceView.post(new Runnable() {
							public void run() {
								String totalDistance = GoogleMapFragment.totalDistance;
								
								if (totalDistance != null) {
									totalDistance = totalDistance.substring(0,3);
									distanceView.setText(totalDistance + "  meters");
								} else {
									distanceView.setText("0 meters");								
								}
							}
						});
					}
				}
			}
		};
		timer.start();
	}

	private void rendering() {
		// TODO Auto-generated method stub
		timerView = (TextView) findViewById(R.id.timerView);
		distanceView = (TextView) findViewById(R.id.distanceView);
		amountView = (TextView) findViewById(R.id.amountView);
		y = com.c1.charityworkout.MainActivity.x;
		imgView = (ImageView) findViewById(R.id.imageView2);
		bStart = (Button) findViewById(R.id.start);
		bStart.setOnClickListener(this);
		bStop = (Button) findViewById(R.id.stop);
		bStop.setOnClickListener(this);
		Drawable image2 = getResources().getDrawable(y);
		imgView.setImageDrawable(image2);
		main = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		main.setOnTouchListener(this);
		pauseMessage = getResources().getString(R.string.pauseWorkout);
		stopWarningMsg = getResources().getString(R.string.stopWarning);
		stopMessage = getResources().getString(R.string.stopWorkout);
		startX = 0;
		endX = 0;

		timerText = minutes + ":" + seconds;
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

			}
			if (endX - startX < -120) {

			}
		}
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.start:
			if (GoogleMapFragment.gpsReady == true) {
				if (startW != true) {
					startW = true;
					GoogleMapFragment.locTrack = true;
					startTimer();
					timerView.setText(timerText);
					Toast.makeText(this, "Workout started", 1000).show();
				}
			} else {
				Toast.makeText(this, "Please wait for GPS Fix", 2000).show();
			}
			break;
		case R.id.stop:
			if (startW != false) {
				startW = false;
				GoogleMapFragment.locTrack = false;
				pauseTime = newTime;
				Toast.makeText(Screen3.this, stopWarningMsg, 2000).show();
				timerView.setText(timerText + " [" + pauseMessage + "]");
			} else {
				pauseTime = 0;
				timerView.setText(timerText + " [" + stopMessage + "]");
				timerText = "00:00";
				String time = timerView.getText().toString();
				String distance = distanceView.getText().toString();
				String amount = amountView.getText().toString();
				data = new Bundle();
				data.putString("timer", time);
				data.putString("distance", distance);
				data.putString("amount", amount);	
			}
			break;
		}
	}

}
