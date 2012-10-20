package com.Grupp01.gymapp.test;

import java.util.List;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Controller.Exercise.EditExerciseDbHandler;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;

public class TestEditExerciseDbHandler extends ActivityInstrumentationTestCase2<ListExerciseActivity>
{	
	private ListExerciseActivity activity;
	private EditExerciseDbHandler dbE;
	
	public TestEditExerciseDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListExerciseActivity.class);	
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbE= new EditExerciseDbHandler(activity);
	}
	
	public void testGetExerciseById()
	{
		dbE.open();
		ExerciseData exerciseData = dbE.getExerciseById(1);
		assertEquals("Wrong id", exerciseData.getId(), 1);
		assertNotNull("No name", exerciseData.getName());
		assertNotNull("No type id", exerciseData.getTypeId());
		dbE.close();
	}
	
	public void testGetSports()
	{
		dbE.open();
		List<IdName> allSports = dbE.getSports();
		assertEquals("List not contains rigth nr of sport", allSports.size(), dbE.getNumberOfSports());
		dbE.close();
	}
	
	public void testGetMuscles()
	{
		dbE.open();
		List<IdName> allMuscles = dbE.getMuscles();
		assertEquals("List not contains rigth nr of muscles", allMuscles.size(), dbE.getNumberOfMuscles());
		dbE.close();
	}
	
	public void testEditExercise()
	{
		dbE.open();
		ExerciseData exerciseData = dbE.getExerciseById(1);
		exerciseData.putName("Edit");
		dbE.editExercise(exerciseData);
		assertEquals("Wrong name", dbE.getExerciseById(1).getName().equals("Edit"));
		dbE.close();
	}
}