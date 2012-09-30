package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
	private String  [] listWorkouts = { "Övning 1", "Övning 2", "Övning 3",
	"Övning 4", "Övning 5","Övning 6","Övning 7","Övning 8","Övning 9","Övning 10","Övning 11","Övning 12",};
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
		
		//Builds the list of all workouts
		 mainListView = (ListView) findViewById( R.id.ListViewWorkouts );
		 ArrayList<String> arrayWorkouts = new ArrayList<String>();
		 arrayWorkouts.addAll( Arrays.asList(listWorkouts) );		
		 listAdapter = new ArrayAdapter<String>(this, R.layout.list, arrayWorkouts);
		 mainListView.setAdapter(listAdapter);   
		 
		 
		 //Set each row in the list clickable and fetch the title of the workout 
		 //to be able to open correct workout in WorkoutActivity
		 mainListView.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> arg0, View v, int position,
	                    long id) {
	            	//Get the text label of the row that has been clicked
	            	String listRow = mainListView.getAdapter().getItem(position).toString();	
	            	
	            	//The toast is only a test so far to make sure that the information about the text label is correct
	            	Toast.makeText(getApplicationContext(), listRow, Toast.LENGTH_SHORT).show();
	            	
	            }// end of onItemClick
	        });// end of setOnItemClickListener
		 
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