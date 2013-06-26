package com.c1.charityworkout;

import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultPage extends Activity implements OnClickListener {

	// Variables TextViews
	TextView speedResult, amountResult, distanceResult, timerResult,
			workoutView;
	String speed, amount, distance, timer, workout, stringSave, speedText,
			amountText, distanceText, workoutText, timerText;

	// Variables SaveButtons
	Button save, back;

	private static final String FILENAME = "saveFile.txt";
	private static final String TAG = Screen3.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultpage);
		initialize();
		getBundle();
	}

	protected void getBundle() {
		// TODO Auto-generated method stub
		Bundle gotResult = getIntent().getExtras();
		workout = gotResult.getString("workout");
		speed = gotResult.getString("speed");
		amount = gotResult.getString("amount");
		distance = gotResult.getString("distance");
		timer = gotResult.getString("timer");
		workoutView.setText(workout + "\r\n");
		speedResult.append(": " + speed + "\r\n");
		amountResult.append(": " + amount + "\r\n");
		distanceResult.append(": " + distance + "\r\n");
		timerResult.append(": " + timer + "\r\n");

	}

	private void initialize() {
		// TODO Auto-generated method stub
		workoutView = (TextView) findViewById(R.id.workout);
		speedResult = (TextView) findViewById(R.id.speedResult);
		amountResult = (TextView) findViewById(R.id.amountResult);
		distanceResult = (TextView) findViewById(R.id.distanceResult);
		timerResult = (TextView) findViewById(R.id.timerResult);
		save.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.save:
			stringstoSave();
			writeToFile();
			break;
		case R.id.back:
			Intent back = new Intent(ResultPage.this, Screen3.class);
			startActivity(back);
			finish();
		}
	}

	private void writeToFile() {
		// TODO Auto-generated method stub
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					openFileOutput(FILENAME, Context.MODE_APPEND));
			outputStreamWriter.write(stringSave);
			outputStreamWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "File write failed: " + e.toString());
		}
	}

	private void stringstoSave() {
		// TODO Auto-generated method stub
		workoutText = workoutView.getText().toString();
		speedText = speedResult.getText().toString();
		distanceText = distanceResult.getText().toString();
		timerText = timerResult.getText().toString();
		amountText = amountResult.getText().toString();
		stringSave = workoutText + speedText + distanceText + timerText
				+ amountText ;
	}

}
