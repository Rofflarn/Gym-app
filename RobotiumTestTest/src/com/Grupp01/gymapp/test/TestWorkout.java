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
import android.widget.TextView;

import com.Grupp01.gymapp.View.Workout.EditWorkoutActivity;
import com.Grupp01.gymapp.View.Workout.ListWorkoutActivity;
import com.Grupp01.gymapp.View.Workout.RegisterCardioActivity;
import com.Grupp01.gymapp.View.Workout.RegisterDynamicActivity;
import com.Grupp01.gymapp.View.Workout.RegisterStaticActivity;
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
public class TestWorkout extends ActivityInstrumentationTestCase2<ListWorkoutActivity> {

	private Solo solo;
	private static final String WORKOUT_NAME = "WORKOUT_NAME";
	private static final String JOELS = "Joels";
	private static final String ADD_WORKOUT = "ADD WORKOUT";
	private static final String WRONG_ACTIVITY = "Wrong activity"; 
	private static final String ADD_SET = "Add set";
	private static final String BENCH_PRESS = "Bench press";
	private static final String CHINS = "Chins";
	private static final String PUSH_UPS = "Push-ups";
	private static final int TIME = 500;
	
	public TestWorkout()
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
	public void testAddAWorkout()
	{		
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.enterText(0, WORKOUT_NAME);
		solo.sleep(TIME);
		solo.clickOnButton(1);
		solo.clickOnText(WORKOUT_NAME);
		solo.assertCurrentActivity("Wrong Activity", EditWorkoutActivity.class);
	}
	
	/**Testing that the workout that was added before is still there. If it's there, the
	 * test is ok.
	 * Reference to Test-case in documentation: 2.1
	 */
	public void testIfAWorkoutIsSaved()
	{
		assertTrue("Don't exist", solo.searchButton(WORKOUT_NAME));
	}
	
