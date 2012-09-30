package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;



public class WorkoutActivity extends SherlockActivity {
	private String  [] listWorkouts = { "Övning 1", "Övning 2", "Övning 3",
			"Övning 4", "Övning 5","Övning 6","Övning 7","Övning 8","Övning 9","Övning 10","Övning 11","Övning 12",};
			//list1 is only a string used in testing before fetching data from DB
	private ListView listExercisesView;
	private String workoutName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutName = getIntent().getStringExtra("WORKOUT_NAME");
        setContentView(R.layout.activity_workout);
        listAllExercises();
    }
/**
 * Is called when the activity starts to set the listview to
 * show all exercises available in this workout.
 */
    private void listAllExercises() {
		listExercisesView = (ListView) findViewById(R.id.activeWorkoutList) ;
		ArrayList<String> listExercises = new ArrayList<String>();
		listExercises.addAll(Arrays.asList(listWorkouts));
		ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.list, listExercises);
		listExercisesView.setAdapter(listAdapter);
		
	
		//Set up listener and action for pressing the listview
		listExercisesView.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> arg0, View v, int position,
	                    long id) {
	            	
	            	//Get the text label of the row that has been clicked (will be used to open the correct workout)
	            	String exerciseName = listExercisesView.getAdapter().getItem(position).toString();	
	            	openInputDialog(exerciseName);
	            	
	            	
	            }// onItemClick end
			});// setOnItemClickListener end
		
	
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.activity_workout, menu);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        //Set the title to the name of the workout
        getSupportActionBar().setTitle(workoutName);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    		//Make app icon navigate back to the applications start screen.
    		case	android.R.id.home:
    			Intent intent = new Intent(this, MainActivity.class);
    			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    			return true;
    		case R.id.menu_editWorkout:
    			Intent intent2 = new Intent(this, EditWorkout.class);
    			intent2.putExtra(ListWorkoutActivity.WORKOUT_NAME, workoutName);
    			startActivity(intent2);
    			return true;    			
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
    
    
    
    
    /**
     * Is called when the user clicks on an exercise in the list of exercises in the workout.
     * Will open the corresponding input dialog depending on which type if exercise it is.
     * @param workoutName The name of the workout
     */
    private void openInputDialog(String exerciseName) {
    	inputDynamicExercise(exerciseName);
		
	}
    
    private void inputDynamicExercise(String exerciseName){
    	// custom dialog
    				final Dialog dialog = new Dialog(this);
    				dialog.setContentView(R.layout.dynamic_dialog);
    				dialog.setTitle(exerciseName);
    	 
    				// set the custom dialog components - text, image and button
    //				TextView text = (TextView) dialog.findViewById(R.id.text);
    //				text.setText("Android custom dialog example!");
    	 
    				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
    				// if button is clicked, close the custom dialog
    				dialogButton.setOnClickListener(new OnClickListener() {
    					@Override
    					public void onClick(View v) {
    						dialog.dismiss();
    					}
    				});
    	 
    				dialog.show();
    }
    
}
    

