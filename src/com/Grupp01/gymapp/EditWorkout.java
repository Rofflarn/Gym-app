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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class EditWorkout extends SherlockActivity implements OnItemSelectedListener
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
        // Find the ListView resource.   
        listView = (ListView) findViewById( R.id.mainListView );  
          

        // When item is tapped, toggle checked properties of CheckBox and Exercise.  
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          /*** 
           * Is called when the user press a Exercise in the workout, when
           * a user click on a exercise, the exercise is getting checked
           * If the exercise already was checked when clicked, it is getting unchecked
           * */
          @Override  
          public void onItemClick( AdapterView<?> parent, View item,   int position, long id) 
          {  
            Exercise Exercise = listAdapter.getItem( position );
            Exercise.toogleChecked();
            ExerciseViewHolder viewHolder = (ExerciseViewHolder) item.getTag();
            viewHolder.getCheckBox().setChecked( Exercise.isChecked() );
          }//End of onItemClick
        });//End of Listener
        
        //Create a spinner and add a listener to it.
        Spinner spinnerBody = (Spinner) findViewById(R.id.spinner1);
		spinnerBody.setOnItemSelectedListener((OnItemSelectedListener) this);
		
		Spinner spinnerMuscle = (Spinner) findViewById(R.id.spinner2);
		spinnerMuscle.setOnItemSelectedListener(this);
		

		//Creates an ArrayAdapter that contains the String-Array called musclegroups
		ArrayAdapter<String> bodyGroupSpinner = new ArrayAdapter<String>
		(this,android.R.layout.simple_spinner_item,muscleGroups);

		ArrayAdapter<String> muscleGroupSpinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,muscleGroups); 
		//On click, make a dropdown menu
		bodyGroupSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		muscleGroupSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//Set the Arrayadapter into the spinner
		spinnerBody.setAdapter(bodyGroupSpinner);
		spinnerMuscle.setAdapter(muscleGroupSpinner);
        
		//Not implemented yet since we dont have a database
        Button save_Workout;
        
        //Creates a cancel button and adds a listener
        Button cancel_EditWorkout = (Button) findViewById(R.id.button_Workout_Cancel);
        cancel_EditWorkout.setOnClickListener(new View.OnClickListener() 
        {
        	/**When the user clicks on the cancel button a dialog pop up that
        	 * asks the user if he want to close the "EditWorkout"-session*/
            public void onClick(View v) 
            {
            	cancel_EditWorkoutDialog();
            }
        }
        );
        
        
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
        	exerciseList.add(new Exercise("dasd",false));
        	exerciseList.add(new Exercise("hejdå",false));
        	
        }
        
        // Set our custom Arrayadapter as the ListView's adapter.
        listAdapter = new ExerciseArrayAdapter(this, exerciseList);
        listView.setAdapter( listAdapter );
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
      /**Is called if a user press the cancel button,
       * asks the user if it want to close the dialog.*/
    public void cancel_EditWorkoutDialog()
    {
    	final AlertDialog.Builder closeEditWorkout_Dialog = new AlertDialog.Builder(this);
    	
    	closeEditWorkout_Dialog.setMessage("Are you sure you want to close workout?");
    	
    	closeEditWorkout_Dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
    	{
    		/** When the user click the "Yes"-button, go back to ListWorkout*/
    		public void onClick(DialogInterface dialog, int whichButton) 
    		{
    			 finish();
    		 }
    	});
    	//If pressing "Cancel"
    	closeEditWorkout_Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    	{
			@Override
			/** When the user click the "Cancel"-button, close the dialog*/
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				dialog.cancel();
			}
		});
    	closeEditWorkout_Dialog.show();
    }
    
      
    /** When a item in the spinner is selected, do
     * NOT IMPLEMENTED YET SINCE WE GOT NO DATABASE*/
  	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
  	{
  		//Implement later when we got a working database
  	}

  	@Override
  	public void onNothingSelected(AdapterView<?> arg0){}  
 }  