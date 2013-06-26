package com.c1.charityworkout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WorkoutHistory extends Activity {
	String classes[] = { "haha", "ff proberen" };
	String ret;
	private static final String TAG = Screen3.class.getName();
	private static final String FILENAME = "saveFile.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		readFromFile();
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
				StringBuilder stringBuiler = new StringBuilder();
				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuiler.append(receiveString);
				}
				inputStream.close();
				ret = stringBuiler.toString();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e(TAG, "Can not read file: " + e.toString());
		}
		return ret;
	}

}
