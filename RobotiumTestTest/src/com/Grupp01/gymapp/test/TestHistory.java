package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.History.ListHistoryActivity;
import com.Grupp01.gymapp.View.History.ShowSingleHistoryActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestHistory extends ActivityInstrumentationTestCase2<MainActivity> {

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
		solo.clickOnImageButton(2);
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
		solo.clickOnImageButton(0);
		solo.clickOnText("Joels pass");
		//Start it and finish
		solo.clickOnText("Start");
		solo.clickOnText("Färdig");
		//Go back to main menu
		solo.goBack();
		//Select history
		solo.clickOnImageButton(2);
		//Make sure the workout is visible
		assertTrue(solo.searchText("Joels pass"));
	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
