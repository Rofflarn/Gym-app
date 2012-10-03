package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;



public class WorkoutActivity extends SherlockActivity {
	private String  [] listWorkouts = { "Dynamisk övning", "Statisk övning", "Cardio"};
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
	            	registerWorkoutResult(exerciseName);
	            	
	            	
	            }// onItemClick end
			});// setOnItemClickListener end
		
	
    }
    
    private void registerWorkoutResult(String exerciseName){
    	//ONLY FOR TESTING DIFFERENT REGISTER ACTIVITY!!
    	if(exerciseName.equals("Dynamisk övning")){
    		Intent intent = new Intent(WorkoutActivity.this, RegisterDynamicActivity.class);
    		intent.putExtra("exercisename", exerciseName);
    		startActivity(intent);
    	}
    	if(exerciseName.equals("Statisk övning")){
    		Intent intent = new Intent(WorkoutActivity.this, RegisterStaticActivity.class);
    		intent.putExtra("exercisename", exerciseName);
    		startActivity(intent);
    	}
    	if(exerciseName.equals("Cardio")){
    		Intent intent = new Intent(WorkoutActivity.this, RegisterCardioActivity.class);
    		intent.putExtra("exercisename", exerciseName);
    		startActivity(intent);
    	}
    	
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
    
    
    
    
   }
    

