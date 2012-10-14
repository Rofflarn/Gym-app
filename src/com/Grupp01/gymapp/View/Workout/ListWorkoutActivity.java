/*Copyright � 2012 GivDev
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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Controller.Workout.WorkoutDbHandler;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 04/10/12
 *
 * Class ListWorkoutActivity will show all workout routines that the user has created,
 *
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p>
 *
 */
public class ListWorkoutActivity extends SherlockActivity implements OnClickListener {


	public final static String WORKOUT_ID = "com.Grupp01.gymapp.View.ListWorkoutActivity.WORKOUT.ID";
	private ListView mainListView ; //This is the listview where the list of all workouts will be shown
	private ArrayAdapter<String> listAdapter ; //Adapter used for the list
	private List<IdName> idNameList;
	private Dialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_workout);
		createWorkoutList();
	}


	/**
	 * Set up the actionbar (layout xml and title)
	 * @param Menu the actionbar menu
	 * @return true to make the menu visible
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.list_workout, menu);
		//Set the App icon to work as a button in the actionbar
		getSupportActionBar().setHomeButtonEnabled(true);
		return true;
	}


	/**
	 * Set up actions for buttons in actionbar
	 * @param MenuItem item - The menuitem thas has been pressed
	 *
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		//Make app icon navigate back to the applications start screen.
		case	android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;


			//when clicking "add workout" a dialog pops up with input for the name
		case	R.id.menu_addWorkout:
			initDialog();


		default:
			return super.onOptionsItemSelected(item);
		}
	}
	public void initDialog()
	{
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle(R.string.dialog_new_workout_title);
		
		((Button) dialog.findViewById(R.id.add_Button)).setOnClickListener( this);
		((Button) dialog.findViewById(R.id.cancel_Button)).setOnClickListener( this);
		dialog.show();
	}



	/**
	 * This method creates the menu for longclicking a workout in the list
	 * @param menu, v & menuInfo
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		menu.add(Menu.NONE, 0, 0, "Edit");
		menu.add(Menu.NONE, 1, 1, "Delete");

	}
	/**
	 * Updates ListView containing all Workouts after adding a new Workout, and return back to this activity
	 */
	@Override
	public void onResume()
	{
		super.onResume();
		createWorkoutList();		
	}
	/**
	 * Is called when any of the options in the context menu is clicked.
	 * This method will make the selected action.
	 * @param item The context menu item that was clicked
	 */
	@Override
	public boolean onContextItemSelected(android.view.MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int menuItemIndex = item.getItemId();
		int workoutId = idNameList.get(info.position).getId();
		switch(menuItemIndex){
		case 0:
			editWorkouts(workoutId);
			return true;
		case 1:
			deleteWorkout(workoutId);
			return true;
		}
		return true;
	}


	/**
	 * Is called from onCreate to build the list with all workouts and
	 * set correct actions for onClick and also context menus for longclicking.
	 *
	 */
	private void createWorkoutList() {
		mainListView = (ListView) findViewById( R.id.ListViewWorkouts );
		ArrayList<String> arrayWorkouts = new ArrayList<String>();


		//Add all the strings from stringarray to the ArrayList

		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		idNameList = dbHandler.getWorkoutsIdName();
		dbHandler.close();

		for(IdName temp: idNameList)
		{
			arrayWorkouts.add(temp.getName());

		}

		//Set the listview layout with strings in array
		listAdapter = new ArrayAdapter<String>(this, R.layout.list_simple_row, arrayWorkouts);
		mainListView.setAdapter(listAdapter);

		//Activate longclick menu in the list
		registerForContextMenu(mainListView);

		//Set each row in the list clickable and fetch the title of the workout
		//to be able to open correct workout in WorkoutActivity
		mainListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				//Get the text label of the row that has been clicked (will be used to open the correct workout)
				int workoutId = idNameList.get(position).getId();
				startNewWorkout(workoutId);
			}// end of onItemClick
		});// end of setOnItemClickListener

	}


	/**
	 * Is called with the user selects to create a new workout (clicking the "add workout" button
	 * in ActionBar.
	 * @param workoutName The name for the new workout.
	 *
	 */
	private void startNewWorkout(int workoutId) {
		Intent workout = new Intent(ListWorkoutActivity.this, com.Grupp01.gymapp.View.Workout.WorkoutActivity.class);
		workout.putExtra(WORKOUT_ID, workoutId);
		startActivity(workout);
	}




	/**
	 * Is called from contextMenu when the user longclicks a workout and selects
	 * "Delete" from the menu.
	 * @param The name of the workout
	 */
	private void deleteWorkout(int id) {
		
		final int workoutId = id;
		//Show a confirmation dialog before deleting
		AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
		deleteDialog.setMessage("Are you sure you want to delete workout " + getWorkoutName(workoutId) +"?");
		deleteDialog.setCancelable(false);

		//Set action for clicking "Yes" (the user wants to delete)
		deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				WorkoutDbHandler dbHandler = new WorkoutDbHandler(ListWorkoutActivity.this);
				dbHandler.open();
				dbHandler.deleteWorkoutTemplate(workoutId);
				dbHandler.close();
				createWorkoutList();
			} //End of onclick method
		}	//end of DialogInterface
				);	//End of setPositiveButton

		//Set action for choosing not to delete (the dialog just closes and no action is taken)
		deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			} //End of onclick method
		}	//end of newDialogInterface
				);	//End of setPositiveButton

		//Show the dialog
		AlertDialog alert = deleteDialog.create();
		alert.show();
	}



	/**
	 * Is called from contextMenu when the user longclicks a workout and selects
	 * "Edit" from the menu. Will start EditWorkout to add/remove exercises.
	 * @param workoutName The name of the workout
	 */
