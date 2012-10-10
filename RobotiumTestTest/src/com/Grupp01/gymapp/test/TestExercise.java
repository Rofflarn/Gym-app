package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestExercise extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public TestExercise() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testViewAllExercises()
	{
		solo.clickOnImageButton(1);
	}
	
	public void testExerciseHomeButton()
	{
		solo.clickOnImageButton(1);
		solo.clickOnActionBarHomeButton();
	}
	
	public void testEnterNameNewExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
	}
	
	public void testAddExerciseMinimalSettingsCardio()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.pressSpinnerItem(0, 0);
		solo.pressSpinnerItem(1, 1);
		solo.clickOnButton(0);
	}
	
	public void testAddExerciseMinimalSettingsStatic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.pressSpinnerItem(0, 1);
		solo.pressSpinnerItem(1, 1);
		solo.pressSpinnerItem(2, 1);
		solo.clickOnButton(0);
	}
	
	public void testAddExerciseMinimalSettingsDynamic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.pressSpinnerItem(0, 2);
		solo.pressSpinnerItem(1, 1);
		solo.pressSpinnerItem(2, 1);
		solo.clickOnButton(0);
	}
	
	public void testAddExerciseMaximalSettingsCardio()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.pressSpinnerItem(0, 0);
		solo.pressSpinnerItem(1, 1);
		solo.enterText(0, "test");
		solo.enterText(1, "test");
		solo.clickOnButton(0);
	}
	
	public void testAddExerciseMaximalSettingsStatic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.pressSpinnerItem(0, 1);
		solo.pressSpinnerItem(1, 1);
		solo.pressSpinnerItem(2, 1);
		solo.enterText(0, "test");
		solo.enterText(1, "test");
		solo.clickOnButton(0);
	}
	
	public void testAddExerciseMaximalSettingsDynamic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.pressSpinnerItem(0, 2);
		solo.pressSpinnerItem(1, 1);
		solo.pressSpinnerItem(2, 1);
		solo.enterText(0, "test");
		solo.enterText(1, "test");
		solo.clickOnButton(0);
	}
	
	public void testDeleteExercise()
	{
		solo.clickOnImageButton(1);
	}
	
	public void testEditExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickInList(0);
		solo.enterText(0, "Edit");
		solo.enterText(1, "Edit");
		solo.clickOnButton(0);
	}
	
	public void testRenameExercise()
	{
		solo.clickOnImageButton(1);
	}
	
	public void testAbortingAddExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.clickOnButton(1);
	}
	
	public void testAbortingEditExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickInList(0);
		solo.clickOnButton(1);
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}