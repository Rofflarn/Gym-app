package com.Grupp01.gymapp;

import org.xml.sax.ContentHandler;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class AddExercise extends SherlockActivity implements OnItemSelectedListener {
	private TextView comment;
	private Button button;
	private String[] items;
	private Spinner spinnerType;
	private ContentHandler CH;
	//private Spinner typeOfTraining;
	//private static final String[] items={"Cardio", "Static", "Dynamic", "blaj"};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exercise);
		getSupportActionBar().setHomeButtonEnabled(true);

		Resources res = getResources();
		items = res.getStringArray(R.array.trainingtype_array);
		initSpinnerType();
		setUpInvisLayout();
		Spinner spinner = (Spinner) findViewById(R.id.spinner_type_of_training);
		spinner.setOnItemSelectedListener(this);
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
		
		
		
	
	public void initSpinnerType()
	{
		spinnerType = (Spinner) findViewById(R.id.spinner_type_of_training);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.trainingtype_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerType.setAdapter(adapter);
		
		//spinnerType = (Spinner) findViewById(R.id.spinner_type_of_training);
		//spinnerType.setOnItemSelectedListener(this);
		
	}
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			System.out.println("Entering onItemSelected");
		if(items[position].equals("Static"))
		{
			setContentView(R.layout.add_exercise_static);
			spinnerType = (Spinner)findViewById(R.id.spinner_type_of_training);
			initSpinnerType();
			spinnerType.setSelection(1);
			System.out.println("static");
			
			//button.setVisibility(View.VISIBLE);
		}
		
		else if(items[position].equals("Dynamic"))
		{
			setContentView(R.layout.add_exercise_static);
			spinnerType = (Spinner)findViewById(R.id.spinner_type_of_training);
			initSpinnerType();
			spinnerType.setSelection(2);
			System.out.println("dynamic");
		}
		else
		{
			setContentView(R.layout.add_exercise_static);
			System.out.println("cardio");
			spinnerType.setSelection(1);
			button.setVisibility(view.VISIBLE);
			initSpinnerType();
			//setContentView(R.layout.activity_add_exercise);
	
		}

		
	}

	public void onNothingSelected(AdapterView<?> parent) {
		
	}
}
