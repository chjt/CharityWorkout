package com.c1.charityworkout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.MotionEventCompat;
//import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
//import android.support.v4.view.GestureDetectorCompat;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class Screen3 extends FragmentActivity implements LocationListener {
    Button thankyou, startbutton;
    ImageView imgView;
    private int y;
    private long tijd1, tijdpauze;
    public static long tijd;
    TextView WorkoutText;
  //the map
  	private GoogleMap theMap;

  	 LocationManager myLocationManager = null;
  	 OnLocationChangedListener myLocationListener = null;
    // private static final String DEBUG_TAG = "Gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_3);
        rendering();
        location();
        
    }


    private void location() {
		// TODO Auto-generated method stub
    	 //find out if we already have it
  		if(theMap==null){
  			//get the map
  			theMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.the_map)).getMap();
  				//choose map
  				//theMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
  				//theMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
  				 theMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
  				 //theMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
  				//set settings
  				  theMap.getUiSettings().setZoomControlsEnabled(true);
  				  theMap.getUiSettings().setCompassEnabled(true);
  				  theMap.getUiSettings().setMyLocationButtonEnabled(true);
  				  theMap.getUiSettings().setRotateGesturesEnabled(true);
  				  theMap.getUiSettings().setScrollGesturesEnabled(true);
  				  theMap.getUiSettings().setTiltGesturesEnabled(true);
  				  theMap.getUiSettings().setZoomGesturesEnabled(true);
  				  //or myMap.getUiSettings().setAllGesturesEnabled(true);
  				  //set traffic
  				  theMap.setTrafficEnabled(false);
  				  theMap.setMyLocationEnabled(true);
  				  
  				  myLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);}
	}


	public void timer(View view)
    {  startbutton = (Button) view;
       if (startbutton.getText().equals("Start"))
       {
           tijd1 = System.currentTimeMillis();
           startbutton.setText("Pauze");
           //Declare the timer
           Timer t = new Timer();
//Set the schedule function and rate
           t.scheduleAtFixedRate(new TimerTask() {

               @Override
               public void run() {
                   WorkoutText.setText(String.valueOf(System.currentTimeMillis()-tijd1));
               }

           },
//Set how long before to start calling the TimerTask (in milliseconds)
                   0,
//Set the amount of time between each execution (in milliseconds)
                   1000);
       }
       else if(startbutton.getText().equals("Pauze"))
       {
           tijdpauze = System.currentTimeMillis();
           startbutton.setText("Doorgaan");
       }
       else if(startbutton.getText().equals("Doorgaan"))
       {
           tijd1 += (System.currentTimeMillis()-tijdpauze);
           startbutton.setText("Pauze");
       }

    }

    public void timerstop(View view)
    {   thankyou = (Button) view;
    	tijd = System.currentTimeMillis()-tijd1;
        WorkoutText.setText(String.valueOf(tijd));

    }

    // @Override
    //    public boolean onTouchEvent (MotionEvent event){
    //  float x1, x2, y1, y2;
    //String direction;
    //    switch(event.getAction()) {
    //      case(MotionEvent.ACTION_DOWN):
    //        x1 = event.getX();
    //       y1 = event.getY();
    //      break;
    //   case(MotionEvent.ACTION_UP):
    //        x2 = event.getX();
    //        y2 = event.getY();
    //        float differenceInX = x2-x1;
    //        float differenceInY = y2-y1;

	private void rendering() {
		// TODO Auto-generated method stub
		thankyou = (Button) findViewById(R.id.thankyou);
        // history = (Button) findViewById(R.id.history);
		WorkoutText = (TextView) findViewById(R.id.textView);
		y = com.c1.charityworkout.MainActivity.x;
        imgView =(ImageView) findViewById(R.id.imageView2);
        Drawable image2 = getResources().getDrawable(y);
        imgView.setImageDrawable(image2);
        thankyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent random = new Intent("android.c1.CharityWorkout.THANKYOU");
                startActivity(random);
            }
        });
	}

	@Override
    public void onLocationChanged(Location location) {
    		  if (myLocationListener != null) {
    		   myLocationListener.onLocationChanged(location);
    		  
    		   LatLng latlng= new LatLng(location.getLatitude(), location.getLongitude());
    		   theMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
    		         
    		  }
    		 }

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
    }

	
