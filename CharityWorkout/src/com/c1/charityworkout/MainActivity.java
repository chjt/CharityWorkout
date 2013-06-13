package com.c1.charityworkout;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	ImageButton running, cycling, skateboard;
	static int x;
	String prefLocale;
	Locale locale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setLocale();
		setContentView(R.layout.activity_main);
		initialize();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void setLocale() {
		SharedPreferences getLocale = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		Configuration config = getBaseContext().getResources()
				.getConfiguration();
		prefLocale = getLocale.getString("prefLang", "en");
		locale = new Locale(prefLocale);
		Locale.setDefault(locale);
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());

	}

	private void initialize() {
		// TODO Auto-generated method stub
		running = (ImageButton) findViewById(R.id.imageButton);
		cycling = (ImageButton) findViewById(R.id.imageButton2);
		skateboard = (ImageButton) findViewById(R.id.imageButton3);

		running.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent next = new Intent("android.c1.CharityWorkout.SCREEN2");
				startActivity(next);
				x = (R.drawable.runningbanner);
			}
		});
		cycling.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent next = new Intent("android.c1.CharityWorkout.SCREEN2");
				startActivity(next);
				x = (R.drawable.cyclingbanner);
			}
		});
		skateboard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent next = new Intent("android.c1.CharityWorkout.SCREEN2");
				startActivity(next);
				x = (R.drawable.skateboardingbanner);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent p = new Intent("android.c1.charityworkout.PREFS");
			startActivity(p);
			break;
		case R.id.exit_app:
			finish();
			break;

		}

		return false;
	}

}
