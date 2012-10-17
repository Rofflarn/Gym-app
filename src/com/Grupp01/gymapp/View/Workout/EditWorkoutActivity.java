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
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.Exercise.ListExerciseDbHandler;
import com.Grupp01.gymapp.Controller.Workout.WorkoutDbHandler;
import com.Grupp01.gymapp.View.Exercise.EditExerciseActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

/** 
 * The Graphical layout where the user Edit a Workout.
 *  @author Robert Blomberg
 */	

public class EditWorkoutActivity extends SherlockActivity implements OnClickListener
{
	public static final String EXTRA_EXERCISE_NAME = "com.Grupp01.gymapp.message";
	private Dialog dialogAddExercise;
	private Dialog dialogCancelEditWorkout;
	private ArrayAdapter<ExerciseListElementData> listAdapter;
	private int workoutId;
	private List<ExerciseListElementData> exerciseList = new ArrayList<ExerciseListElementData>();
	


	/**Setups the layout of the class
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		initDialogueAddExercise();
		initDialogueCancelEditWorkout();
		if(intent.getIntExtra(ListWorkoutActivity.WORKOUT_ID, 0) !=0)
		{
			workoutId = intent.getIntExtra(ListWorkoutActivity.WORKOUT_ID, 0);
		}
		else
		{
			workoutId = intent.getIntExtra(WorkoutActivity.EXTRA_WORKOUT_ID, 0);
		}
		setContentView(R.layout.editworkout);
		createEditWorkout();

	}

	/**Setups the menu of the class
	 * @param menu
	 * @return true = menu shown false = menu hidden
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.activity_list_exercises, menu);
		//Enables the home button in SherlockActionBar
		getSupportActionBar().setHomeButtonEnabled(true);
		return true;
	}
	/** The method listens to the home button and to add a new exercise button in the menu
	 * @param item the item that has been clicked
	 * @return true
	 */     
	@Override
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item)
	{
		if(item.getItemId() == R.id.menu_addExe)
		{
			//shows the dialogAddExercise if addExe button is pressed
			dialogAddExercise.show();

		}
		//If the user presses the home button(the logo)
		else if(item.getItemId() == android.R.id.home)
		{
			//from developer.android.com
			Intent parentActivityIntent = new Intent(this, MainActivity.class);
			parentActivityIntent.addFlags(
					Intent.FLAG_ACTIVITY_CLEAR_TOP |
					Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();

		}
		return true;
	}
	/** Listens to the add and cancel button in the dialogAddExercise
	 * @param view the view that is clicked
	 */
	@Override
	public void onClick(View view)
	{
		if(view == (Button) dialogCancelEditWorkout.findViewById(R.id.yes_Button))
		{
			finish();
		}
		else if(view == (Button) dialogCancelEditWorkout.findViewById(R.id.cancel_Button))
		{
			dialogCancelEditWorkout.dismiss();
		}
		else if(view == ((Button) dialogAddExercise.findViewById(R.id.add_Button)))
		{
			//takes the text from exercise name textfield and puts it to AddExercise intent		
			EditText name = (EditText) dialogAddExercise.findViewById(R.id.exerciseName);
			String temp = name.getText().toString();
			//if the string is empty, prompt for a name
			if(temp.length() == 0)
			{
				//set the editTextfield to
				name.setHint("Fyll i ett namn");
				name.setHintTextColor(Color.RED);
			}
			//Else, create intent and start EditExerciseActivity
			else
			{
				ListExerciseDbHandler dbHandler = new ListExerciseDbHandler(this);
				dbHandler.open();
				int id = dbHandler.addExercise(temp);
				Intent intentAddExercise = new Intent(this, EditExerciseActivity.class);
				intentAddExercise.putExtra(EXTRA_EXERCISE_NAME, id);
				dialogAddExercise.dismiss();
				startActivity(intentAddExercise);
			}
		}
		//If the user presses the cancel-button, close the dialogAddExercise.
		else if(view == ((Button) dialogAddExercise.findViewById(R.id.cancel_Button)))
		{
			dialogAddExercise.dismiss();
		}
	}
	/**
	 * Updates ListView containing all Workouts after adding a new Workout, and 
	 * return back to this activity.
	 */
	public void onResume()
	{
		super.onResume();
		createEditWorkout();
	}
	/**
	 * Initialize dialogAddExerciseue-window by adding buttons and setting layout
	 */
	public void initDialogueAddExercise()
	{
		//setup a new dialogAddExercise
		dialogAddExercise = new Dialog(this);
		dialogAddExercise.setContentView(R.layout.dialog);
		dialogAddExercise.setTitle("New Exercise");
		
		//Create listeners to the button
		((Button) dialogAddExercise.findViewById(R.id.add_Button)).setOnClickListener(this);
		((Button) dialogAddExercise.findViewById(R.id.cancel_Button)).setOnClickListener(this);
	}
	public void initDialogueCancelEditWorkout()
	{
		//setup a new dialogAddExercise
		dialogCancelEditWorkout = new Dialog(this);
		dialogCancelEditWorkout.setContentView(R.layout.alertdialog_workout);
		//Create listeners to the button
		((Button) dialogCancelEditWorkout.findViewById(R.id.cancel_Button)).setOnClickListener(this);
		((Button) dialogCancelEditWorkout.findViewById(R.id.yes_Button)).setOnClickListener(this);

	}
	/** Creates all the listeners and the listview for the dialogAddExercise
	 */
	public void createEditWorkout()
	{
		// Find the ListView resource.
		ListView listView = (ListView) findViewById( R.id.mainListView );

		// When item is tapped, toggle checked properties of CheckBox and Exercise.
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			/**Is called when the user press a Exercise in the workout, when
			 * a user click on a exercise, the exercise is getting checked
			 * If the exercise already was checked when clicked, it is getting unchecked
			 * *
			 * @param parent
			 * @param item
			 * @param position
			 * @param id*/
			@Override
			public void onItemClick( AdapterView<?> parent, View item, int position, long id)
			{
				//Get the item the user clicks on
				ExerciseListElementData exercise = listAdapter.getItem( position );
				//Set the exercise to checked.
				exercise.toogleChecked();
				//Tag the object, so if the user presses the name, the checkbox
				//is also getting checked or unchecked
				ExerciseViewHolder viewHolder = (ExerciseViewHolder) item.getTag();
				//Uncheck or check the checkbox
				viewHolder.getCheckBox().setChecked( exercise.isChecked() );
			}//End of onItemClick
		});//End of Listener

		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		exerciseList = (ArrayList<ExerciseListElementData>) 
				dbHandler.getExercisesCheckedByWorkoutTemplateId(workoutId);
		dbHandler.close();


		// Set our custom Arrayadapter as the ListView's adapter.
		listAdapter = new ExerciseArrayAdapter(this, exerciseList);
		listView.setAdapter( listAdapter );
	}

	/**
	 * Saves an Edited Workout to Databse
	 * @param view
	 */
	public void saveToDatabase(View view)
	{
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		for(ExerciseListElementData exercise: exerciseList)
		{
			dbHandler.editWorkoutTemplate(exercise, workoutId);
		}
		dbHandler.close();
		finish();
	}
	public void cancelEditWorkoutDialog(View view)
	{
		dialogCancelEditWorkout.show();
	}
} 