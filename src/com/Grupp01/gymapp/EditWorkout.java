package com.Grupp01.gymapp;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class EditWorkout extends SherlockActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getStringExtra(ListWorkoutActivity.WORKOUT_NAME);
        
        setContentView(R.layout.editworkout);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.activity_main, menu);
        return true;
    }
}