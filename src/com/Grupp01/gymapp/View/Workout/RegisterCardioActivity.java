/*This file is part of Gymapp.
*
*   Gymapp is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   Gymapp is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*  along with Gymapp.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package com.Grupp01.gymapp.View.Workout;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.Controller.Workout.ListWorkoutDbHandler;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 04/10/12
 *
 * Class RegisterCardioActivity is an activity that enables the user to
 * register his or hers result when performing a cardio exercise.
 * The user will be able to input time (minutes and seconds) and the distance. 
 * 
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p> 
 *
 */

public class RegisterCardioActivity extends SherlockActivity {
	private ExerciseData exercise; //The name of the workout
	private ArrayList<String> currentSets;	//The array where new sets is added (in form of REPSxWEIGHT)
	private int exerciseId;
	private int workoutId;
	
	/**
	 * Set up the default layout and call initiate method that is required. 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cardio);
        //Create the array
        currentSets = new ArrayList<String>();
        exerciseId = getIntent().getIntExtra(WorkoutActivity.EXTRA_EXERCISE_ID, 0);
        workoutId = getIntent().getIntExtra(WorkoutActivity.EXTRA_WORKOUT_ID, 0);
        getExerciseData();
        setTitle(exercise.getName());
        //Show the sets (reps and weight) for the last time this
        //exercise was performed.
        setLastSetString();
        //setNoteString(exercise.getNote());
     }
    
    
    /**
     * Set up the actionbar (layout xml and title)
     * @param Menu the actionbar menu
     * @return true to make the menu visible
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_register_cardio, menu);
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
     * that shows the time and distance for when performing this exercise last time.
     */
    private void setLastSetString() {
    	//The textview that will show sets from last time.
    	TextView lastSetString = (TextView) findViewById(R.id.lastTimeSetsCardio);
    	lastSetString.setText("ska hämtas från DB");
		
	}
    
    private void setNoteString(String note)
    {
    	TextView notes = (TextView) findViewById(R.id.noteText);
    	notes.setText(note);
    }
    
    
    /**
     * Method called when pressing any of the buttons in the view
     * @param View v - The view that has been clicked
     */
    public void onClick(View v){
    	switch(v.getId()){
    	
    	//Cancel button pressed, exit without saving
    	case R.id.CardioButtonCancel:
    		finish();
    		break;
    		
    	//OK button pressed, exit and save to database
    	case R.id.CardioButtonOK:
    		saveSetsToDatabase();
    		finish();
    		break;
    		
    	//Finish set pressed, add the distance and time to the array
    	case R.id.CardioFinishSet:
    		addNewSet();
    		break;
    		
    	//Undo set pressed, remove the latest set from the array.
    	case R.id.CardioUndoSet:
    		removeLatestSet();
    		break;
    	}
    }

    /**
     * THis is called when pressing the "OK" button, will save the sets
     * to the database before this activity terminates.
     * 
     */
    private void saveSetsToDatabase() {
    	Toast.makeText(this, "will close this activity and save", Toast.LENGTH_SHORT).show();
		
	}



	/**
     * Will read the input of reps and weight EditTexts, add it to the array and update the view
     * 
     */
    private void addNewSet() {
    	//Input from the user, the minutes seconds and weight
    	EditText editMinutes = (EditText) findViewById(R.id.editMinutesCardio);
    	EditText editSeconds = (EditText) findViewById(R.id.editSecondsCardio);
    	EditText editDistance = (EditText) findViewById(R.id.editDistance);
    	
    	
    	//Read the time (minutes and seconds) and the distance.
    	//If any of them if blank, set it to zero
		String minutes = editMinutes.getText().toString();
		if(minutes.equals("")){
			minutes = "0";
		}
		String seconds = editSeconds.getText().toString();
		if(seconds.equals("")){
			seconds = "0";
		}
		String distance = editDistance.getText().toString();
		if(distance.equals("")){
			distance = "0";
		
		}
		//Dont add the set if both minutes and seconds is zero (= no time)
		if((minutes.equals("0")) && (seconds.equals("0"))){			
			
			Toast.makeText(this, "Cant add set with no time", Toast.LENGTH_SHORT).show();
			
		}  
		//else add the information to the array and update information in the view
		else{
			currentSets.add(minutes + ":" + seconds + "x" + distance);
			ListWorkoutDbHandler dbHandler = new ListWorkoutDbHandler(this);
			dbHandler.open();
			Integer min = Integer.parseInt(editMinutes.getText().toString());
			Integer sec = Integer.parseInt(editSeconds.getText().toString());
			Integer dist = Integer.parseInt(editDistance.getText().toString());
			dbHandler.addCardioSet(min, sec, dist, workoutId, exerciseId);
			updateView();
			
		}
			
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
		TextView currentSetString = (TextView) findViewById(R.id.thisTimeSetsCardio);
				
		//The prefix is used to separate each set in the string.
		String prefix = new String("");
		for (String s : currentSets)
		{
			string = string + prefix;
			prefix = ", ";
			string = string + s;
		}
		//Update the view with the new string
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
	
	private void getExerciseData()
	{
		ListWorkoutDbHandler dbHandler = new ListWorkoutDbHandler(this);
        dbHandler.open();
        exercise = dbHandler.getExerciseDataFromExerciseId(exerciseId);
        dbHandler.close();
		
	}
}
