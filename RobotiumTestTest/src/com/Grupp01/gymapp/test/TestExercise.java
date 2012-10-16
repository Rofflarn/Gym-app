package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.Exercise.EditExerciseActivity;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;
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
		solo.assertCurrentActivity("View Exercise activity", ListExerciseActivity.class);
	}
	
	public void testExerciseHomeButton()
	{
		solo.clickOnImageButton(1);
		solo.clickOnActionBarHomeButton();
		solo.assertCurrentActivity("View Main activity", MainActivity.class);
	}
	
	public void testEnterNameNewExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View Add exercise activity", EditExerciseActivity.class);
	}
	
	public void testEnterEmptyNameNewExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "");
		solo.clickOnButton(1);
		assertEquals("Error text should be shown", solo.getEditText(0).getHint().equals("Please enter a name"));
	}
	
	public void testAddExerciseMinimalSettingsCardio()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.sleep(1);
		solo.pressSpinnerItem(0, 0);
		solo.sleep(1);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(1);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View all exercises activity", ListExerciseActivity.class);
	}
	
	public void testAddExerciseMinimalSettingsStatic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.sleep(1);
		solo.pressSpinnerItem(0, 1);
		solo.sleep(1);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(1);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(1);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View all exercises activity", ListExerciseActivity.class);
	}
	
	public void testAddExerciseMinimalSettingsDynamic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.sleep(1);
		solo.pressSpinnerItem(0, 2);
		solo.sleep(1);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(1);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(1);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View all exercises activity", ListExerciseActivity.class);
	}
	
	public void testAddExerciseMaximalSettingsCardio()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.sleep(1);
		solo.pressSpinnerItem(0, 0);
		solo.sleep(1);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(1);
		solo.enterText(0, "test");
		solo.sleep(1);
		solo.enterText(1, "test");
		solo.sleep(1);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View all exercises activity", ListExerciseActivity.class);
	}
	
	public void testAddExerciseMaximalSettingsStatic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.sleep(1);
		solo.pressSpinnerItem(0, 1);
		solo.sleep(1);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(1);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(1);
		solo.enterText(0, "test");
		solo.sleep(1);
		solo.enterText(1, "test");
		solo.sleep(1);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View all exercises activity", ListExerciseActivity.class);
	}
	
	public void testAddExerciseMaximalSettingsDynamic()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.sleep(1);
		solo.pressSpinnerItem(0, 2);
		solo.sleep(1);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(1);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(1);
		solo.enterText(0, "test");
		solo.sleep(1);
		solo.enterText(1, "test");
		solo.sleep(1);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View all exercises activity", ListExerciseActivity.class);
	}
	
	public void testDeleteExercise()
	{
		solo.clickOnImageButton(1);
	}
	
	public void testEditExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickInList(0);
		solo.sleep(1);
		solo.enterText(0, "Edit");
		solo.sleep(1);
		solo.enterText(1, "Edit");
		solo.sleep(1);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View all exercises activity", ListExerciseActivity.class);
	}
	
	public void testRenameExercise()
	{
		solo.clickOnImageButton(1);
	}
	
	public void testAbortingAddExercise()
	{
		solo.clickOnImageButton(1);
		solo.sleep(1);
		solo.clickOnMenuItem("LÄGG TILL ÖVNING!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.clickOnButton(0);
		solo.assertCurrentActivity("View ListExercise Activity", ListExerciseActivity.class);
	}
	
	public void testAbortingEditExercise()
	{
		solo.clickOnImageButton(1);
		solo.sleep(1);
		solo.clickInList(0);
		solo.sleep(1);
		solo.clickOnButton(0);
		solo.assertCurrentActivity("View ListExercise Activity", ListExerciseActivity.class);
	}

	protected void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}

}