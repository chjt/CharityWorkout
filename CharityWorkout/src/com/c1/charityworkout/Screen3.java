package com.c1.charityworkout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.MotionEventCompat;
//import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
//import android.support.v4.view.GestureDetectorCompat;
import android.widget.ImageView;
import android.location.LocationManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import android.location.Location;

public class Screen3 extends FragmentActivity implements LocationListener {
    Button thankyou, history;
    ImageView imgView;
    private int y;
  //the map
  	private GoogleMap theMap;

  	 LocationManager myLocationManager = null;
  	 OnLocationChangedListener myLocationListener = null;
   // private static final String DEBUG_TAG = "Gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_3);
        
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
      				  
      				  myLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
    

      		
        thankyou = (Button) findViewById(R.id.thankyou);
        history = (Button) findViewById(R.id.history);
        thankyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent random = new Intent("android.c1.CharityWorkout.THANKYOU");
                startActivity(random);
            }
        });
                
        y = com.c1.charityworkout.MainActivity.x;
        imgView =(ImageView) findViewById(R.id.imageView2);
        Drawable image2 = getResources().getDrawable(y);
        imgView.setImageDrawable(image2);
        
        }
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

                // Use dx and dy to determine the direction
        //        if(Math.abs(differenceInX) > Math.abs(differenceInY)) {
        //            if(differenceInX > 0) direction = "right";
        //            else direction = "left";
        //        } else {
        //            if(differenceInY > 0) direction = "down";
         //           else direction = "up";
         //       }
         //       break;
         //       }
    //  }
}