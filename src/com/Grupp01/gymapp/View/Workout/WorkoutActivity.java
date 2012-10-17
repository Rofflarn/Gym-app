/*Copyright © 2012 GivDev
 * 
 * This file is part of Gymapp.
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
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

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
 * @peer reviewed by Robert(07/10-2012)
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
public class WorkoutActivity extends SherlockActivity implements OnItemClickListener{

	//Constants defining type of exercise
	private static final int CARDIO_TYPE = 1;
	private static final int DYNAMIC_TYPE = 2;
	private static final int STATIC_TYPE = 3;
	//Default value for passed int via Intent.
	private static final int INTENT_INT_DEFAULT_VALUE = 0;
	//Constants used to identify a string passed via Intent.
	public static final String EXTRA_EXERCISE_ID = "com.Grupp01.gymapp.message.exercise.exercise";
	public static final String EXTRA_WORKOUT_ID = "com.Grupp01.gymapp.message.exercise.workout";
	//The listview that holds all the exercises for the workout
	private ListView listExercisesView;	
	//Id of current workout
	private int workoutId;
	//List of exercises for the current workout
	private List<ExerciseData> exerciseDataList;
	//Buttons used to start and stop the workout.
	private Button buttonDone, buttonStart;

	/**
	 * Set up the default layout and list all exercises
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Get the id which was passed via the intent
		workoutId = getIntent().getIntExtra(ListWorkoutActivity.WORKOUT_ID, 
				INTENT_INT_DEFAULT_VALUE);
		//Set the layout of the activity
		setContentView(R.layout.activity_workout);
		
		//Get the name of the workout and set it as a title
		getAndSetTitle();
		
		//Get the list of exercises for the current workout from the database
		getExerciseDataList();
		
		//Use the list and show it in the listview
		listAllExercises();
		
		//Get both buttons, only allow the Start button to be visible.
		buttonStart= (Button) findViewById(R.id.button_start);
		buttonDone = (Button) findViewById(R.id.button_done);
		buttonDone.setVisibility(View.GONE);
	}
	/**
	 * Set up the actionbar (layout xml and title)
	 * @param Menu the actionbar menu
	 * @return true to make the menu visible
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Set the layout of the menu and set the app icon button to be clickable.
		getSupportMenuInflater().inflate(R.menu.activity_workout, menu);
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
			Intent intentHome = new Intent(this, MainActivity.class);
			intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentHome);
			return true;
		//Edit workout pressed, open EditWorkout activity and pass the id via the intent
		case R.id.menu_editWorkout:
			Intent intentEditWorkout = new Intent(this, EditWorkoutActivity.class);
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
		
		//Get the listview that will hold the list
		listExercisesView = (ListView) findViewById(R.id.activeWorkoutList);
		
		//The array which will be passed to the listview
		ArrayList<String> listExercises = new ArrayList<String>();

		//Add every exercise name from the list we got from the database
		//to the Array which we will send to the adapter.
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
	 * Is called when a user has clicked on an exercise in the list.
	 * Will get the exercise object and then open a new activity where
	 * the user can input his/her result.
	 */
	public void onItemClick(AdapterView<?> arg0, View v, int position,
			long id) {
		//Get the object of the clicked exercise and open a new activity
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
		//Check which type of exercise it is. Depending on type different actvities will
		//open and prompt for different input values.
		if(exercise.getTypeId() == CARDIO_TYPE){
			Intent intent = new Intent(WorkoutActivity.this, RegisterCardioActivity.class);
			//Pass the ID of the exercise (so launched activity knows which exercise the input
			//will be for.
			intent.putExtra(EXTRA_EXERCISE_ID, exercise.getId());
			//Pass the ID of the workout to the launched activity. This is needed 
			//so the entry of the Exercise in the database is referenced to the correct
			//workout id.
			intent.putExtra(EXTRA_WORKOUT_ID, workoutId);
			startActivity(intent);
		}
		if(exercise.getTypeId() == DYNAMIC_TYPE){
			Intent intent = new Intent(WorkoutActivity.this, RegisterDynamicActivity.class);
			//Pass the ID of the exercise (so launched activity knows which exercise the input
			//will be for.
			intent.putExtra(EXTRA_EXERCISE_ID, exercise.getId());
			//Pass the ID of the workout to the launched activity. This is needed 
			//so the entry of the Exercise in the database is referenced to the correct
			//workout id.
			intent.putExtra(EXTRA_WORKOUT_ID, workoutId);
			startActivity(intent);
		}
		if(exercise.getTypeId() == STATIC_TYPE){
			Intent intent = new Intent(WorkoutActivity.this, RegisterStaticActivity.class);
			//Pass the ID of the exercise (so launched activity knows which exercise the input
			//will be for.
			intent.putExtra(EXTRA_EXERCISE_ID, exercise.getId());
			//Pass the ID of the workout to the launched activity. This is needed 
			//so the entry of the Exercise in the database is referenced to the correct
			//workout id.
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
		//Get the name of the workout and set is as the title
		String title = dbHandler.getWorkoutIdNameById(workoutId).getName();
		setTitle(title);
		//Close the help object
		dbHandler.close();
	}

	/**
	 * Gets a workouts exercises from database
	 */
	private void getExerciseDataList()
	{
		//Create and open a database help object
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		//Get the list of all exercises for the specific workout id
		exerciseDataList = dbHandler.getExerciseIdNameById
				(dbHandler.getExercisesbyWorkoutId(workoutId));
		//Close help object
		dbHandler.close();
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		getExerciseDataList();
		listAllExercises();
	}
	/**
	 * Is called when the user presses the Start button
	 * @param view
	 */
	public void start(View view)
	{	
		//When pressing start, activate the onclicklistener for the listitems-
		listExercisesView.setOnItemClickListener(this);
		//Hide the start button and show the "Done"- button
		buttonStart.setVisibility(View.GONE);
		buttonDone.setVisibility(View.VISIBLE);
		//Disable app icon navigation in action bar
		getSupportActionBar().setHomeButtonEnabled(false);
	}
	/**
	 * Is called when the user presses the done button.
	 * @param view
	 */
	public void done(View view)
	{
		//Remove the clicklistener and close the activity.
		listExercisesView.setOnItemClickListener(null);
		finish();
	}

}