<<<<<<< HEAD
	private void editWorkouts(String workoutName) {
		Intent intent = new Intent(this, com.Grupp01.gymapp.View.Workout.EditWorkoutActivity.class);
		intent.putExtra(WORKOUT_ID, workoutName);
=======
	private void editWorkouts(int workoutId) {
		Intent intent = new Intent(this, com.Grupp01.gymapp.View.Workout.EditWorkoutActivity.class);
		intent.putExtra(WORKOUT_ID, workoutId);
>>>>>>> 88fed55c9207f6dfb5afa8f57e257b8a6d7c974a
		startActivity(intent);	

	}
	/**
	 * Put the new Workout to database. Only the WorkoutName is put to database
	 * @param workoutName
	 */
	private int newWorkoutToDatabase(String workoutName)
	{
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		int id = dbHandler.addWorkoutTemplate(workoutName);
		dbHandler.close();
		return id;
	}

	/**
	 * Is called from when a user clicks the "Add workout"-button. Will open a dialog which ask
	 * the person to write a name for the workout. When the user clicks "Add workout" in
	 * the dialog the activity "EditWorkout" starts where you can add/remove exercises.
	 */

	@Override
	public void onClick(View view) {
		final Intent intent2 = new Intent(this, com.Grupp01.gymapp.View.Workout.EditWorkoutActivity.class);
		if(view == ((Button) dialog.findViewById(R.id.add_Button)))
		{
			//takes the text from exercise name textfield and puts it to AddExercise intent
			//if the string is not empty
			EditText newWorkoutNameField = (EditText) dialog.findViewById(R.id.exerciseName);
			String stringName = newWorkoutNameField.getText().toString();
			if(stringName.trim().length() > 0)
			{
				
				//Add the name of the workout to the intent so the next activity can get the name
				int id = newWorkoutToDatabase(stringName);
				intent2.putExtra(WORKOUT_ID, id);
				dialog.dismiss();
				startActivity(intent2);
			}
			else
			{
				newWorkoutNameField.setText("");
				newWorkoutNameField.setHint(R.string.invalid_value);
				newWorkoutNameField.setHintTextColor(Color.RED);
			}
		}
		else if(view == ((Button) dialog.findViewById(R.id.cancel_Button)))
		{
			dialog.dismiss();
		}
		
	}
	
	public String getWorkoutName(int workoutId)
	{
		WorkoutDbHandler dbHandler = new WorkoutDbHandler(this);
		dbHandler.open();
		String workoutName = dbHandler.getWorkoutIdNameById(workoutId).getName();
		dbHandler.close();
		return workoutName;
	}
}