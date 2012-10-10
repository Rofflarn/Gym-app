/*This file is part of Gymapp.
 *
 *   Gymapp is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Gymapp is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *  along with Gymapp.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.Grupp01.gymapp.View.Exercise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.Controller.Exercise.EditExerciseDbHandler;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 04/10/12
 *
 * This class is made exclusively for initiating the part of the GUI that gives the user 
 * a opportunity to add and change exercises
 * 
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Exercise</i>
 * <p> Subpackage</p> 
 *
 */
public class EditExerciseAcitivity extends SherlockActivity implements AdapterView.OnItemSelectedListener {
	private Spinner spinnerType, spinnerPMuscle, spinnerSMuscle, spinnerSport;
	private String currentView;
	private int exerciseId;
	private ExerciseData exercise;
	private ArrayList<String> listMuscles, listSports, listTrainingType;
	/**
	 * Instantiates the class with necessary method calls, setting up the correct layout
	 * and receiving the intent that started this activity
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exercise);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		exerciseId = getIntent().getIntExtra(ListExerciseActivity.EXTRA_EXERCISE_NAME, 0);
		getExerciseData();
		

		listTrainingType = getExerciseTypesFromDb();//get String-array from strings.xml
		initSpinnerType(0); //initialize spinner with listener and set spinner to static
		
	}
	/**
	 * Sets up the menubar, note the use of actionbarsherlock, making it possible of using
	 * a menubar for APIs lower than 11
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.activity_add_exercise, menu);
		return true;
	}

	/**
	 * This method initializes the spinners, populating them with items and adds listener
	 * @param position used for changing the default value due to the previous selection in a spinner
	 */
	public void initSpinnerType(int position)
	{	
		spinnerType = (Spinner) findViewById(R.id.spinner_type_of_training); //Retrieves the view from .xml-file
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTrainingType);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerType.setAdapter(adapter); //Sets the adapter to the spinner

		spinnerType = (Spinner) findViewById(R.id.spinner_type_of_training);
		spinnerType.setSelection(position); //Sets the spinner default value to selected value
		spinnerType.setOnItemSelectedListener(this); //Adds listener to spinner spinnterType
		setTexts();
		
	}
	public void initSpinnerDynamicStatic()
	{
		listMuscles = getMusclesFromDb(); 
		
		spinnerPMuscle = (Spinner) findViewById(R.id.spinner_primary_muscle); //Retrieves the view from .xml-file
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listMuscles);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerPMuscle.setAdapter(adapter); //Sets the adapter to the spinner
		
		spinnerSMuscle = (Spinner) findViewById(R.id.spinner_secondary_muscle);
		spinnerSMuscle.setAdapter(adapter);
	}
	public void initSpinnerCardio()
	{
		listSports = getSportsFromDb();
		spinnerSport = (Spinner) findViewById(R.id.spinner_sport); //Retrieves the view from .xml-file
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSports);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSport.setAdapter(adapter); //Sets the adapter to the spinner
	}
	/**
	 * Callback method to be invoked when an item in this view has been selected
	 */
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if(!listTrainingType.get(position).equals(currentView)) //conditional if to prevent infinite loop
		{
			
			if(listTrainingType.get(position).equals("Static"))
			{
				setContentView(R.layout.add_exercise_static);	//Switches the layout to the selected one
				initSpinnerType(position);	//After switching view, adds listener to spinner again
				initSpinnerDynamicStatic();
				currentView = listTrainingType.get(position);	//Sets currentView to prevent infinite loop
			}

			else if(listTrainingType.get(position).equals("Dynamic"))
			{
				setContentView(R.layout.add_exercise_static);	
				initSpinnerType(position);
				initSpinnerDynamicStatic();
				currentView = listTrainingType.get(position);
			}
			else if (listTrainingType.get(position).equals("Cardio"))
			{
				setContentView(R.layout.activity_add_exercise);	
				initSpinnerType(position);
				initSpinnerCardio();
				currentView=listTrainingType.get(position);
			}
		}
	}
	
	
	public void setTexts()
	{
		setTitle(exercise.getName());
		
		if((exercise.getNote() != null) && (exercise.getDesc() != null))
		{
			EditText comment = (EditText) findViewById(R.id.edit_comment);
			EditText desc = (EditText) findViewById(R.id.edit_description);
			comment.setText(exercise.getNote());
			desc.setText(exercise.getDesc());
		}
	}
	/**
	 * Callback method to be invoked when the selection disappears from this view.
	 */
	public void onNothingSelected(AdapterView<?> parent)
	{
	}
	/**
	 * <p>Callback method to be invoked when the </p><i>done-button</i><p> has been clicked, making the activity
	 * send the new exercise to the database.</p>
	 */
	public void done(View view)
	{
		//Send data to database
	}
	/**
	 * <p>Callback method to be invoked when the </p><i>cancel-button</i><p> has been clicked, making the activity
	 * return the the previous</p><i> Exercise.java</i>
	 */
	public void cancel(View view)
	{
		finish();
	}
	private ArrayList<String> getMusclesFromDb()
	{
		EditExerciseDbHandler get = new EditExerciseDbHandler(this);
		get.open();
		List<IdName>list = get.getMuscles();
		get.close();
		ArrayList<String> getIdName = new ArrayList<String>();
		for (IdName idname : list)
		{
			getIdName.add(idname.getName());
		}
		return getIdName;
	}
	private ArrayList<String> getSportsFromDb()
	{
		EditExerciseDbHandler get = new EditExerciseDbHandler(this);
		get.open();
		List<IdName>list = get.getSports();
		get.close();
		ArrayList<String> getIdName = new ArrayList<String>();
		for (IdName idname : list)
		{
			getIdName.add(idname.getName());
		}
		return getIdName;
	}
	
	private ArrayList<String> getExerciseTypesFromDb()
	{
		EditExerciseDbHandler get = new EditExerciseDbHandler(this);
		get.open();
		List<IdName>list = get.getExerciseTypes();
		get.close();
		ArrayList<String> getIdName = new ArrayList<String>();
		for (IdName idname : list)
		{
			getIdName.add(idname.getName());
		}
		return getIdName;
	}
	
	
	/*private String[] ListIdName2StringArr(List<IdName> list)
	{
		ArrayList<String> getIdName = new ArrayList<String>();
		for (IdName idname : list)
		{
			getIdName.add(idname.getName());
		}
		return (String[]) getIdName.toArray(new String[getIdName.size()]);
	}*/
	
	private void getExerciseData()
	{
		EditExerciseDbHandler get = new EditExerciseDbHandler(this);
		get.open();
		exercise = get.getExerciseById(exerciseId);
		get.close();
	}
}
