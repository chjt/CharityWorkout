package com.c1.charityworkout;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Window;

public class Prefs extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}

	
}
