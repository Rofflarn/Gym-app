package com.Grupp01.gymapp;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


public class RegisterDynamicActivity extends SherlockActivity {

	private String workoutName;				//The name of the workout
	private ArrayList<String> currentSets;	//The array where new sets is added (in form of REPSxWEIGHT)
	
	/**
	 * Set up the default layout and call initiate method that is required. 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutName = getIntent().getStringExtra("exercisename");
        setContentView(R.layout.activity_register_dynamic);
        
        //Create the array
        currentSets = new ArrayList<String>();
        
        //Show the sets (reps and weight) for the last time this
        //exercise was performed.
        setLastSetString();
     }
    
    
    /**
     * Set up the actionbar (layout xml and title)
     * @param Menu the actionbar menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_register_dynamic, menu);
        getSupportActionBar().setTitle(workoutName);
        getSupportActionBar().setHomeButtonEnabled(true);
        return true;
    }
    
    
    /**
     * Set up actions for buttons in actionbar
     * @param MenuItem item - The menuitem thas has been pressed
     * 
     */
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    		
    	//Make app icon navigate back to the applications start screen.
    		case	android.R.id.home:
    			Intent intent = new Intent(this, MainActivity.class);
    			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    			return true;
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
    
    
    
    /**
     * This method will set the TextView in the layout
     * that shows the time and weight for when performing this exercise last time.
     */
    private void setLastSetString() {
    	TextView lastSetString = (TextView) findViewById(R.id.lastTimeSets);
    	lastSetString.setText("ska hämtas från DB");
		
	}
    
    
  /**
     * Method called when pressing any of the buttons in the view
     * @param View v - The view thas has been clicked
     */
    
    public void onClick(View v){
    	switch(v.getId()){
    	
    	//Cancel button pressed, exit without saving
    	case R.id.dynamicButtonCancel:
    		finish();
    		break;
    		
    	//OK button pressed, exit and save to database
    	case R.id.dynamicButtonOK:
    		saveSetsToDatabase();
    		finish();
    		break;
    		
    	//Finish set pressed, add the reps and weight to the array
    	case R.id.dynamicFinishSet:
    		addNewSet();
    		break;
    		
    	//Undo set pressed, remove the latest set from the array.
    	case R.id.dynamicUndoSet:
    		removeLatestSet();
    		break;
    	}
    }

    /**
     * THis is called when pressing the "OK" button, will save the sets
     * to the database before this activity terminates.
     */
    private void saveSetsToDatabase() {
    	Toast.makeText(this, "will close this activity and save", Toast.LENGTH_SHORT).show();
		
	}



	/**
     * Will read the input of reps and weight EditTexts, add it to the 
     * array and update the view
     * 
     */
    private void addNewSet() {
    	//Input from the user, the reps and weight
    	EditText editReps = (EditText) findViewById(R.id.editReps);
		EditText editWeight = (EditText) findViewById(R.id.editWeight);
    	
    	//Fetch number of reps
		String reps = editReps.getText().toString();
		
		//Do not add set where the number of repetitions is 0 or blank ("")
		if(!(reps.equals("")) && !(reps.equals("0"))){
			
			//If the weight is blank, set it to zero
			String weight = editWeight.getText().toString();
			if(weight.equals(""))
				weight = "0";
			
			//add to the array and update view on the screen
			currentSets.add(reps + "x" + weight);
			updateView();
		
		}
		else
			Toast.makeText(this, "Cant add set with 0 repetitions.", Toast.LENGTH_SHORT).show();
		
	}
		
	/**
	 * This method will "translate" the items in the ArrayList to a string and separate each 
	 * item with a "," in the resulting string.
	 * It will then call the method "setCurrentSetsView" with the resulting string.
	 * 
	 */
	private void updateView() {
		String string = new String();
		
		//The textview where current sets will be showed (added when button "Finish set" is pressed)
		TextView currentSetString = (TextView) findViewById(R.id.thisTimeSets);
		
		//The prefix is used to separate each set in the string.
		String prefix = new String("");
		
		for (String s : currentSets)
		{
			string = string + prefix;
			prefix = ", ";
			string = string + s;
		}
		currentSetString.setText(string);
	}
	
	
	/**
	 * Will remove the last set from the array and then update the view.
	 * 
	 */
	private void removeLatestSet() {
		if(currentSets.size() > 0)
			currentSets.remove(currentSets.size() -1);
		updateView();
	}
}

