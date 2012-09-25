package com.Grupp01.gymapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    
    public void text(View view)
    {
    	TextView tv = (TextView)findViewById(R.id.visa);
    	tv.setText("det funkar");
    	Dialog dialog = new Dialog(this);
    	TextView t = new TextView(this);
    	t.setText("hej");
    	dialog.setContentView(R.layout.dialog);
    	dialog.setTitle("Exercise");
    	dialog.show();
    }
}