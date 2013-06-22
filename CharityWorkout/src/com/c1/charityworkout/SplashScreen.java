package com.c1.charityworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
				Thread waiting = new Thread() {
					public void run() {
						try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							Intent start = new Intent(SplashScreen.this, MainActivity.class);
							startActivity(start);
							finish();
						}
					}
				};
				waiting.start();		
	}
}
