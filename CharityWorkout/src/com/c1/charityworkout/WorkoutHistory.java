package com.c1.charityworkout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutHistory extends Activity implements OnClickListener {
	private String ret;
	private TextView tvHistory;
	private static final String TAG = WorkoutPage.class.getName();
	private static final String FILENAME = "history.txt";
	private Button donate, delete;
	private int amountOfWorkouts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		tvHistory = (TextView) findViewById(R.id.tvHistory);
		tvHistory.setSingleLine(false);
		initialize();
		tvHistory.setText(readFromFile());
		if (amountOfWorkouts==0) {
			tvHistory.setText("No workout history found.");
		}
	}

	private void initialize() {
		// TODO Auto-generated method stub
		donate = (Button) findViewById(R.id.donate);
		delete = (Button) findViewById(R.id.delete);
		donate.setOnClickListener(this);
		delete.setOnClickListener(this);
		amountOfWorkouts=0;
	}

	private String readFromFile() {
		// TODO Auto-generated method stub
		ret = "";
		try {
			InputStream inputStream = openFileInput(FILENAME);
			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();
				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString + "\n");
					amountOfWorkouts++;
				}
				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e(TAG, "Can not read file: " + e.toString());
		}
		return ret;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.donate:
			break;
		case R.id.delete:
			File file = new File(getFilesDir(), "history.txt");
			file.delete();
			finish();
			break;
		}
	}
}
