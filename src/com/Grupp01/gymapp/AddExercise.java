package com.Grupp01.gymapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class AddExercise extends SherlockActivity implements AdapterView.OnItemSelectedListener {
	private TextView comment;
	private Button button;
	private String[] items;
	private Spinner spinner;
	//private Spinner typeOfTraining;
	//private static final String[] items={"Cardio", "Static", "Dynamic", "blaj"};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exercise);
		getSupportActionBar().setHomeButtonEnabled(true);

		Resources res = getResources();
		items = res.getStringArray(R.array.trainingtype_array);
		initSpinnerTypeOfTraining();
		setUpInvisLayout();
		button = (Button) findViewById(R.id.buttontest);
		button.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.activity_add_exercise, menu);
		return true;
	}
	
	public void setUpInvisLayout(){
		button = (Button) findViewById(R.id.buttontest);
		button.setVisibility(View.INVISIBLE);
		
		comment = (TextView) findViewById(R.id.comment);
		comment.setVisibility(View.INVISIBLE);
		
		
		
	}
	public void initSpinnerTypeOfTraining()
	{
		spinner = (Spinner) findViewById(R.id.spinner_type_of_training);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.trainingtype_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		spinner = (Spinner) findViewById(R.id.spinner_type_of_training);
		spinner.setOnItemSelectedListener(this);
		
	}
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if(items[position].equals("Static"))
		{
			setContentView(R.layout.add_exercise_static);
			
			//button.setVisibility(View.VISIBLE);
		}
		if(items[position].equals("Dynamic"))
		{
			button.setVisibility(View.VISIBLE);
			comment.setVisibility(View.VISIBLE);
		}
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}
}