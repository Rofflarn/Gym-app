package com.Grupp01.gymapp;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

public class Ovningar extends SherlockActivity implements OnMenuItemClickListener {
	
	public final static String EXTRA_EXERCISE_NAME = "com.Grupp01.gymapp.message";
	private Dialog dialog;
	private Intent intent;
	private com.actionbarsherlock.view.MenuItem newExButton;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ovningar);
        dialog = new Dialog(this);
        intent = new Intent(this, AddExercise.class);
        createListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.ovningar, menu);
    	newExButton = menu.add("New exercise");
    	newExButton.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    	newExButton.setOnMenuItemClickListener(this);
        return true;
    }
    
    public void createListView()
    {
    	ListView listView = (ListView)findViewById(R.id.theList);
    	ArrayList<String> listElements = new ArrayList<String>();
    	listElements.add("ett");
    	listElements.add("två");
    	listElements.add("tre");
    	listElements.add("fyra");
    	listElements.add("fem");
    	listElements.add("sex");
    	listElements.add("sju");
    	ArrayAdapter<String> element_adapter = new ArrayAdapter<String>(this, R.layout.thelist_row, listElements);
    	listView.setAdapter(element_adapter);
    }

	@Override
	public boolean onMenuItemClick(com.actionbarsherlock.view.MenuItem item) {
		
		//if(item.getActionView() == newExButton)
		//{

		    	dialog.setContentView(R.layout.dialog);
		    	dialog.setTitle("New Exercise");
		    	Button cancel_Button = (Button) dialog.findViewById(R.id.cancel_Button);
		    	Button add_Button = (Button) dialog.findViewById(R.id.add_Button);
		    	add_Button.setOnClickListener(
		    			new OnClickListener()
		    			{
		    				public void onClick(View view)
		    				{
		    					intent.putExtra(EXTRA_EXERCISE_NAME, ((EditText) dialog.findViewById(R.id.exerciseName)).getText().toString());
		    					dialog.dismiss();
		    					startActivity(intent);
		    				}
		    			});
		    	
		    	cancel_Button.setOnClickListener(
		    			new OnClickListener()
		    			{
							@Override
							public void onClick(View view)
							{
								dialog.dismiss();
							}
		    				
		    			});
		    	dialog.show();
				return false;
		//}
		//return false;
	}
}