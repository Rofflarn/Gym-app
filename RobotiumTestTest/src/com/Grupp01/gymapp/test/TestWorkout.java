package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.View.Workout.EditWorkoutActivity;
import com.Grupp01.gymapp.View.Workout.ListWorkoutActivity;
import com.Grupp01.gymapp.View.Workout.RegisterDynamicActivity;
import com.Grupp01.gymapp.View.Workout.RegisterStaticActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestWorkout extends ActivityInstrumentationTestCase2<ListWorkoutActivity> {

	private Solo solo;
	public TestWorkout() {
		super("com.Grupp01.gymapp", ListWorkoutActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}

	public void testOpenWorkout()
	{		
		solo.assertCurrentActivity("Wrong activity", ListWorkoutActivity.class);
	}
	public void testAddAWorkoutNoName()
	{		
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Wrong Activity", ListWorkoutActivity.class);
	}
	public void testAddAWorkout()
	{		
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.enterText(0, "abc");
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Wrong Activity", EditWorkoutActivity.class);
	}
	public void testAddAWorkoutGetTitle()
	{		
		String test = "testingåäö-?.;+";
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.enterText(0, test);
		solo.clickOnButton(0);
		solo.sleep(1000);
		assertEquals(test, (((SherlockActivity) solo.getCurrentActivity()).getSupportActionBar().getTitle()));
	}
	public void testCancelAddWorkoutDialog()
	{
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.clickOnButton(1);
		//If you can't cancel the dialog, then this test will fail since you cant press the menu item if the dialog is up
		solo.clickOnMenuItem("ADD WORKOUT");
	}
	public void testSearchExercise()
	{
		solo.clickInList(0);
		solo.clickOnMenuItem("EDIT WORKOUT!");
		solo.pressSpinnerItem(0, 3);
		solo.sleep(1000);
	}
	public void testCheckedActivity()
	{
		solo.clickInList(0);
		solo.clickOnMenuItem("EDIT WORKOUT!");
		solo.clickInList(0);
		assertTrue(solo.isCheckBoxChecked(0));
	}
	public void testNotCheckedActivity()
	{
		solo.clickInList(0);
		solo.clickOnMenuItem("EDIT WORKOUT!");
		solo.clickInList(0);
		solo.sleep(1000);
		solo.clickInList(0);
		assertFalse(solo.isCheckBoxChecked(0));
	}
	public void testStartDynamicExercise()
	{
		solo.clickInList(0);
		solo.clickOnText("Dynamisk");
		solo.assertCurrentActivity("Wrong activity", RegisterDynamicActivity.class);
	}
	public void testStartStaticExercise()
	{
		solo.clickInList(0);
		solo.clickOnText("Statisk");
		solo.assertCurrentActivity("Wrong activity", RegisterStaticActivity.class);
	}
	public void testRegisterDynamicExerciseReps()
	{
		solo.clickInList(0);
		solo.clickOnText("Dynamisk");
		solo.enterText(0, "3");
		solo.enterText(1, "3");
		solo.clickOnButton("Add set");
		assertTrue(solo.searchText("3x3"));
	}
	public void testRegisterDynamicExerciseNoReps()
	{
		solo.clickInList(0);
		solo.clickOnText("Dynamisk");
		solo.enterText(0, "0");
		solo.enterText(1, "3");
		solo.clickOnButton("Add set");
		assertFalse(solo.searchText("0x3"));
	}
	public void testRegisterStaticExerciseValidTime()
	{
		solo.clickInList(0);
		solo.clickOnText("Statisk");
		solo.enterText(0, "0");
		solo.enterText(1, "3");
		solo.enterText(2, "3");
		solo.clickOnButton("Add set");
		assertTrue(solo.searchText("0:3x3"));
	}
	public void testRegisterStaticExerciseNotValidTime()
	{
		solo.clickInList(0);
		solo.clickOnText("Statisk");
		solo.enterText(0, "0");
		solo.enterText(1, "0");
		solo.enterText(2, "3");
		solo.clickOnButton("Add set");
		assertFalse(solo.searchText("0:0x3"));
	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
