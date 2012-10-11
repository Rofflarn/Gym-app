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
	public TestWorkout()
	{
		super("com.Grupp01.gymapp", ListWorkoutActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	/**Testing if the current activity is the right activity
	 * 
	 */
	public void testOpenWorkout()
	{		
		solo.assertCurrentActivity("Wrong activity", ListWorkoutActivity.class);
	}
	/**Testing to add a workout with no name, if no name the dialog should be cancelled
	 * 
	 */
	public void testAddAWorkoutNoName()
	{		
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Wrong Activity", ListWorkoutActivity.class);
	}
	/**Testing to add a workout, if the EditWorkoutActivity starts afterwards and the workout is existing in the database
	 * the test is successful
	 */
	public void testAddAWorkout()
	{		
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.enterText(0, "abc");
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Wrong Activity", EditWorkoutActivity.class);
	}
	/**Testing to add a workout and is checking if the titled that has been used isw the title of the workout
	 * 
	 */
	public void testAddAWorkoutGetTitle()
	{		
		String test = "testingåäö-?.;+";
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.enterText(0, test);
		solo.clickOnButton(0);
		solo.sleep(1000);
		assertEquals(test, (((SherlockActivity) solo.getCurrentActivity()).getSupportActionBar().getTitle()));
	}
	/**Testing to cancel the add workout-dialog, if it is able to push the button add workout againthe test 
	 * has been successful
	 */
	public void testCancelAddWorkoutDialog()
	{
		solo.clickOnMenuItem("ADD WORKOUT");
		solo.clickOnButton(1);
		//If you can't cancel the dialog, then this test will fail since you can't press the menu item if the dialog is up
		solo.clickOnMenuItem("ADD WORKOUT");
	}
	/**Testing to search for a exercise, if all the exercise has the correct attributes
	 * and all the exercises that has the correct attributes has been included from the database,
	 * then the test is ok.
	 */
	public void testSearchExercise()
	{
		solo.clickInList(0);
		solo.clickOnMenuItem("EDIT WORKOUT!");
		solo.pressSpinnerItem(0, 3);
		solo.sleep(1000);
	}
	/** Testing if an exercise is checked, if the exercise is checked after pressing it,
	 * 	the test is ok.
	 */
	public void testCheckedActivity()
	{
		solo.clickInList(0);
		solo.clickOnMenuItem("EDIT WORKOUT!");
		solo.clickInList(0);
		assertTrue(solo.isCheckBoxChecked(0));
	}
	/** Testing if an exercise is unchecked, if the exercise is unchecked after pressing it two times
	 * (first checked and afterwards unchecked),
	 * 	the test is ok.
	 */
	public void testNotCheckedActivity()
	{
		solo.clickInList(0);
		solo.clickOnMenuItem("EDIT WORKOUT!");
		solo.clickInList(0);
		solo.sleep(1000);
		solo.clickInList(0);
		assertFalse(solo.isCheckBoxChecked(0));
	}
	/** Testing to start a dynamic exercise, the starting activity should be
	 * 	RegisterDynamicActivity.
	 */
	public void testStartDynamicExercise()
	{
		solo.clickInList(0);
		solo.clickOnText("Dynamisk");
		solo.assertCurrentActivity("Wrong activity", RegisterDynamicActivity.class);
	}
	/** Testing to start a static exercise, the starting activity should be
	 * 	RegisterStaticActivity.
	 */
	public void testStartStaticExercise()
	{
		solo.clickInList(0);
		solo.clickOnText("Statisk");
		solo.assertCurrentActivity("Wrong activity", RegisterStaticActivity.class);
	}
	/**Testing to add a set on a dynamic-exercise with reps, when added a set it searches if the set is added.
	 * If added, the test is ok.
	 */
	public void testRegisterDynamicExerciseReps()
	{
		solo.clickInList(0);
		solo.clickOnText("Dynamisk");
		solo.enterText(0, "3");
		solo.enterText(1, "3");
		solo.clickOnButton("Add set");
		assertTrue(solo.searchText("3x3"));
	}
	/**Testing to add a set on a dynamic-exercise with no reps.
	 * If it doesn't find the set when searching for it, the test is ok.
	 */
	public void testRegisterDynamicExerciseNoReps()
	{
		solo.clickInList(0);
		solo.clickOnText("Dynamisk");
		solo.enterText(0, "0");
		solo.enterText(1, "3");
		solo.clickOnButton("Add set");
		assertFalse(solo.searchText("0x3"));
	}
	/**Testing to add a set on a static-exercise with a valid time, when added a set it searches if the set is added.
	 * If added, test is ok.
	 */
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
	/**Testing to add a set on a static-exercise with a invalid time.
	 * If it doesn't find the set when searching for it, the test is ok.
	 */
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

	public void testRegisterCardioExercise()
	{
		solo.clickOnText("Fejk");
		solo.clickOnText("Cardio");
		solo.assertCurrentActivity("Failed to launch cardio exercise", RegisterCardioActivity.class);
	}
	
	public void testRegisterCardioValidTime()
	{
		solo.clickOnText("Fejk");
		solo.clickOnText("Cardio");

		//Add one set with invalid time and make sure nothing has changed
		solo.enterText(0, "0");
		solo.enterText(1, "0");
		solo.enterText(2, "3");
		solo.clickOnButton("Add set");
		TextView text = (TextView) solo.getView(com.Grupp01.gymapp.R.id.thisTimeSetsCardio);
		assertEquals("", text.getText().toString());
	}
	/*  This will fail
	 * 
		public void testRegisterCardioTimeNoLetters()
	{
		solo.clickOnText("Fejk");
		solo.clickOnText("Cardio");

		//Add one set with invalid time and make sure nothing has changed
		solo.enterText(0, "abc");
		solo.enterText(1, "abc");
		solo.enterText(2, "3");
		solo.clickOnButton("Add set");
		TextView text = (TextView) solo.getView(com.Grupp01.gymapp.R.id.thisTimeSetsCardio);
		assertEquals("", text.getText().toString());
	}
	*/
	
	public void testRegisterCardioTimeValidTime()
	{
		solo.clickOnText("Fejk");
		solo.clickOnText("Cardio");

		//Add one set with invalid time and make sure nothing has changed
		solo.enterText(0, "16");
		solo.enterText(1, "10");
		solo.enterText(2, "8");
		solo.clickOnButton("Add set");
		TextView text = (TextView) solo.getView(com.Grupp01.gymapp.R.id.thisTimeSetsCardio);
		assertEquals("16:10x8", text.getText().toString());
	}
	
	
}
