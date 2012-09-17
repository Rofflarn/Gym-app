package com.Grupp01.gymapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void workout(View view)
    {
    	Intent workout = new Intent(this, Workout.class);
    	startActivity(workout);
    }
    
    public void historik(View view)
    {
    }
    
    public void statistik(View view)
    {
    }
    
    public void ovningar(View view)
    {
    }
}
