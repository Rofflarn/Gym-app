package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
	
	//Variables for the dialog
	TextView Dialog_TextView;
	EditText Dialog_EditText;
	Button Dialog_addWorkout, Dialog_Cancel;
	Dialog d;
	
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
      switch(menuItemIndex){
      	case 0:
    	  editWorkouts();
    	  return true;
      	case 1:
    	  deleteWorkout();
    	  return true;
      }
      return true;
    }


    
    
    
    /**
     * Is called from contextMenu when the user longclicks a workout and selects 
     * "Delete" from the menu.
     */
	private void deleteWorkout() {
		
		//Show a confirmation dialog before deleting
		AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
		deleteDialog.setMessage("Are you sure you want to delete the workout?");
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
     * "Delete" from the menu.
     */
	private void editWorkouts() {
		Toast.makeText(this, "Will open edit workout activity", Toast.LENGTH_SHORT).show();
		
	}

    //Handles the dialog
    private void openDialog()
    {
    	d = new Dialog(ListWorkoutActivity.this);
		
		
		d.setContentView(R.layout.newworkoutdialog);
		d.setTitle("Create a new Workout");
		//An intent that starts when you will click 
		final Intent intent2 = new Intent(this,EditWorkout.class);

		//A listener to the "add workout" button in the dialog
		 Button.OnClickListener Dialog_addWorkoutListener
		   = new Button.OnClickListener()
		 {
		 
			 
			 //When the button is clicked
		 @Override
		 		public void onClick(View view) 
		 		{
			 		String workoutName = Dialog_EditText.getText().toString();
			 		//If the workout doesn't contain any characters, cancel the dialog and go back to the ListWorkoutActivity
			 		if(workoutName.trim().equals(""))
			 		{
			 			d.dismiss();
			 			return;
			 		}
			 		//Add the name of the workout to the intent so the next activity can get the name
			 		intent2.putExtra(WORKOUT_NAME, workoutName);
			 		startActivity(intent2);
		 		}
		    
		 };
		//A listener that is getting added to the "Cancel" button in the dialog  
		 Button.OnClickListener Dialog_CancelListener
		   = new Button.OnClickListener()
		 {
		 
			 	@Override
			 	//When the button is clicked
			 	public void onClick(View view)
			 	{
			 		//Cancel the dialog
			 		d.dismiss();
			 	}
		    
		 };
		//Creates all of the GUI in the dialog 
		Dialog_EditText = 	(EditText)d.findViewById(R.id.dialogedittext);
		Dialog_TextView = 	(TextView)d.findViewById(R.id.dialogtextview);
	    Dialog_addWorkout = (Button)d.findViewById(R.id.dialogaddworkout);
	    Dialog_Cancel = 	(Button)d.findViewById(R.id.dialogcancel);
	    
	    //The listeners are added to the buttons
	    Dialog_addWorkout.setOnClickListener(Dialog_addWorkoutListener);
	    Dialog_Cancel.setOnClickListener(Dialog_CancelListener);
	    
		d.show();
    }

    	
}