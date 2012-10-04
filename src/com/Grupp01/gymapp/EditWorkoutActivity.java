package com.Grupp01.gymapp;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

/** The Graphical layout where the user Edit a Workout
 *  * @author Robert Blomberg
 *  */	

public class EditWorkoutActivity extends SherlockActivity implements OnItemSelectedListener
{
	
	private ListView listView;
	private ArrayAdapter<Exercise> listAdapter;
	private String workoutName;
    public ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
	  
	  
	String[] muscleGroups = { "Hej", "detta", "är", "Robert", "och", "Anders",
			  "och" , "Joel", "och" , "Zotty"};
	  
	  
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        workoutName = intent.getStringExtra(ListWorkoutActivity.WORKOUT_NAME);
        
        setContentView(R.layout.editworkout);
        createEditWorkout();
        
	}
	
      @Override
      public boolean onCreateOptionsMenu(Menu menu)
      {
      	  getSupportMenuInflater().inflate(R.menu.editworkout, menu);
          getSupportActionBar().setHomeButtonEnabled(true);
          
          //Set the title to the name of the workout
          getSupportActionBar().setTitle(workoutName);
          return false;
      }
      public void createEditWorkout()
      {
    	// Find the ListView resource.   
        listView = (ListView) findViewById( R.id.mainListView );  

        	// When item is tapped, toggle checked properties of CheckBox and Exercise.  
	        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
	        {
	            /*** 
	             * Is called when the user press a Exercise in the workout, when
	             * a user click on a exercise, the exercise is getting checked
	             * If the exercise already was checked when clicked, it is getting unchecked
	             * * 
	             * @param parent		
	             * @param item		
	             * @param position	
	             * @param id*/
	            @Override  
	            public void onItemClick( AdapterView<?> parent, View item,   int position, long id) 
	            {  
	              Exercise Exercise = listAdapter.getItem( position );
	              Exercise.toogleChecked();
	              ExerciseViewHolder viewHolder = (ExerciseViewHolder) item.getTag();
	              viewHolder.getCheckBox().setChecked( Exercise.isChecked() );
	            }//End of onItemClick
	        });//End of Listener
          
          
	    //Create spinners and add listeners to them.
	    Spinner spinnerMuscleGroup = (Spinner) findViewById(R.id.spinnermusclegroup);
	  	spinnerMuscleGroup.setOnItemSelectedListener((OnItemSelectedListener) this);
	  		
	  	Spinner spinnerMuscle = (Spinner) findViewById(R.id.spinnermuscle);
	  	spinnerMuscle.setOnItemSelectedListener(this);
	  		
	
	  	//Creates ArrayAdapters that contains a String-Array	
	  	ArrayAdapter<String> muscleGroupSpinner = new ArrayAdapter<String>
	  	(this,android.R.layout.simple_spinner_item,muscleGroups);
	
	  	ArrayAdapter<String> muscleSpinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,muscleGroups); 
	  		
	  	//On click, make a dropdown menu with big buttons so the user won't missclick
	  	muscleGroupSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  	muscleSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  		
	  	//Set the Arrayadapter into the spinner
	  	spinnerMuscleGroup.setAdapter(muscleGroupSpinner);
	  	spinnerMuscle.setAdapter(muscleSpinner);
	         
          // Since we don't have a database we manually put in exercises
          //Could use a Arraylist directly but we use a String since we will load
          //String-Arrays from the database we use a String-array here
          if ( exerciseList.isEmpty() )
          {
          	exerciseList.add(new Exercise("hejja",false));
          	exerciseList.add(new Exercise("nbvn",false));
          	exerciseList.add(new Exercise("jyytj",false));
          	exerciseList.add(new Exercise("eqwe",true));
          	exerciseList.add(new Exercise("hej",false));
          	exerciseList.add(new Exercise("hejasd",true));
          	exerciseList.add(new Exercise("hejf",false));
          	exerciseList.add(new Exercise("hejzxc",false));
          	exerciseList.add(new Exercise("31234",false));
          	exerciseList.add(new Exercise("zxczc",false));	
          }
          
          // Set our custom Arrayadapter as the ListView's adapter.
          listAdapter = new ExerciseArrayAdapter(this, exerciseList);
          listView.setAdapter( listAdapter );
    }
    public void saveToDatabase(View view)
    {
    	//Implement later when we got a working database
    }
      
      /**Is called if a user press the cancel button,
       * asks the user if it want to close the dialog.*/
    public void cancelEditWorkoutDialog(View view)
    {
    	final AlertDialog.Builder closeEditWorkoutDialog = new AlertDialog.Builder(this);
    	
    	closeEditWorkoutDialog.setMessage("Are you sure you want to close workout?");
    	
    	closeEditWorkoutDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
    	{
    		/** When the user click the "Yes"-button, go back to ListWorkout*/
    		public void onClick(DialogInterface dialog, int whichButton) 
    		{
    			 finish();
    		 }
    	});
    	//If pressing "Cancel"
    	closeEditWorkoutDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    	{
			@Override
			/** When the user click the "Cancel"-button, close the dialog*/
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				dialog.cancel();
			}
		});
    	closeEditWorkoutDialog.show();
    }
    
      
    /** When a item in the spinners is selected, do
     * NOT IMPLEMENTED YET SINCE WE GOT NO DATABASE*/
  	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
  	{
  		switch (parent.getId()) 
  		{
  	    case R.id.spinnermusclegroup:
  	    //Implement later when we got a working database
  	    case R.id.spinnermuscle:
  		//Implement later when we got a working database
  		}
  	}

  	@Override
  	public void onNothingSelected(AdapterView<?> arg0){}  
 }  