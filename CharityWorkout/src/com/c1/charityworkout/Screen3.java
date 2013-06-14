package com.c1.charityworkout;


import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Screen3 extends FragmentActivity implements OnTouchListener, LocationListener  {
	Button thankyou, startbutton;
	float startX, endX;
	GestureOverlayView main;
	ImageView imgView;
	private int y;
	public static long tijd;
	TextView workoutText;
	
	private LocationManager locationManager;
	private String provider;

	// the map
	private GoogleMap theMap;
	ArrayList<LatLng> pointList = new ArrayList<LatLng>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	    Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			Toast.makeText(
					this,
					"Last known location is available with selected Provider "
							+ provider, Toast.LENGTH_SHORT).show();
			onLocationChanged(location);
		} else {

			// do something
			Toast.makeText(this, "Location not available ", Toast.LENGTH_SHORT)
					.show();
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
		super.onResume();
		locationManager.requestLocationUpdates(provider, 30000, 30, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		LatLng coordinate = new LatLng(lat, lng);
		Toast.makeText(this,
				"Location " + coordinate.latitude + "," + coordinate.longitude,
				Toast.LENGTH_SHORT).show();

	// Instantiating the class MarkerOptions to plot marker on the map
    MarkerOptions markerOptions = new MarkerOptions();

    // Setting latitude and longitude of the marker position
    markerOptions.position(coordinate);

    // Setting title of the infowindow of the marker
    markerOptions.title("Position");
    
    // Setting icon of the marker
    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

    // Setting the content of the infowindow of the marker
    markerOptions.snippet("Latitude:"+coordinate.latitude+","+"Longitude:"+coordinate.longitude);

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
		Toast.makeText(this, "Provider status changed " + provider,
				Toast.LENGTH_SHORT).show();

	}




	private void rendering() {
		// TODO Auto-generated method stub
		thankyou = (Button) findViewById(R.id.thankyou);
		// history = (Button) findViewById(R.id.history);
		workoutText = (TextView) findViewById(R.id.textView);
		y = com.c1.charityworkout.MainActivity.x;
		imgView = (ImageView) findViewById(R.id.imageView2);
		Drawable image2 = getResources().getDrawable(y);
		imgView.setImageDrawable(image2);
		thankyou.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent random = new Intent("android.c1.CharityWorkout.THANKYOU");
				startActivity(random);
			}
		});
		main = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		main.setOnTouchListener(this);
		startX = 0;
		endX = 0;
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
				workoutText.setText("rechts");
			}
			if (endX - startX < -120) {
				workoutText.setText("links");
			}
		}
		return true;
	}
}


			

