package com.c1.charityworkout;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WorkoutHistory extends ListActivity {
	String classes[] = {"haha", "ff proberen"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		
		setListAdapter(new ArrayAdapter<String>(WorkoutHistory.this,
				android.R.layout.simple_list_item_1, classes));		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String Workout = classes[position];
		
	}
	
	

}
