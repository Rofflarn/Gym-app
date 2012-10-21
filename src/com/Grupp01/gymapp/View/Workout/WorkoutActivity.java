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

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.Controller.Workout.WorkoutDbHandler;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Robert
 * @date 04/10/12
 *
 * Class WorkoutActivity will be shown if the user selects a workout routine from the list in
 * ListWorkoutActivity. It will display all exercises in the specific workout routine
 * and enables the user to registering workout results by selecting an exercise from the list.
 *
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p>
 *
 */

public class WorkoutActivity extends SherlockActivity implements OnClickListener, OnItemClickListener{

	//Constants defining type of exercise
	private static final int EXERCISE_CARDIO = 1;
	private static final int EXERCISE_DYNAMIC = 2;
	private static final int EXERCISE_STATIC = 3;
	//Default value for passed int via Intent.
	private static final int INTENT_INT_DEFAULT_VALUE = 0;
	//Constants used to identify a string passed via Intent.
	public static final String EXTRA_EXERCISE_ID = "com.Grupp01.gymapp.message.exercise.exercise";
	public static final String EXTRA_WORKOUT_ID = "com.Grupp01.gymapp.message.exercise.workout";

	//The listview that holds all the exercises for the workout
	private ListView listExercisesView;	
	//Id of current workouttemplate
	private int workoutId;
	//List of all exercises that this workout has
	private List<ExerciseData> exerciseDataList;
	//Start and done button at the bottom of the screen
	private Button buttonDone, buttonStart;
	private Dialog dialog;

	/**
	 * Set up the default layout and list all exercises
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Get the ID of the workout which was passed via the invoking intent
		workoutId = getIntent().
				getIntExtra(ListWorkoutActivity.WORKOUT_ID, INTENT_INT_DEFAULT_VALUE);

		//Set layout from xml file
		setContentView(R.layout.activity_workout);
		//Get the name of the workout and set the title to the name
		getAndSetTitle();

		//Get the list with all exercises that this workout has
		getExerciseDataList();

		//Build the list with all the exercises
		listAllExercises();

		//Fetch the buttons which will be visible at the bottom of the screen
		buttonStart= (Button) findViewById(R.id.button_start);
		buttonDone = (Button) findViewById(R.id.button_done);

		//Only show the "done" button when the user has pressed the "Start" button
		buttonDone.setVisibility(View.GONE);
	}
	/**
	 * Set up the actionbar (layout xml and title)
	 * @param Menu the actionbar menu
	 * @return true to make the menu visible
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Set layout of menu
		getSupportMenuInflater().inflate(R.menu.activity_workout, menu);

		//Make the app icon be clickable
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
		//User clicked the app icon, navigate back to the main activity.
		case	android.R.id.home:
			Intent intentHome = new Intent(this, MainActivity.class);
			//Clear all activites above the main activity in the stack
			intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentHome);
			return true;

			//User selected "edit" from actionbar menu, open new activity where the user
			//can add/remove exercises from the workout.

		case R.id.menu_editWorkout:
			Intent intentEditWorkout = new Intent(this, EditWorkoutActivity.class);
			//Pass the id of the workout
			intentEditWorkout.putExtra(WorkoutActivity.EXTRA_WORKOUT_ID, workoutId);
			startActivity(intentEditWorkout);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Is called when the activity starts to set the listview to
	 * show all exercises available in this workout.
	 */
	private void listAllExercises() {
		//Get the listview that we are going to use
		listExercisesView = (ListView) findViewById(R.id.activeWorkoutList);

		//This is the array we will use to hold all exercise names

		ArrayList<String> listExercises = new ArrayList<String>();

		//For each ExerciseData object that we retrieve from the database
		//add the name from the object to the array.
		for(ExerciseData name: exerciseDataList)
		{
			listExercises.add(name.getName());
		}

		//The adapter we use to adapt the array to the listview
		ListAdapter listAdapter = new ArrayAdapter<String>
		(this, R.layout.list_simple_row, listExercises);
		//Create the listview
		listExercisesView.setAdapter(listAdapter);
	}

	/**
	 * This method is called when the user clicks on an exercise name. Will call the method that
	 * opens a new activity where the user can input data for the sets (reps, weight, time).
	 */
	public void onItemClick(AdapterView<?> arg0, View v, int position,
			long id) {
		//Get the object corresponding to the position in the list and send to the receiving method
		ExerciseData exercise = exerciseDataList.get(position);
		registerWorkoutResult(exercise);
	}


