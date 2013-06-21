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
	TextView speedResult, amountView, distanceView, timerView, text;
	String speed, amount, distance, timer, stringSave;

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
		speed = gotResult.getString("speed");
		amount = gotResult.getString("amount");
		distance = gotResult.getString("distance");
		timer = gotResult.getString("timer");
		speedResult.setText(speed);
		amountView.setText(amount);
		distanceView.setText(distance);
		timerView.setText(timer);

	}

	private void initialize() {
		// TODO Auto-generated method stub
		speedResult = (TextView) findViewById(R.id.speedResultView);
		amountView = (TextView) findViewById(R.id.amountResultView);
		distanceView = (TextView) findViewById(R.id.distanceResultView);
		timerView = (TextView) findViewById(R.id.timerResultView);
		save.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.save:
			stringstoSave();
			stringSave = text.getText().toString();
			writeToFile(stringSave);
			break;
		case R.id.back:
			Intent back = new Intent(ResultPage.this, Screen3.class);
			startActivity(back);
			finish();
		}
	}

	private void writeToFile(String stringSave2) {
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
		text.setText("Speed: " + speed + "Distance: " + distance + "Timer: "
				+ timer + "Amount: " + amount);
	}

}
