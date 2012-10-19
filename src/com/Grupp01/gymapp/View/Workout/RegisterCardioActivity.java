/*Copyright © 2012 GivDev
 *
 * This file is part of Gymapp.
 *
 * Gymapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gymapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gymapp. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.Grupp01.gymapp.View.Workout;


import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.Controller.Workout.RegisterDbHandler;
import com.Grupp01.gymapp.Controller.Workout.SetsData;
import com.Grupp01.gymapp.Controller.Workout.WorkoutDbHandler;
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
	//Strings used no modify input
	private static final String EMPTY = "";
	private static final String ZERO = "0";
	//Default value for int passed via intent
	private static final int INTENT_INT_DEFAULT_VALUE = 0;
	//Object that holds the data about the current exercise
	private ExerciseData exercise; 
	//ID of current exercise
	private int exerciseId;
	//ID of the workout that the exercise belongs to.
	private int workoutId;
	//List with currently performed sets. Will in the end be written to the database.
	private List<SetsData> cardioSetsList = new LinkedList<SetsData>();
	//The desired unit for distance that the user has selected in the settings menu.
	private String distanceUnit;

	/**
	 * Set up the default layout and call initiate method that is required.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_cardio);
		//Get the id of the workout that the user is performing
		workoutId = getIntent().
				getIntExtra(WorkoutActivity.EXTRA_WORKOUT_ID, INTENT_INT_DEFAULT_VALUE);
		//Get the id of the current exercise.
		exerciseId = getIntent().
				getIntExtra(WorkoutActivity.EXTRA_EXERCISE_ID, INTENT_INT_DEFAULT_VALUE);
		//Get data about the exercise from the database
		getExerciseData();
		//Set the title to the exercise name.
		setTitle(exercise.getName());

		//Show the sets (reps and weight) for the last time this
		//exercise was performed.
		setLastSetsString();
		//Get the distance unit from sharedpreferences and set it in this class.
		setDistanceUnit();
		//Set the personal note
		setNoteString();
		//Set the string with information about the sets from last time.
		setLastSetsString();
	}

	/**
	 * This method will read the desired distance unit from the SharedPreferences.
	 */
	private void setDistanceUnit() {
		//Get the shared preferences object and find the value for the key corresponding
		//to the distance unit.
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		distanceUnit = sharedPref.getString("pref_key_distance", "km");
		
	}


	/**
	 * Set up the actionbar (layout xml and title)
	 * @param Menu the actionbar menu
	 * @return true to make the menu visible
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.universal_menu, menu);
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
	 * This method will set the personal note (related to the exercise) if the user has 
	 * set any.
	 */
	private void setNoteString()
	{
		//Get the note from the exercise data and set in the textfield.
		TextView notes = (TextView) findViewById(R.id.myNoteTextCardio);
		notes.setText(exercise.getNote());
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
			//adds all the added sets to database
			for(SetsData cardiosets: cardioSetsList)
			{
				addCardioSet(cardiosets);
			}
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
	 * Will read the input of reps and weight EditTexts, add it to the array and update the view
	 *
	 */
	private void addNewSet() {
		//Input from the user, the minutes seconds and weight
		EditText editMinutes = (EditText) findViewById(R.id.editMinutesCardio);
		EditText editSeconds = (EditText) findViewById(R.id.editSecondsCardio);
		EditText editDistance = (EditText) findViewById(R.id.editDistance);


		//Read the time (minutes and seconds) and the distance.
		//If any of them if blank(""), set it to zero("0")
		String minutes = editMinutes.getText().toString();
		if(minutes.equals(EMPTY)){
			minutes = ZERO;
		}
		String seconds = editSeconds.getText().toString();
		if(seconds.equals(EMPTY)){
			seconds = ZERO;
		}
		String distance = editDistance.getText().toString();
		if(distance.equals(EMPTY)){
			distance = ZERO;

		}
		//Dont add the set if both minutes and seconds is zero (= no time)
		if((minutes.equals(ZERO)) && (seconds.equals(ZERO))){	
			
			Toast.makeText(this, R.string.cant_add_set_without_time, Toast.LENGTH_SHORT).show();

		}
		//else add the information to the array and update information in the view
		else{
			Integer min = Integer.parseInt(minutes);
			Integer sec = Integer.parseInt(seconds);
			Integer dist = Integer.parseInt(distance);
			//Adds new sets to cardioSetsList and update the view
			cardioSetsList.add(new SetsData(min,sec,dist,workoutId,exerciseId));
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
		//The textview where current sets will be showed (added when button "Finish set" is pressed)
		TextView currentSetString = (TextView) findViewById(R.id.thisTimeSetsCardio);

		//Stringbuffer is used to create a string from all the sets in the cardioSetsList.
		//Will combine minutes, seconds, distance unit to "(Min)m (Sec)s x(Distance)(distanceUnit)
		//without the ().
		StringBuffer prefix = new StringBuffer();
		for (SetsData setData : cardioSetsList)
		{
			prefix.append(setData.getMin());
			prefix.append("m ");
			prefix.append(setData.getSec());
			prefix.append("s x");
			prefix.append(setData.getDistance());
			prefix.append(distanceUnit);
			prefix.append(", ");
		}
		//Update the view with the new string
		currentSetString.setText(prefix);
	}


	/**
	 * Will remove the last set from the array and then update the view.
	 *
	 */
	private void removeLatestSet() {
		//Only try to remove if the list contains any sets.
		if(cardioSetsList.size() > 0)
		{	
			cardioSetsList.remove(cardioSetsList.size() -1);
		}
		//Else show an error message to the user.
		else
		{
			Toast.makeText(this, R.string.no_sets_to_delete, Toast.LENGTH_SHORT).show();
		}
		updateView();
	}

	/**
	 * Gets information from database about the current exercise in a ExerciseData object.
	 */
	private void getExerciseData()
	{
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		exercise = dbHandler.getExerciseDataFromExerciseId(exerciseId);
		dbHandler.close();

	}

	/**
	 * Gets information about the 4 latest sets in this exercise 
	 * and puts this information on the screen.
	 */
	private void setLastSetsString()
	{
		//Use a stringbuffer to expand the string with time and distance for each set.
		StringBuffer sets = new StringBuffer();
		
		//The textview that the resulting string should be written in.
		TextView latestSets = (TextView) findViewById(R.id.lastTimeSetsCardio);
		
		//Create and open the database help object.
		RegisterDbHandler dbHandler = new RegisterDbHandler(this);
		dbHandler.open();
		
		//Get the list with sets from last time from the database.
		List<SetsData> previousSetsList = dbHandler.
				getPreviouslyCardioSets(exerciseId, exercise.getTypeId());
		dbHandler.close();
		
		//For each set, add it (and format correctly) to the stringbuffer.
		for(SetsData cardioSet: previousSetsList)
		{
			sets.append(cardioSet.getDuration());
			sets.append("x");
			sets.append(cardioSet.getDistance());
			sets.append(distanceUnit);
			sets.append(", ");
		}
		
		//Add the resulting string to the textView.
		latestSets.setText(sets);
	}

	/**
	 * This method will write a cardio set to the database.
	 * @param cardioSet
	 */
	private void addCardioSet(SetsData cardioSet)
	{
		//Create and open the database help object
		RegisterDbHandler dbHandler = new RegisterDbHandler(this);
		dbHandler.open();
		//Add the set to the database and close the object.
		dbHandler.addCardioSet(cardioSet.getSec(), cardioSet.getMin(), 
				cardioSet.getDistance(), cardioSet.getworkoutId(), cardioSet.getexerciseid());
		dbHandler.close();
		
	}
}