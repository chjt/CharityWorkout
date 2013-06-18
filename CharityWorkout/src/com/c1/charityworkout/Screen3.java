package com.c1.charityworkout;

import java.util.ArrayList;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Screen3 extends FragmentActivity implements OnTouchListener, OnClickListener, LocationListener  {

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
	Boolean startW = false;
	TextView workoutText;
	
	// the map
	GoogleMap theMap;
	ArrayList<LatLng> pointList = new ArrayList<LatLng>();
	ArrayList<Location> coordinates = new ArrayList<Location>();
	LocationManager locationManager;
	String provider;
	float distance;

	// Variables unsorted
	ImageView imgView;
	private int y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_3);
		rendering();
		theMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.the_map)).getMap();
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabledGPS = service
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabledGPS) {
			// Show gps settings...
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);

			// set title
			alertDialogBuilder.setTitle("Turn On Your GPS!");

			// set dialog message
			alertDialogBuilder
					.setMessage("Please turn on your GPS!")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent intent = new Intent(
											Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									startActivity(intent);
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// if this button is clicked, just close
									// the dialog box and do nothing
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		}

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the location provider -> use
		// default
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		provider = locationManager.getBestProvider(criteria, true);


	// Restoring the markers on configuration changes
    if(savedInstanceState!=null){
        if(savedInstanceState.containsKey("markers")){
            pointList = savedInstanceState.getParcelableArrayList("markers");
            if(pointList!=null){
                for(int i=0;i<pointList.size();i++){
                	drawMarker(pointList.get(i));
                }
            }
        }
    }
}
	// A callback method, which is invoked on configuration is changed
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Adding the pointList arraylist to Bundle
        outState.putParcelableArrayList("markers", pointList);
 
        // Saving the bundle
        super.onSaveInstanceState(outState);
    }


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		while (secondsCalc / 60 > 1) {
			minTimer++;
		}
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		 
        // Showing status
        if(status==ConnectionResult.SUCCESS)
        {}
        else{
        	Toast.makeText(this, "Google Play Services are not available",
					Toast.LENGTH_SHORT).show();
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }	
        if (startW = true); {
        locationManager.requestLocationUpdates(provider, 30000, 30, this);
        }
    }
	
	@Override
	public void onLocationChanged(Location location) {
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		coordinates.add(location);
		LatLng coordinate = new LatLng(lat, lng);
		drawMarker(coordinate);
	}
	
	public void calculateonstop() {
		for (int n = 0; n < coordinates.size()-1;n++){
		distance += coordinates.get(n).distanceTo(coordinates.get(n + 1));
		}
		System.out.println(distance);
		Toast.makeText(this, "The route was " +distance +" metres",
				Toast.LENGTH_SHORT).show();
	}
	
	private void drawMarker(LatLng coordinate){
		// Instantiating the class MarkerOptions to plot marker on the map
	    MarkerOptions markerOptions = new MarkerOptions();

	    // Setting latitude and longitude of the marker position
	    markerOptions.position(coordinate);

	    // Setting title of the infowindow of the marker
	    markerOptions.title("Position");
	    
	    // Setting icon of the marker
	    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

	    // Instantiating the class PolylineOptions to plot polyline in the map
	    PolylineOptions polylineOptions = new PolylineOptions();

	    // Setting the color of the polyline
	    polylineOptions.color(Color.RED);

	    // Setting the width of the polyline
	    polylineOptions.width(7);

	    // Adding the taped point to the ArrayList
	    pointList.add(coordinate);

	    // Setting points of polyline
	    polylineOptions.addAll(pointList);

	    // Adding the polyline to the map
	    theMap.addPolyline(polylineOptions);

	    // Adding the marker to the map
	    theMap.addMarker(markerOptions);
	    
	    theMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 16));

	}

		@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText(this, "Provider disabled " + provider,
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onProviderEnabled(String provider) {
			Toast.makeText(this, "Provider enabled " + provider, Toast.LENGTH_SHORT)
					.show();

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			/* This is called when the GPS status alters */
			switch (status) {
			case LocationProvider.OUT_OF_SERVICE:
				Toast.makeText(this, "Status Changed: Out of Service",
						Toast.LENGTH_SHORT).show();
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				Toast.makeText(this, "Status Changed: Temporarily Unavailable",
						Toast.LENGTH_SHORT).show();
				break;
			case LocationProvider.AVAILABLE:
				Toast.makeText(this, "Status Changed: Available",
						Toast.LENGTH_SHORT).show();
				break;
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
						workoutText.post(new Runnable() {
							public void run() {
								if (startW == true) {
									timerText = minutes + ":" + seconds;
									workoutText.setText(timerText);
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
		workoutText = (TextView) findViewById(R.id.textView);
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
			if (startW != true) {
				startW = true;
				startTimer();
				workoutText.setText(timerText);
				locationManager.requestLocationUpdates(provider, 30000, 30, this);
			}
			break;
		case R.id.stop:
			if (startW != false) {
				startW = false;
				pauseTime = newTime;
				Toast.makeText(Screen3.this, stopWarningMsg, Toast.LENGTH_SHORT).show();
				workoutText.setText(timerText + " [" + pauseMessage + "]");
				locationManager.removeUpdates(this);
			} else {
				pauseTime = 0;
				workoutText.setText(timerText + " [" + stopMessage + "]");
				timerText = "00:00";
				locationManager.removeUpdates(this);
				if (coordinates != null && coordinates.size() > 2) {
					calculateonstop();
			}
			break;
		}
	}
	}
}
