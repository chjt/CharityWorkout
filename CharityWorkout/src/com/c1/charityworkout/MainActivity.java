package com.c1.charityworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    ImageButton running, cycling, skateboard;
    static int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        running = (ImageButton) findViewById(R.id.imageButton);
        cycling = (ImageButton) findViewById(R.id.imageButton2);
        skateboard = (ImageButton) findViewById(R.id.imageButton3);


        running.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    Intent next = new Intent("android.c1.CharityWorkout.SCREEN2");
                    startActivity(next);
                    x = (R.drawable.runningbanner);
            }
        }
        );
        cycling.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    Intent next = new Intent("android.c1.CharityWorkout.SCREEN2");
                    startActivity(next);
                    x = (R.drawable.cyclingbanner);
            }
        }
        );
        skateboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    Intent next = new Intent("android.c1.CharityWorkout.SCREEN2");
                    startActivity(next);
                    x = (R.drawable.skateboardingbanner);
            }
        }
        );


    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
