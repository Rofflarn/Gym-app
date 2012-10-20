/*Copyright © 2012 GivDev
 * 
 * This file is part of Gymapp.
 *
 *   Gymapp is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Gymapp is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *  along with Gymapp.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.Grupp01.gymapp.test;



import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.View.Workout.EditWorkoutActivity;
import com.Grupp01.gymapp.View.Workout.ListWorkoutActivity;
import com.Grupp01.gymapp.View.Workout.WorkoutActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.jayway.android.robotium.solo.Solo;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 11/10/12
 *
 * Test class for workout
 *  
 */
public class TestWorkoutCreateRemove extends ActivityInstrumentationTestCase2<ListWorkoutActivity> {

	 private Solo solo;
	 public static final String WORKOUT_NAME = "Workout name";
	 public static final String FULL_BODY = "Full body";
	 public static final String ADD_WORKOUT = "ADD WORKOUT";
	 public static final String WRONG_ACTIVITY = "Wrong activity"; 
	 public static final String ADD_SET = "Add set";
	 public static final String BENCH_PRESS = "Bench press";
	 public static final String DIPS = "Dips";
	 public static final String PUSH_UPS = "Push-ups";
	 public static final String EDIT_WORKOUT = "EDIT WORKOUT!";
	 public static final String SAVE = "Save";
	 public static final int TIME = 1500;
	
	public TestWorkoutCreateRemove()
	{
		super(ListWorkoutActivity.class);
	}

	 protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	/**Testing to open Workout, if the current activity is workout the test
	 * is ok.
	 * Reference to Test-case in documentation: 1.2
	 */
	public void testOpenWorkout()
	{		
		solo.assertCurrentActivity(WRONG_ACTIVITY, ListWorkoutActivity.class);
	}
	
	/**Testing to add a workout, if the EditWorkoutActivity starts afterwards and the workout is existing in the database
	 * the test is ok.
	 * Reference to Test-case in documentation: 2.1
	 */
	public void testAAAddAWorkout()
	{	
		//Click on the button "Add workout"
		solo.clickOnMenuItem(ADD_WORKOUT);
		//Write in a name of the workout
		solo.enterText(0, WORKOUT_NAME);
		//Click on "add workout" in the dialog
		solo.clickOnButton(1);
		//Since Robotium acts to fast we need to "sleep"
		solo.sleep(TIME);
		//Save workout
		solo.clickOnText(SAVE);
		solo.sleep(TIME);
		//Scroll down to the bottom to the newly created workout
		solo.scrollToBottom();
		//Get into the workout that was newly created
		solo.clickOnText(WORKOUT_NAME);
		//Check if the current activity is the WorkoutActivity
		solo.assertCurrentActivity("Wrong Activity", WorkoutActivity.class);
	}
	
	/**Testing that the workout that was added before is still there. If it's there, the
	 * test is ok.
	 * Reference to Test-case in documentation: 2.1
	 */
	public void testIfAWorkoutIsSaved()
	{
		//Check if the workout was saved(Since a reboot has been done
		solo.scrollToBottom();
		//Search for the newly added workout
		assertTrue("Don't exist", solo.searchText(WORKOUT_NAME));
	}
	
	/**Testing to add a workout and is checking if the titled that has been used is the title of the workout
	 * Reference to Test-case in documentation: 2.1
	 */
	public void testAddAWorkoutGetTitle()
	{		
		//Create a workout(using this name to check if all the characters are available)
		String test = "testingåäö-?.;+";
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.enterText(0, test);
		solo.clickOnButton(1);
		solo.sleep(TIME);
		//See if the current title is the workout name
		assertEquals("Edit " + test, (((SherlockActivity) solo.getCurrentActivity()).getSupportActionBar().getTitle()));
	}
	
	/**Testing to cancel the add workout-dialog, if it is able to push the button add workout again,
	 * the test is ok.
	 * Reference to Test-case in documentation: 2.2
	 */
	public void testCancelAddWorkoutDialog()
	{
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.clickOnButton(0);
		//If you can't cancel the dialog, then this test will fail since you can't press the menu item if the dialog is up
		solo.clickOnMenuItem(ADD_WORKOUT);
	}
	
	/**Testing to start EditWorkout, if the activity starts, the test is ok.
	 * Reference to Test-case in documentation: 2.4
	 */
	public void testStartEditWorkoutActivity()
	{
		solo.clickOnText(FULL_BODY);
		//Test to get into the edit workout activity from the workout activity
		solo.clickOnText(EDIT_WORKOUT);
		//Is it the right activity? If wrong, "Wrong activity" will be writen in
		//the test console
		solo.assertCurrentActivity(WRONG_ACTIVITY, EditWorkoutActivity.class);
	}
	