	/**
	 * This is called when pressing a exercise from the list of exercises in the list.
	 * Will open the correct registration activity depending on which type of exercise
	 * it is (dynamic, static or cardio).
	 * @param exerciseName The name of the exercise
	 */
	private void registerWorkoutResult(ExerciseData exercise){
		//Check which type of exercise it is and open corresponding activity. Send both exercise id and
		//the id of the current workout.
		if(exercise.getTypeId() == EXERCISE_CARDIO){
			Intent intent = new Intent(WorkoutActivity.this, RegisterCardioActivity.class);
			intent.putExtra(EXTRA_EXERCISE_ID, exercise.getId());
			intent.putExtra(EXTRA_WORKOUT_ID, workoutId);
			startActivity(intent);
		}
		if(exercise.getTypeId() == EXERCISE_DYNAMIC){
			Intent intent = new Intent(WorkoutActivity.this, RegisterDynamicActivity.class);
			intent.putExtra(EXTRA_EXERCISE_ID, exercise.getId());
			intent.putExtra(EXTRA_WORKOUT_ID, workoutId);
			startActivity(intent);
		}
		if(exercise.getTypeId() == EXERCISE_STATIC){
			Intent intent = new Intent(WorkoutActivity.this, RegisterStaticActivity.class);
			intent.putExtra(EXTRA_EXERCISE_ID, exercise.getId());
			intent.putExtra(EXTRA_WORKOUT_ID, workoutId);
			startActivity(intent);
		}
	}

	/**
	 * Gets the name of the workout and sets that name to title in activity
	 */
	private void getAndSetTitle()
	{
		//Create and open the database help object
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();

		//Get the name of the workout from the database, set it as title and then close the help object.
		String title = dbHandler.getWorkoutTemplateIdNameById(workoutId).getName();
		setTitle(title);
		dbHandler.close();
	}

	/**
	 * Gets a workouts exercises from database
	 */
	private void getExerciseDataList()
	{
		//Create and open the database help object
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();

		//Get the list with all exercises that is linked to the current workout.
		exerciseDataList = dbHandler.getExerciseIdNameById(dbHandler.getWorkoutTemplateExerciseByWorkoutTemplateId(workoutId));
		dbHandler.close();
	}

	/**
	 * Once the user gets back to this activity from another, refresh the list with exercises.
	 * Is necessary if the user has selected "edit" and removed and/or added exercises.
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		//Get the list with exercises from the database again and then rebuild the listview.
		getExerciseDataList();
		listAllExercises();
	}

	/**
	 * This method is called when the user has pressed the "Start" button. It will disable the 
	 * button and instead set the "Done" button to visible. 
	 * It will also add a new performed workout to the "workout" table in the database, which allows 
	 * this workout to be added to the history.
	 * @param view
	 */
	public void start(View view)
	{	
		//Add the listener to the listview so the user can click on exercises.
		listExercisesView.setOnItemClickListener(this);
		//Hide/show the buttons.
		buttonStart.setVisibility(View.GONE);
		buttonDone.setVisibility(View.VISIBLE);
		//Create and open a database help object.
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		//Get the name of the current workouttemplate
		String tmp = dbHandler.getWorkoutTemplateIdNameById(workoutId).getName();
		//Add the workouttemplate as a new workout to the table of performed workouts.
		//Needs to receive an int back (when we can use it)
		dbHandler.addWorkout(tmp);
		dbHandler.close();
	}

	/**
	 * Is called when the user clicks the "done" button to finish his/hers workout.
	 * It will disable the functionality to click on exercises in the list and then 
	 * kill this activity.
	 * @param view
	 */
	public void done(View view)
	{
		//Disable the onclicklistener and finish activity.
		listExercisesView.setOnItemClickListener(null);
		finish();
	}


	/**
	 * Is called when the user presses the back-key, prompting
	 * if user is working out.
	 * @param keyCode
	 * @param event
	 */
	@Override
	public void onBackPressed() 
	{
		//if user is working out, prompt before quitting
		if(buttonDone.getVisibility()!=View.GONE)
		{
			//Show a confirmation dialog before deleting
			dialog = new Dialog(this);
			dialog.setContentView(R.layout.y_n_dialog);
			dialog.setTitle(R.string.leaving);
			((TextView) dialog.findViewById(R.id.TV_dialog)).setText(R.string.done_training);

			((Button) dialog.findViewById(R.id.yes_Button)).setOnClickListener(this);
			((Button) dialog.findViewById(R.id.no_Button)).setOnClickListener(this);
			dialog.setCancelable(false);
			dialog.show();
			
		}
		//if not working out
		else
		{
			//back-key working as usual
			super.onBackPressed();
		}
	}
	
	/**
	 * Take action depending on users selection from the quit dialog.
	 */
	@Override
	public void onClick(View view) 
	{
		if(view == ((Button) dialog.findViewById(R.id.yes_Button)))
		{
			finish();
		}
		else
		{
			dialog.dismiss();
		}

	}

}
