package com.c1.charityworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Screen2 extends Activity {

	Button donate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_2);

		donate = (Button) findViewById(R.id.Submit);

		donate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent submit = new Intent(Screen2.this, Screen3.class);
				startActivity(submit);
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
	
}
