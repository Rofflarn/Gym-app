package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ListWorkoutActivity extends SherlockActivity {
	private String  [] listWorkouts = { "Övning 1", "Övning 2", "Övning 3",
	"Övning 4", "Övning 5","Övning 6","Övning 7","Övning 8","Övning 9","Övning 10","Övning 11","Övning 12",};
	//list1 is only a string used in testing before fetching data from DB
	
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
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
    	
}