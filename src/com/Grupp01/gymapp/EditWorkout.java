package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class EditWorkout extends SherlockActivity implements OnItemSelectedListener{

	  private ListView mainListView;  
	  private Exercise[] excercises;
	  private ArrayAdapter<Exercise> listAdapter;
	  private String workoutName;
	 
	  String[] muscleGroup = { "Hej", "detta", "är", "Robert"};
	  
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        workoutName = intent.getStringExtra(ListWorkoutActivity.WORKOUT_NAME);
        
        setContentView(R.layout.editworkout);
        // Find the ListView resource.   
        mainListView = (ListView) findViewById( R.id.mainListView );  
          

        // When item is tapped, toggle checked properties of CheckBox and Exercise.  
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {  
          @Override  
          public void onItemClick( AdapterView<?> parent, View item,   int position, long id) 
          {  
            Exercise Exercise = listAdapter.getItem( position );
            Exercise.toogleChecked();
            ExerciseViewHolder viewHolder = (ExerciseViewHolder) item.getTag();
            viewHolder.getCheckBox().setChecked( Exercise.isChecked() );
          }  
        });  
        
        
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
		spin.setOnItemSelectedListener((OnItemSelectedListener) this);

		ArrayAdapter<String> muscleGroups = new ArrayAdapter<String>
		(this,android.R.layout.simple_spinner_item,muscleGroup);

		muscleGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(muscleGroups);
        
		//Not implemented yet since we dont have a database
        Button save_Workout;
        
        Button cancel_EditWorkout = (Button) findViewById(R.id.button_Workout_Cancel);
        cancel_EditWorkout.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	cancel_EditWorkoutDialog();
    			
            }
        }
            );
        
        
		
		
        
        // Since we don't have a database we manually put in exercises
        if ( excercises == null )
        {
          excercises = new Exercise[]
        		  {
        		  new Exercise("Push-ups"), new Exercise("Push-ups"),
        		  new Exercise("Push-ups"), new Exercise("Dips"),
        		  new Exercise("Push-ups"), new Exercise("Push-ups"), 
        		  new Exercise("Push-ups"), new Exercise("sit-ups"),
        		  };
        }
        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.addAll( Arrays.asList(excercises) );
          
        // Set our custom array adapter as the ListView's adapter.
        listAdapter = new ExerciseArrayAdapter(this, exerciseList);
        mainListView.setAdapter( listAdapter );
	}
      @Override
      public boolean onCreateOptionsMenu(Menu menu)
      {
      	getSupportMenuInflater().inflate(R.menu.editworkout, menu);
          getSupportActionBar().setHomeButtonEnabled(true);
          
          //Set the title to the name of the workout
          getSupportActionBar().setTitle(workoutName);
          return true;
      }
      
    public void cancel_EditWorkoutDialog()
    {
    	final AlertDialog.Builder closeEditWorkout_Dialog = new AlertDialog.Builder(this);
    	
    	closeEditWorkout_Dialog.setMessage("Are you sure you want to close workout?");
    	
    	closeEditWorkout_Dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
    	{
    		public void onClick(DialogInterface dialog, int whichButton) 
    		{
    			 finish();
    		 }
    	});
    	//If pressing "Cancel"
    	closeEditWorkout_Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    	{
			
			@Override
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				dialog.cancel();
			}
		});
    	closeEditWorkout_Dialog.show();
    }
    
      
    //If you pressed a item on the spinner, do
  	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
  	{
  		
  	}

  	@Override
  	public void onNothingSelected(AdapterView<?> arg0)
  	{
  
  	}  
 }  