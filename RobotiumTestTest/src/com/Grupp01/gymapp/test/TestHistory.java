package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.History.ListHistoryActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestHistory extends ActivityInstrumentationTestCase2<MainActivity> {
	private static final int WORKOUT_BUTTON = 0;
	private static final String JOELS_PASS = "Joels pass";
	private static final String START = "Start";
	private static final String DONE = "Färdig";
	private Solo solo;
	public TestHistory() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}

	/**
	 * WIll only try to open the history activity
	 */
	public void testOpenHistory()
	{		
		//Click on button in main menu
		solo.clickOnImageButton(WORKOUT_BUTTON);
		//Asert that the right activity has started
		solo.assertCurrentActivity("Wrong activity", ListHistoryActivity.class);
	}
	
	/**
	 * Will start and finish a new workout and then check to see if the workout is
	 * visible in the history list.
	 */
	
	public void testHistoryExists()
	{
		//Selet workout
		solo.clickOnImageButton(WORKOUT_BUTTON);
		solo.clickOnText(JOELS_PASS);
		//Start it and finish
		solo.clickOnText(START);
		solo.clickOnText(DONE);
		//Go back to main menu
		solo.goBack();
		//Select history
		solo.clickOnImageButton(WORKOUT_BUTTON);
		//Make sure the workout is visible
		assertTrue(solo.searchText(JOELS_PASS));
	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
