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
 * @peer reviewed by Robert
 * @date 20/10/12
 *
 * Class RegisterStaticActivity is an activity that enables the user to
 * register his or hers result when performing a static exercise.
 * The user will be able to input time (minutes and seconds) and the weight.
 *
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p>
 *
 */
public class RegisterStaticActivity extends SherlockActivity {
	//Constants use to modify input
	private static final String EMPTY = "";
	private static final String ZERO = "0";
	//Default value for extra ints passed via Intent
	private static final int INTENT_INT_DEFAULT_VALUE = 0;
	//The list where new sets are added and removed (this will in the end be written to DB)
	private List<SetsData> staticSetsList = new LinkedList<SetsData>();
	//ID of current exercise
	private int exerciseId;
	//ID of the workout beeing performed
	private int workoutId;
	//Object holding information about the exercise (fetch from database)
	private ExerciseData exercise;
	//Current weight unit set by the user.
	private String weightUnit;

	/**
	 * Set up the default layout and call initiate method that is required.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Set the layout of the activity
		setContentView(R.layout.activity_register_static);
		//Get the exercise id and workout id which is passed via the intent
		exerciseId = getIntent().
				getIntExtra(WorkoutActivity.EXTRA_EXERCISE_ID, INTENT_INT_DEFAULT_VALUE);
		workoutId = getIntent().
				getIntExtra(WorkoutActivity.EXTRA_WORKOUT_ID, INTENT_INT_DEFAULT_VALUE);
		//Get information about the exercise from the database
		getExerciseData();
		//Set the title to the exercise name
		setTitle(exercise.getName());
		//Get the weight unit and set it to its variable.
		setWeightUnit();

		//Show the sets (reps and weight) for the last time this
		//exercise was performed.
		setLastSetString();
	}


	/**
	 * This method will read the users desired weight unit (which is set via the
	 * Settings activity from main menu). 
	 */
	private void setWeightUnit() {
		//Get the shared preferences
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		//Read the value for the key corresponding to weight unit. Default value is "kg"
		weightUnit = sharedPref.getString("pref_key_weight", "kg");
		
	}


	/**
	 * Set up the actionbar (layout xml and title)
	 * @param Menu the actionbar menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Set the layout of the actionbar.
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
			//Clear all activities on top of main activity in the stack
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
		//The textview that holds the information about sets from last time.
		TextView lastSet = (TextView) findViewById(R.id.lastTimeSetsStatic);
		//The list with the sets.
		List<SetsData> dynamicSetsList = new LinkedList<SetsData>();
		//The database help object we use to read the data
		RegisterDbHandler dbHandler = new RegisterDbHandler(this);
		//Stringbuffer is used to read reps and weight for each set
		//and stick it together into one string.
		StringBuffer sets = new StringBuffer();
		dbHandler.open();
		//Get the sets from the database
		dynamicSetsList = dbHandler.getPreviouslyStaticSets(exerciseId, exercise.getTypeId());
		//If the lists with sets is empty, there is nothing to show so set the string to "".
		if(dynamicSetsList.size() == 0)
		{
			lastSet.setText(EMPTY);
		}
		//Else use the stringbuffer to show "(Time)x(Weight)(weightUnit)" without ().
		else
		{
			for (SetsData setData : dynamicSetsList)
			{
				sets.append(" ");
				sets.append(setData.getDuration());
				sets.append("x");
				sets.append(setData.getWeight());
				sets.append(" " + weightUnit + ",");
			}
			//Finally show the resulting string.
			lastSet.setText(sets);
		}
		dbHandler.close();
	}



	/**
	 * Method called when pressing any of the buttons in the view
	 * @param View v - The view thas has been clicked
	 */

