package com.Grupp01.gymapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
    
    public void newExercise(View view)
    {
    	final Dialog dialog = new Dialog(this);
    	dialog.setContentView(R.layout.dialog);
    	dialog.setTitle("New Exercise");
    	
    	Button button = (Button) dialog.findViewById(R.id.button);
    	button.setOnClickListener(
    			new OnClickListener()
    			{
    				public void onClick(View view)
    				{
    					TextView tv = (TextView) findViewById(R.id.test);
    					EditText et = (EditText) dialog.findViewById(R.id.exerciseName);
    					tv.setText(et.getText().toString());
    					dialog.dismiss();
    				}
    			});
    	dialog.show();
    }
}