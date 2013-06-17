package com.c1.charityworkout;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class GoogleMapFragment extends MapFragment {
	
	GoogleMap workoutMap;
	Location myLocation;
	Context myContext;
	
	public GoogleMapFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		workoutMap = getMap();
		return view;
	}

	//@Override
	//public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onViewCreated(view, savedInstanceState);
		//myContext = getActivity();
		//initializeMap();
		//myLocation = getLocation();
		//if (myLocation != null) {
		//	setLocation(myLocation);
		//}
	//}

	private void setLocation(Location location) {
		// TODO Auto-generated method stub
		workoutMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()), 15.0f));
		myLocation = null;
		Toast.makeText(myContext, "CAMERA UPDATED", 2000);
		
	}

	private void initializeMap() {
		// TODO Auto-generated method stub
		workoutMap.setMyLocationEnabled(true);
		workoutMap.getUiSettings().setZoomControlsEnabled(false);
		workoutMap.getUiSettings().setMyLocationButtonEnabled(false);
		workoutMap.getUiSettings().setZoomGesturesEnabled(false);
		workoutMap.getUiSettings().setScrollGesturesEnabled(false);
		
	}
	
	private Location getLocation() {
		
		LocationManager locationManager = (LocationManager) myContext.getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new MapLocationListener(myContext);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,locationListener);
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);		
		return location;
		
	}
	
	

}