	public void onClick(View v){
		switch(v.getId()){

		//Cancel button pressed, exit without saving
		case R.id.staticButtonCancel:
			finish();
			break;

			//OK button pressed, exit and save to database
		case R.id.staticButtonOK:
			saveSetsToDatabase();
			finish();
			break;

			//Finish set pressed, add the reps and weight to the array
		case R.id.staticFinishSet:
			addNewSet();
			break;

			//Undo set pressed, remove the latest set from the array.
		case R.id.staticUndoSet:
			removeLatestSet();
			break;
		}
	}

	/**
	 * THis is called when pressing the "OK" button, will save the sets
	 * to the database before this activity terminates.
	 */
	private void saveSetsToDatabase() {
		//Create and open a help object
		RegisterDbHandler dbHandler = new RegisterDbHandler(this);
		dbHandler.open();
		//For each set, write the values to an entry in the db.
		for(SetsData setData: staticSetsList)
		{
			dbHandler.addStaticSet(setData.getMin(), setData.getSec(), 
					setData.getWeight(), workoutId, exerciseId);
		}
		dbHandler.close();	
	}



	/**
	 * Will read the input of reps and weight EditTexts, add it to the
	 * array and update the view
	 *
	 */
	private void addNewSet() {

		//Input from the user, the minutes seconds and weight
		EditText editMinutes = (EditText) findViewById(R.id.editMinutesStatic);
		EditText editSeconds = (EditText) findViewById(R.id.editSecondsStatic);
		EditText editWeight = (EditText) findViewById(R.id.editWeight);

		//Read minutes
		//If input of minutes is empty, then set it to "0"
		String minutes = editMinutes.getText().toString();
		if(minutes.equals(EMPTY)){
			minutes = ZERO;
		}
		//Read seconds
		//If input of seconds is empty, then set it to "0"
		String seconds = editSeconds.getText().toString();
		if(seconds.equals(EMPTY)){
			seconds = ZERO;
		}
		//Read weight
		//If input of weight is empty, then set it to "0"
		String weight = editWeight.getText().toString();
		if(weight.equals(EMPTY)){
			weight = ZERO;

		}
		//Dont add the set if both minutes and seconds is zero (= no time)
		if((minutes.equals(ZERO)) && (seconds.equals(ZERO))){	
			//Show a toast to the user with explanation.
			Toast.makeText(this, "Cant add set with 0 repetitions", Toast.LENGTH_SHORT).show();
		}
		else{
			//Parses strings to Integer
			Integer minInt = Integer.parseInt(minutes);
			Integer secInt = Integer.parseInt(seconds);
			Integer weightInt = Integer.parseInt(weight);
			//Add it to the list with sets.
			staticSetsList.add(new SetsData(minInt, secInt, weightInt));
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
		TextView currentSetString = (TextView) findViewById(R.id.thisTimeSetsStatic);

		//The prefix is used to separate each set in the string.
		//Stringbuffer is used to create a string with information from every set
		//in the list with sets.
		StringBuffer setsString = new StringBuffer();
		for (SetsData setData : staticSetsList)
		{
			setsString.append(setData.getMin());
			setsString.append("m ");
			setsString.append(setData.getSec());
			setsString.append("s x ");
			setsString.append(setData.getWeight());
			setsString.append(weightUnit);
			setsString.append(", ");
		}
		//Set the resulting string to show "current sets"
		currentSetString.setText(setsString);
	}
	/**
	 * Will remove the last set from the array and then update the view.
	 *
	 */
	private void removeLatestSet() {
		//Only remove if the list has entries.
		if(staticSetsList.size() > 0)
		{
			staticSetsList.remove(staticSetsList.size() -1);
		}
		//Else show an error to the user.
		else
		{
			Toast.makeText(this, R.string.no_sets_to_delete, Toast.LENGTH_SHORT).show();
		}
		updateView();
	}

	/**
	 * Gets information about the exercise in a ExerciseData object.
	 */
	private void getExerciseData()
	{
		//Create and open the database help object
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		//Get the exercise data for the specific exercise ID.
		exercise = dbHandler.getExerciseDataFromExerciseId(exerciseId);
		dbHandler.close();
	}
}


