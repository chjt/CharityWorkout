package com.c1.charityworkout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MapLocationListener extends Screen3 implements LocationListener {
	Context newContext;
	String gpsDis;
	String gpsEna;
	
	public MapLocationListener(Context myContext) {
		newContext = myContext;
		gpsDis = newContext.getResources().getString(R.string.gpsDis);
		gpsEna = newContext.getResources().getString(R.string.gpsEna);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(newContext, gpsDis, 3000).show();		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		Toast.makeText(newContext, gpsEna, 3000).show();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
