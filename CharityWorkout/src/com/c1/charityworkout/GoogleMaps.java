/*package com.c1.charityworkout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class GoogleMaps extends FragmentActivity implements LocationListener{
	//the map
  	private GoogleMap theMap;

  	 LocationManager myLocationManager = null;
  	 OnLocationChangedListener myLocationListener = null;


     @Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
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
  	 
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
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

}*/
