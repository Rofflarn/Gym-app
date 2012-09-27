package com.Grupp01.gymapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

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
    	com.actionbarsherlock.view.MenuItem item = menu.add("New exercise");
    	item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    	final Dialog dialog = new Dialog(this);
    	final Intent intent = new Intent(this, AddExercise.class);
    	item.setOnMenuItemClickListener(new OnMenuItemClickListener()
    	{

			@Override
			public boolean onMenuItemClick(com.actionbarsherlock.view.MenuItem item)
			{
		    	dialog.setContentView(R.layout.dialog);
		    	dialog.setTitle("New Exercise");
		    	Button button = (Button) dialog.findViewById(R.id.button);
		    	button.setOnClickListener(
		    			new OnClickListener()
		    			{
		    				public void onClick(View view)
		    				{
		    					startActivity(intent);
		    				}
		    			});
		    	dialog.show();
				return false;
			}
    		
    	});
        return true;
    }
    
//    public void newExercise(View view)
//    {
//    	final Dialog dialog = new Dialog(this);
//    	dialog.setContentView(R.layout.dialog);
//    	dialog.setTitle("New Exercise");
//    	
//    	Button button = (Button) dialog.findViewById(R.id.button);
//    	button.setOnClickListener(
//    			new OnClickListener()
//    			{
//    				public void onClick(View view)
//    				{
//    					TextView tv = (TextView) findViewById(R.id.test);
//    					EditText et = (EditText) dialog.findViewById(R.id.exerciseName);
//    					tv.setText(et.getText().toString());
//    					dialog.dismiss();
//    				}
//    			});
//    	dialog.show();
//    }
}