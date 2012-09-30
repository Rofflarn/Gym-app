package com.Grupp01.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class MainActivity extends SherlockActivity {
	
	//private ImageButton button1 = (ImageButton) findViewById(R.id.button1);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.activity_main, menu);
    	
        return true;
    }
    
    public void workout(View view)
    {
    	Intent workout = new Intent(this, Workout.class);
    	startActivity(workout);
    }
    
    public void historik(View view)
    {
    	Intent historik = new Intent(this, Historik.class);
    	startActivity(historik);
    }
    
    public void statistik(View view)
    {
    	Intent statistik = new Intent(this, Statistik.class);
    	startActivity(statistik);
    }
    
    public void ovningar(View view)
    {
    	Intent ovningar = new Intent(this, Ovningar.class);
    	startActivity(ovningar);
    }
}
