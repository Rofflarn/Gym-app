package com.Grupp01.gymapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Ovningar extends SherlockActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ovningar);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.ovningar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intentMain = new Intent(this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentMain);
                return true;
            case R.id.menu_addExe:
            	Intent intentAddExe = new Intent(this, AddExercise.class);
                intentAddExe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentAddExe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void newExercise(View view)
    {
    	Dialog dialog = new Dialog(this);
    	dialog.setContentView(R.layout.dialog);
    	dialog.setTitle("New Exercise");
    	dialog.show();
    }
}