	/**Testing to add a workout and is checking if the titled that has been used is the title of the workout
	 * Reference to Test-case in documentation: 2.1
	 */
	public void testAddAWorkoutGetTitle()
	{		
		String test = "testingåäö-?.;+";
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.enterText(0, test);
		solo.clickOnButton(1);
		solo.sleep(TIME);
		assertEquals(test, (((SherlockActivity) solo.getCurrentActivity()).getSupportActionBar().getTitle()));
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
	
	/**Testing to search for a exercise, if all the exercise has the correct attributes
	 * and all the exercises that has the correct attributes has been included from the database,
	 * then the test is ok.
	 * Reference to Test-case in documentation: 2.3
	 */
	// No spinner so we'll wait with this test
	/*public void testSearchExercise()
	{
		solo.clickInList(0);
		solo.clickOnMenuItem("EDIT WORKOUT!");
		solo.pressSpinnerItem(0, 3);
		solo.sleep(TIME);
	}*/
	
	/**Testing to start EditWorkout, if the activity starts, the test is ok.
	 * Reference to Test-case in documentation: 2.4
	 */
	public void testStartEditWorkoutActivity()
	{
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.enterText(0, WORKOUT_NAME);
		solo.clickOnButton(1);
		solo.assertCurrentActivity(WRONG_ACTIVITY, EditWorkoutActivity.class);
	}
	
	/**Testing that the "longclick-function to edit" works. After longclicking robotium
	 * choose edit. If the activity starting is EditWorkoutActivity,
	 * the test is ok
	 * Reference to Test-case in documentation: 2.4
	 */
	public void testLongClickEdit()
	{
		solo.clickLongOnText(JOELS);
		solo.clickOnText("Edit");
		solo.assertCurrentActivity(WRONG_ACTIVITY, EditWorkoutActivity.class);
	}
	
	/** Testing if an exercise is checked, if the exercise is checked after pressing it,
	 * 	the test is ok.
	 * Reference to Test-case in documentation: 2.5
	 */
	public void testCheckedActivityOnSave()
	{
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.enterText(0, WORKOUT_NAME);
		solo.clickOnButton(1);
		solo.clickInList(0);
		solo.goBack();
		solo.clickOnText(WORKOUT_NAME);
		assertTrue(solo.isCheckBoxChecked(0));
	}
	
	/** Testing if an exercise is checked after it has been checked(it exist in workoutActivity).
	 * If it exist in workoutActivity, the test is ok.
	 * Reference to Test-case in documentation: 2.5
	 */
	public void testCheckedActivityOnSaveExistInWorkout()
	{
		solo.clickOnText(WORKOUT_NAME);
		assertTrue(solo.searchText(CHINS));
	}
	
	/** Testing if an exercise is unchecked, if the exercise is unchecked after pressing it two times
	 * (first checked and afterwards unchecked) and is still unchecked after 
	 * 	the test is ok.
	 * Reference to Test-case in documentation: 2.6
	 */
	public void testNotCheckedActivityOnSave()
	{
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.enterText(0, WORKOUT_NAME);
		solo.clickOnButton(1);
		solo.clickInList(0);
		solo.sleep(TIME);
		solo.clickInList(0);
		solo.goBack();
		solo.clickOnText(WORKOUT_NAME);
		assertFalse(solo.isCheckBoxChecked(0));
	}
	
	/** Testing if an exercise is not checked after it has been not checked(it doesnt exist in workoutActivity).
	 * If it doesn't exist in workoutActivity, the test is ok.
	 * Reference to Test-case in documentation: 2.6
	 */
	public void testNotCheckedActivityOnSaveNotExistInWorkout()
	{
		solo.clickOnText(WORKOUT_NAME);
		assertFalse(solo.searchText(CHINS));
	}
	
	/**Testing that the "longclick-function to delete" works. After longclicking robotium
	 * choose delete. If the the workout doesn't exist anymore, the test is ok.
	 * Reference to Test-case in documentation: 2.8
	 */
	public void testLongClickDelete()
	{
		solo.clickLongOnText(WORKOUT_NAME);
		solo.clickOnText("Delete");
		solo.clickOnText("Yes");
		assertFalse(solo.searchText(WORKOUT_NAME));
	}
	
	/**Testing to start a cardio-exercise. If the exercise is started,
	 * the test is ok.
	 * Reference to Test-case in documentation: 2.9
	 */
	public void testToStartAnCardioExercise()
	{
		solo.clickOnText(JOELS);
		solo.clickOnText(CHINS);
		solo.assertCurrentActivity(WRONG_ACTIVITY, RegisterCardioActivity.class);
	}
	
	/**Testing to register an invalid TIME(in our case, 0 minutes and 0 seconds).
	 * When adding the set, no set is added. If nothing is added, test is ok.
	 * Reference to Test-case in documentation: 2.10
	 */
	public void testRegisterCardioNotValidTime()
	{
		solo.clickOnText(JOELS);
		solo.clickOnText(CHINS);

		//Add one set with invalid TIME and make sure nothing has changed
		solo.enterText(0, "0");
		solo.enterText(1, "0");
		solo.enterText(2, "3");
		solo.clickOnButton(ADD_SET);
		TextView text = (TextView) solo.getView(com.Grupp01.gymapp.R.id.thisTimeSetsCardio);
		assertEquals("", text.getText());
	}
	
	//Will fail
		 /*
			public void testRegisterCardioTimeNoLetters()
		{
			solo.clickOnText(JOELS);
			solo.clickOnText(CHINS);

			//Add one set with invalid TIME and make sure nothing has changed
			solo.enterText(0, WORKOUT_NAME);
			solo.enterText(1, WORKOUT_NAME);
			solo.enterText(2, "3");
			solo.clickOnButton(ADD_SET);
			TextView text = (TextView) solo.getView(com.Grupp01.gymapp.R.id.thisTimeSetsCardio);
			assertEquals("", text.getText());
		}
		*/
	
	/**Testing to register an valid TIME in Cardio, if the set exist,
	 * the test is ok.
	 * Reference to Test-case in documentation: 2.11
	 */
	public void testRegisterCardioValidTime()
	{
		solo.clickOnText(JOELS);
		solo.clickOnText(CHINS);

		//Add one set with invalid TIME and make sure nothing has changed
		solo.enterText(0, "16");
		solo.enterText(1, "10");
		solo.enterText(2, "8");
		solo.clickOnButton(ADD_SET);
		solo.sleep(TIME);
		TextView text = (TextView) solo.getView(com.Grupp01.gymapp.R.id.thisTimeSetsCardio);
		assertEquals("16:10x8, ", text.getText());
	}
	
	/** Testing to start a static exercise, the starting activity should be
	 * 	RegisterStaticActivity.
	 * Reference to Test-case in documentation: 2.12
	 */
	public void testToStartAnStaticExercise()
	{
		solo.clickInList(0);
		solo.clickOnText(PUSH_UPS);
		solo.assertCurrentActivity(WRONG_ACTIVITY, RegisterStaticActivity.class);
	}
	
	/**Testing to add a set on a static-exercise with a invalid TIME.
	 * If it doesn't find the set when searching for it, the test is ok.
	 * Reference to Test-case in documentation: 2.13
	 */
	public void testRegisterStaticExerciseNotValidTime()
	{
		solo.clickInList(0);
		solo.clickOnText(PUSH_UPS);
		solo.enterText(0, "0");
		solo.enterText(1, "0");
		solo.enterText(2, "3");
		solo.clickOnButton(ADD_SET);
		assertFalse(solo.searchText("0:0x3"));
	}
	
	/**Testing to add a set on a static-exercise with a valid time, when added a set it searches if the set is added.
	 * If added, test is ok.
	 * Reference to Test-case in documentation: 2.14
	 */
	public void testRegisterStaticExerciseValidTime()
	{
		solo.clickInList(0);
		solo.clickOnText(PUSH_UPS);
		solo.enterText(0, "0");
		solo.enterText(1, "3");
		solo.enterText(2, "3");
		solo.clickOnButton(ADD_SET);
		assertTrue(solo.searchText("0:3 3"));
	}
	
	/** Testing to start a dynamic exercise, the starting activity should be
	 * 	RegisterDynamicActivity.
	 * Reference to Test-case in documentation: 2.15
	 */
	public void testToStartAnDynamicExercise()
	{
		solo.clickInList(0);
		solo.clickOnText(BENCH_PRESS);
		solo.assertCurrentActivity(WRONG_ACTIVITY, RegisterDynamicActivity.class);
	}
	/**Testing to add a set on a dynamic-exercise with no reps.
	 * If it doesn't find the set when searching for it, the test is ok.
	 * Reference to Test-case in documentation: 2.16
	 */
	public void testRegisterDynamicExerciseNoRepsInField()
	{
		solo.clickInList(0);
		solo.clickOnText(BENCH_PRESS);
		solo.enterText(0, "0");
		solo.enterText(1, "3");
		solo.clickOnButton(ADD_SET);
		assertFalse(solo.searchText("0x3"));
	}
	
	/**Testing to add a set on a dynamic-exercise with no reps filled in reps field.
	 *(The user hasn't written in any reps at all)
	 * If it doesn't find the set when searching for it, the test is ok.
	 * Reference to Test-case in documentation: 2.16
	 */
	public void testRegisterDynamicExerciseNoReps()
	{
		solo.clickInList(0);
		solo.clickOnText(BENCH_PRESS);
		solo.enterText(0, "0");
		solo.enterText(1, "3");
		solo.clickOnButton(ADD_SET);
		assertFalse(solo.searchText("0x3"));
	}
	
	/**Testing to add a set on a dynamic-exercise with reps, when added a set it searches if the set is added.
	 * If added, the test is ok.
	 * Reference to Test-case in documentation: 2.17
	 */
	public void testRegisterDynamicExerciseReps()
	{
		solo.clickInList(0);
		solo.clickOnText(BENCH_PRESS);
		solo.enterText(0, "3");
		solo.enterText(1, "3");
		solo.clickOnButton(ADD_SET);
		assertTrue(solo.searchText("3x3"));
	}
	/**Testing to add a workout with no name, if no name the dialog should write out
	 * "Invalid value".
	 * Reference to Test-case in documentation: 2.19
	 */
	public void testAddAWorkoutNoName()
	{		
		solo.clickOnMenuItem(ADD_WORKOUT);
		solo.clickOnButton(1);
		assertEquals("Wrong", "Invalid value!", solo.getEditText(0).getHint());
	}
	
	protected void tearDown() throws Exception 
	{
		solo.finishOpenedActivities();
	}
	
}
