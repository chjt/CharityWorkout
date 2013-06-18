package com.c1.charityworkout;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class GoogleMapFragment extends MapFragment implements LocationListener,
		Listener {

	GoogleMap workoutMap;
	PolylineOptions lineOptions;
	Polyline lineRoute;
	Location myLocation, prevLocation;
	Context myContext;
	float[] result = new float[5];
	float countedResult;
	LocationManager locationManager;
	static String totalDistance;
	static boolean gpsReady = false;
	static boolean locTrack = false;

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
		initializeDraw();
		initializeLocation();
		if (myLocation != null) {
			setInitialLocation();
		}
	}

	private void initializeLocation() {
		// TODO Auto-generated method stub

		locationManager = (LocationManager) myContext
				.getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				5000, 0, this);
		locationManager.addGpsStatusListener(this);
		myLocation = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	}

	private void initializeMap() {
		// TODO Auto-generated method stub
		workoutMap.setMyLocationEnabled(true);
		workoutMap.getUiSettings().setZoomControlsEnabled(false);
		workoutMap.getUiSettings().setMyLocationButtonEnabled(false);
		workoutMap.getUiSettings().setZoomGesturesEnabled(false);
		workoutMap.getUiSettings().setScrollGesturesEnabled(false);
		workoutMap.getUiSettings().setTiltGesturesEnabled(false);
		workoutMap.getUiSettings().setRotateGesturesEnabled(false);

	}

	private void initializeDraw() {
		lineOptions = new PolylineOptions().width(5).color(Color.RED);
		lineRoute = workoutMap.addPolyline(lineOptions);
	}

	private void setInitialLocation() {
		// TODO Auto-generated method stub
		CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(
				myLocation.getLatitude(), myLocation.getLongitude()));
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);

		workoutMap.moveCamera(center);
		workoutMap.animateCamera(zoom);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		locationManager.removeUpdates(this);
		super.onDestroy();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if (locTrack == true) {
			prevLocation = myLocation;
			myLocation = location;
			workoutMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(
					myLocation.getLatitude(), myLocation.getLongitude())));
			drawTrail();
			calcDistance();
		}

	}

	private void calcDistance() {
		// TODO Auto-generated method stub
		Location.distanceBetween(prevLocation.getLatitude(),
				prevLocation.getLongitude(), myLocation.getLatitude(),
				myLocation.getLongitude(), result);
		countedResult = countedResult + result[0];
		totalDistance = Float.toString(countedResult / 1000);
		Toast.makeText(myContext, totalDistance, 1000).show();
	}

	private void drawTrail() {
		// TODO Auto-generated method stub
		lineRoute.getPoints();
		LatLng newPoint = new LatLng(myLocation.getLatitude(),
				myLocation.getLongitude());
		List<LatLng> points = lineRoute.getPoints();
		points.add(newPoint);
		lineRoute.setPoints(points);
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

	@Override
	public void onGpsStatusChanged(int arg0) {
		// TODO Auto-generated method stub
		if (arg0 == GpsStatus.GPS_EVENT_FIRST_FIX) {
			gpsReady = true;
			Toast.makeText(myContext,
					"Location fixed. You can now start your workout.", 3000)
					.show();
		} else if (arg0 == GpsStatus.GPS_EVENT_STARTED) {
			Toast.makeText(myContext, "Looking for current location", 3000)
					.show();
		} else if (arg0 == GpsStatus.GPS_EVENT_STOPPED) {
			Toast.makeText(myContext, "Please re-enable your gps.", 3000)
					.show();
			gpsReady = false;
		}

	}
}
