package com.Grupp01.gymapp.test;

import java.util.List;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.Controller.Workout.RegisterDbHandler;
import com.Grupp01.gymapp.Controller.Workout.SetsData;
import com.Grupp01.gymapp.View.Workout.ListWorkoutActivity;

public class TestRegisterDbHandler extends ActivityInstrumentationTestCase2<ListWorkoutActivity>
{	
	private ListWorkoutActivity activity;
	private RegisterDbHandler dbR;
	private static final int MIN = 1;
	private static final int SEC = 10;
	private static final float DISTANCE = 5;
	private static final int WORKOUT_ID = 1;
	private static final int EXERCISE_ID = 1;
	private static final int WEIGTH = 20;
	private static final int REPS = 15;
	private static final int TYPE_ID = 1;
	
	public TestRegisterDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListWorkoutActivity.class);	
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbR = new RegisterDbHandler(activity);
	}
	
	public void testAddCardioSet()
	{
		int nrOfSets = dbR.getNumberOfSets();
		dbR.addCardioSet(SEC, MIN, DISTANCE, WORKOUT_ID, EXERCISE_ID);
		assertEquals("Did not work to add CardioSet", nrOfSets + 1, dbR.getNumberOfSets());
	}
	
	public void testAddDynamicSet()
	{
		int nrOfSets = dbR.getNumberOfSets();
		dbR.addDynamicSet(WEIGTH, REPS, WORKOUT_ID, EXERCISE_ID);
		assertEquals("Did not work to add DynamicSet", nrOfSets + 1, dbR.getNumberOfSets());
	}
	
	public void testAddStaticSet()
	{
		int nrOfSets = dbR.getNumberOfSets();
		dbR.addStaticSet(MIN, SEC, WEIGTH, WORKOUT_ID, EXERCISE_ID);
		assertEquals("Did not work to add StaticSet", nrOfSets + 1, dbR.getNumberOfSets());
	}
	
	public void testDeleteCardioSet()
	{
		int numberOfSets = dbR.getNumberOfSets();
		dbR.deleteCardioSet(EXERCISE_ID);
		assertEquals("Did not work to delete CardioSet", numberOfSets - 1, dbR.getNumberOfSets());
	}
}