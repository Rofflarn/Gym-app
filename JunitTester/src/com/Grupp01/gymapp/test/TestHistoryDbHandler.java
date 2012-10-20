package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.Controller.History.HistoryDbHandler;
import com.Grupp01.gymapp.View.History.ListHistoryActivity;

public class TestHistoryDbHandler extends ActivityInstrumentationTestCase2<ListHistoryActivity>
{	
	private ListHistoryActivity activity;
	private HistoryDbHandler dbH;
	
	public TestHistoryDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListHistoryActivity.class);	
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbH = new HistoryDbHandler(activity);
	}
	
	public void testGetPerformedWorkouts()
	{
		int nrOfPerformedWorkouts = dbH.getPerformedWorkoutsList().size();
		assertEquals("Performed activitys not match", nrOfPerformedWorkouts, dbH.getNumberOfPerformedWorkouts());
	}
}