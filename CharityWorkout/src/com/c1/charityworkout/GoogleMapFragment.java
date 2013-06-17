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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class GoogleMapFragment extends MapFragment {

	GoogleMap workoutMap;
	Location myLocation;
	Context myContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		workoutMap = getMap();
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		myContext = getActivity();
		initializeMap();
		myLocation = getLocation();
		if (myLocation != null) {
			setLocation();
		}
	}

	private void setLocation() {
		// TODO Auto-generated method stub
		CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(
				myLocation.getLatitude(), myLocation.getLongitude()));
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

		workoutMap.moveCamera(center);
		workoutMap.animateCamera(zoom);
		
		Toast.makeText(myContext, "CAMERA UPDATED LOCATION IS" + myLocation,
				10000).show();
		
		myLocation = null;

	}

	private void initializeMap() {
		// TODO Auto-generated method stub
		workoutMap.setMyLocationEnabled(true);
		workoutMap.getUiSettings().setZoomControlsEnabled(true);
		workoutMap.getUiSettings().setMyLocationButtonEnabled(false);
		workoutMap.getUiSettings().setZoomGesturesEnabled(false);
		workoutMap.getUiSettings().setScrollGesturesEnabled(true);

	}

	private Location getLocation() {

		LocationManager locationManager = (LocationManager) myContext
				.getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new MapLocationListener(myContext);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				5000, 5, locationListener);
		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		return location;

	}
}
