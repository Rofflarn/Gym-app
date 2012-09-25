package com.Grupp01.gymapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class Ovningar extends SherlockActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ovningar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.ovningar, menu);
        return true;
    }
    
    public void newExercise(View view)
    {
    	Dialog dialog = new Dialog(this);
    	dialog.setContentView(R.layout.dialog);
    	dialog.setTitle("New Exercise");
    	dialog.show();
    }
}