	/**Testing that the "longclick-function to edit" works. After longclicking robotium
	 * choose edit. If the activity starting is EditWorkoutActivity,
	 * the test is ok
	 * Reference to Test-case in documentation: 2.4
	 */
	public void testToLongClickEdit()
	{
		//Test to longclick
		solo.clickLongOnText(FULL_BODY);
		//Click on edit
		solo.clickOnText("Edit");
		solo.assertCurrentActivity(WRONG_ACTIVITY, EditWorkoutActivity.class);
	}
	
	/** Testing if an exercise is checked, if the exercise is checked after pressing it,
	 * 	the test is ok.
	 * Reference to Test-case in documentation: 2.5
	 */
	public void testAACheckedActivityOnSave()
	{
		//Scroll to bottom to save time
		solo.scrollToBottom();
		solo.clickOnText(WORKOUT_NAME);
		solo.clickOnText(EDIT_WORKOUT);
		//Click on a exercise in edit workout, now it is marked as checked
		solo.clickOnText(DIPS);
		//Save the workout
		solo.clickOnText(SAVE);
		//Go into the workout and see if it is saved
		solo.clickOnText(EDIT_WORKOUT);
		//Is the checkbox of the exercise that was clicked checked?
		assertTrue(solo.isCheckBoxChecked(1));
	}
	
	/** Testing if an exercise is checked after it has been checked(it exist in workoutActivity).
	 * If it exist in workoutActivity, the test is ok.
	 * Reference to Test-case in documentation: 2.5
	 */
	public void testCheckedActivityOnSaveExistInWorkout()
	{
		solo.scrollToBottom();
		//Go into workout activity
		solo.clickOnText(WORKOUT_NAME);
		//Check if the exercise that was clicked is still checked
		//after reboot
		assertTrue(solo.searchText(DIPS));
	}
	
	/** Testing if an exercise is unchecked, if the exercise is unchecked after pressing it two times
	 * (first checked and afterwards unchecked) and is still unchecked after 
	 * 	the test is ok.
	 * Reference to Test-case in documentation: 2.6
	 */
	public void testNotCheckedActivityOnSave()
	{
		solo.scrollToBottom();
		solo.clickOnText(WORKOUT_NAME);
		solo.clickOnText(EDIT_WORKOUT);
		//Press the exercise so it is unchecked
		solo.clickOnText(DIPS);
		//Click on the save-button
		solo.clickOnText(SAVE);
		solo.clickOnText(EDIT_WORKOUT);
		//Check if the exercise is not checked
		assertFalse(solo.isCheckBoxChecked(1));
	}
	
	/** Testing if an exercise is not checked after it has been not checked(it doesnt exist in workoutActivity).
	 * If it doesn't exist in workoutActivity, the test is ok.
	 * Reference to Test-case in documentation: 2.6
	 */
	public void testNotCheckedActivityOnSaveNotExistInWorkout()
	{
		solo.scrollToBottom();
		solo.clickOnText(WORKOUT_NAME);
		//Check that the exercise isn't listed in workout
		assertFalse(solo.searchText(DIPS));
	}
	
	/**Testing that the "longclick-function to delete" works. After longclicking robotium
	 * choose delete. If the the workout doesn't exist anymore, the test is ok.
	 * Reference to Test-case in documentation: 2.8
	 */
	public void testToLongToClickDelete()
	{
		solo.scrollToBottom();
		//Longclick a workout
		solo.clickLongOnText(WORKOUT_NAME);
		//Click on delete
		solo.clickOnText("Delete");
		//Press the yes-button to delete the workout
		solo.clickOnText("Yes");
		//Search if the workout still exist
		assertFalse(solo.searchText(WORKOUT_NAME));
	}
	
	/**Testing to add a workout with no name, if no name the dialog should write out
	 * "Invalid value".
	 * Reference to Test-case in documentation: 2.19
	 */
	public void testAddAWorkoutNoName()
	{
		solo.clickOnMenuItem(ADD_WORKOUT);
		//Click on the "add workout"-button without writing a workout name
		solo.clickOnButton(1);
		//Check if the EditText-Field writes out "Invalid value!"
		assertEquals("Invalid value!", solo.getEditText(0).getHint());
	}
	
	protected void tearDown() throws Exception 
	{
		solo.finishOpenedActivities();
	}
	
}
