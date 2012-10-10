package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.Workout.EditWorkoutActivity;
import com.Grupp01.gymapp.View.Workout.ListWorkoutActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestOpenWorkout extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public TestOpenWorkout() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}

	public void testOpenWorkout()
	{		
		solo.clickOnImageButton(0);
		solo.assertCurrentActivity("Wrong activity", ListWorkoutActivity.class);
	}
	public void testAddAWorkoutNoName()
	{		
		solo.clickOnImageButton(0);
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Wrong Activity", ListWorkoutActivity.class);
	}
	public void testAddAWorkout()
	{		
		solo.clickOnImageButton(0);
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.enterText(0, "abc");
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Wrong Activity", EditWorkoutActivity.class);
	}
	public void testAddAWorkoutGetTitle()
	{		
		String test = "testingåäö-?.;+";
		solo.clickOnImageButton(0);
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.enterText(0, test);
		solo.clickOnButton(0);
		solo.sleep(1000);
		assertEquals(test, (((SherlockActivity) solo.getCurrentActivity()).getSupportActionBar().getTitle()));
	}
	public void testCancelAddWorkoutDialog()
	{
		solo.clickOnImageButton(0);
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.clickOnButton(1);
		//If you can't cancel the dialog, then this test will fail since you cant press the menu item if the dialog is up
		solo.clickOnMenuItem("ADD WORKOUT");
	}
	public void testSearchExercise()
	{
		solo.clickOnImage(0);
		
	}
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
