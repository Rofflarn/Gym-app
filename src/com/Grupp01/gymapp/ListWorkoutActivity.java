package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ListWorkoutActivity extends SherlockActivity {
	private String  [] listWorkouts = { "Pass 1", "Pass 2", "Pass 3",
	"Pass 4", "Pass 5","Passg 6","Pass 7","Pass 8","Pass 9","Pass 10","Pass 11","Pass 12",};
	//list1 is only a string used in testing before fetching data from DB
	
	public final static String WORKOUT_NAME = "com.Grupp01.gymapp.WORKOUT";
	
	
	
	
	private ListView mainListView ;  				
	private ArrayAdapter<String> listAdapter ;  

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_workout);
		createWorkoutList();
	}
	
	
	
	
	/**
	 * Is called from onCreate to build the list with all workouts and
	 * set correct actions for onClick and also context menus for longclicking.
	 * 
	 */
	private void createWorkoutList() {
		//Builds the list of all workouts
		 mainListView = (ListView) findViewById( R.id.ListViewWorkouts );
		 ArrayList<String> arrayWorkouts = new ArrayList<String>();
		 arrayWorkouts.addAll( Arrays.asList(listWorkouts) );		
		 listAdapter = new ArrayAdapter<String>(this, R.layout.list, arrayWorkouts);
		 mainListView.setAdapter(listAdapter);   
		 registerForContextMenu(mainListView);
		 
		 //Set each row in the list clickable and fetch the title of the workout 
		 //to be able to open correct workout in WorkoutActivity
		 mainListView.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> arg0, View v, int position,
	                    long id) {
	            	
	            	//Get the text label of the row that has been clicked (will be used to open the correct workout)
	            	String workoutName = mainListView.getAdapter().getItem(position).toString();	
	            	startNewWorkout(workoutName);
	            	
	            	
	            }// end of onItemClick
	        });// end of setOnItemClickListener
		
	}


	
	
	
	
	/**
	 * Is called with the user selects to create a new workout (clicking the "add workout" button
	 * in ActionBar. 
	 * @param workoutName The name for the new workout.
	 * 
	 */
	private void startNewWorkout(String workoutName) {
		Intent workout = new Intent(ListWorkoutActivity.this, WorkoutActivity.class);
		workout.putExtra("WORKOUT_NAME", workoutName);
    	startActivity(workout);
	}
	
	
	
	
	
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.list_workout, menu);
    	//Set the App icon to work as a button in the actionbar
    	getSupportActionBar().setHomeButtonEnabled(true);
        return true;
    }
    
    
    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    		//Make app icon navigate back to the applications start screen.
    		case	android.R.id.home:
    			Intent intent = new Intent(this, MainActivity.class);
    			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    			return true;
    			
    			
    		//when clicking "add workout" a dialog pops up
    		case	R.id.menu_addWorkout:
    			openDialog();
    			
    			
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
    

    
    
    
    
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
        ContextMenuInfo menuInfo) {
      if (v.getId()==R.id.ListViewWorkouts) {
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
       // menu.setHeaderTitle(Countries[info.position]);
         menu.add(Menu.NONE, 0, 0, "Edit");
         menu.add(Menu.NONE, 1, 1, "Delete");
         
        }
      }
   
    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
    	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      int menuItemIndex = item.getItemId();
      String workoutName = mainListView.getAdapter().getItem(info.position).toString();
      switch(menuItemIndex){
      	case 0:
      		editWorkouts(workoutName);
      		return true;
      	case 1:
      		
      		deleteWorkout(workoutName);
      		return true;
      }
      return true;
    }


    
    
    
    /**
     * Is called from contextMenu when the user longclicks a workout and selects 
     * "Delete" from the menu.
     */
	private void deleteWorkout(String workoutName) {
		
		//Show a confirmation dialog before deleting
		AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
		deleteDialog.setMessage("Are you sure you want to delete workout " + workoutName +"?");
		deleteDialog.setCancelable(false);
		
		//Set action for clicking "Yes" (the user wants to delete)
		deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	  
	        	   Toast.makeText(ListWorkoutActivity.this, "Not implementet yet!", Toast.LENGTH_SHORT).show();
	           } //End of onclick method
			}	//end of newDialogInterface
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
     */
	private void editWorkouts(String workoutName) {
		Intent intent = new Intent(this, EditWorkout.class);
		intent.putExtra(WORKOUT_NAME, workoutName);
		startActivity(intent);		
		
	}

    //Handles the dialog
    private void openDialog()
    {
    	//Variables for the dialog
    	final AlertDialog.Builder addWorkout_Dialog = new AlertDialog.Builder(this);
    	final EditText editText_Dialog = new EditText(this);
    	final Intent intent2 = new Intent(this, EditWorkout.class);
    	
    	addWorkout_Dialog.setMessage("Enter name of Workout:");
    	addWorkout_Dialog.setView(editText_Dialog);
    	//If pressing "Add Workout"
    	addWorkout_Dialog.setPositiveButton("Add Workout", new DialogInterface.OnClickListener() 
    	{
    		public void onClick(DialogInterface dialog, int whichButton) 
    		{
    		  Editable value = editText_Dialog.getText();
    		  String workoutName = value.toString();
		 		//If the workout doesn't contain any characters, cancel the dialog and go back to the ListWorkoutActivity
		 		if(workoutName.trim().equals(""))
		 		{
		 			dialog.cancel();
		 			return;
		 		}
		 		//Add the name of the workout to the intent so the next activity can get the name
		 		intent2.putExtra(WORKOUT_NAME, workoutName);
		 		startActivity(intent2);
    		 }
    	});
    	//If pressing "Cancel"
    	addWorkout_Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    	{
			
			@Override
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				dialog.cancel();
			}
		});
    	
    	addWorkout_Dialog.show();
 
    }
}