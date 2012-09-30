package com.Grupp01.gymapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class AddExercise extends SherlockActivity implements AdapterView.OnItemSelectedListener {
	private TextView tvPMuscle,tvSMuscle, tvSport;
	private String[] items;
	private Spinner spinnerType, spinnerPMuscle, spinnerSMuscle, spinnerSport;

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
		spinnerPMuscle = (Spinner) findViewById(R.id.spinner_primary_muscle);
		spinnerPMuscle.setVisibility(View.INVISIBLE);

		spinnerSMuscle = (Spinner) findViewById(R.id.spinner_secondary_muscle);
		spinnerSMuscle.setVisibility(View.INVISIBLE);

		spinnerSport = (Spinner) findViewById(R.id.spinner_sport);
		spinnerSport.setVisibility(View.INVISIBLE);

		tvPMuscle = (TextView) findViewById(R.id.textView_primary_muscle);
		tvPMuscle.setVisibility(View.INVISIBLE);

		tvSMuscle = (TextView) findViewById(R.id.textView_secondary_muscle);
		tvSMuscle.setVisibility(View.INVISIBLE);

		tvSport = (TextView) findViewById(R.id.textView_sport);
		tvSport.setVisibility(View.INVISIBLE);

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

		spinnerType = (Spinner) findViewById(R.id.spinner_type_of_training);
		spinnerType.setOnItemSelectedListener(this);

	}

	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {		
		if(items[position].equals("Static"))
		{
			setUpInvisLayout();
			spinnerPMuscle.setVisibility(View.VISIBLE);
			spinnerSMuscle.setVisibility(View.VISIBLE);
			tvSMuscle.setVisibility(View.VISIBLE);
			tvPMuscle.setVisibility(View.VISIBLE);



			System.out.println("static");			//button.setVisibility(View.VISIBLE);
		}

		else if(items[position].equals("Dynamic"))
		{
			setUpInvisLayout();
			spinnerPMuscle.setVisibility(View.VISIBLE);
			spinnerSMuscle.setVisibility(View.VISIBLE);
			tvSMuscle.setVisibility(View.VISIBLE);
			tvPMuscle.setVisibility(View.VISIBLE);
			System.out.println("dynamic");
		}
		else if (items[position].equals("Cardio"))
		{
			System.out.println("cardio");
			setUpInvisLayout();
			spinnerSport.setVisibility(View.VISIBLE);
			tvSport.setVisibility(View.VISIBLE);

			//setContentView(R.layout.activity_add_exercise);

		}


	}

	public void onNothingSelected(AdapterView<?> parent) {

	}
}
