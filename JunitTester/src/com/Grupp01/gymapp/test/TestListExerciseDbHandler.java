package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;
import com.Grupp01.gymapp.Controller.Exercise.ListExerciseDbHandler;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;

public class TestListExerciseDbHandler extends ActivityInstrumentationTestCase2<ListExerciseActivity>
{	
	private ListExerciseActivity activity;
	private ListExerciseDbHandler dbL;
	private final int MANY_EXERCISES = 100;
	private final int ZERO_EXERCISES = 0;
	
	public TestListExerciseDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListExerciseActivity.class);	
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbL= new ListExerciseDbHandler(activity);
	}
	
	//Lägger till 100 exercises
	public void testAddManyExercises()
	{
		String name = "n";
		dbL.open();
		int nrOfExercise = dbL.getNumberOfExercises();
		for(int i = 0; i < 100; i++)
		{		
			dbL.addExercise(name);
			name = name + "n";
		}
		assertEquals("Did not work to add exercises", MANY_EXERCISES, dbL.getNumberOfExercises() - nrOfExercise);
		dbL.close();
	}
	
	//Lägger till en övning
	public void testAddExercise()
	{
		dbL.open();
		int nrOfExercise = dbL.getNumberOfExercises();
		dbL.addExercise("test");
		assertEquals("Did not work to add exercise", nrOfExercise + 1, dbL.getNumberOfExercises());
		dbL.close();
	}
	
	//Tar bort alla övningar från databasen	
	public void testDeleteAllExercises()	
	{		
	  dbL.open();
	  int nrOfExercises = dbL.getNumberOfExercises();
	  for(int i = 0; i <= nrOfExercises; i++)
	  {
		  dbL.deleteExercise(i);
	  }
	  assertEquals("Did not work to delete all exercises from database", ZERO_EXERCISES, dbL.getNumberOfExercises());
	  dbL.close();
	}
	
	//Tar bort en övning
	public void testDeleteOneExercise()	
	{
	  dbL.open();
	  int nrOfExercises = dbL.getNumberOfExercises();
	  int exerciseId = dbL.addExercise("test");
	  assertEquals("Did not work to add an exercise", dbL.getNumberOfExercises(), nrOfExercises + 1);
	  dbL.deleteExercise(exerciseId);
	  assertEquals("Did not work to delete exercise from database", nrOfExercises, dbL.getNumberOfExercises());
	  dbL.close();
	}
	
	public void testgetExerciseIdName()
	{
		assertEquals("Not match", dbL.getNumberOfExercises(), dbL.getExerciseIdName().size());
	}
}