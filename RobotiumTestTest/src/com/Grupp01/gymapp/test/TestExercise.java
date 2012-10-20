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
 *	Copyright © 2012 GivDev
 *
 */
package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.Exercise.EditExerciseActivity;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;
import com.jayway.android.robotium.solo.Solo;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Robert Blomberg
 * @date dd/mm/yyyy
 */

public class TestExercise extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	private static final int SLEEP = 1000;
	private static final String EXERCISE_NAME = "Exercise Name";
	private static final String ADD_EXERCISE = "ADD EXERCISE!";
	private static final String VIEW_EXERCISEACTIVITY = "Should view exercise activity";
	private static final String NOTES = "Notes";
	private static final String DESC = "Description";
	
	public TestExercise() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	/**Testing to open the viewExercise activity
	 * Reference to Test-case in documentation: 3.1
	 */
	public void testViewAllExercises()
	{
		//Clicks on the exercise button and verifies that the ListExerciseActivity is shown
		solo.clickOnImageButton(1);
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to open viewExercise and the push the homebutton
	 * Reference to Test-case in documentation: 3.2
	 */
	public void testAAAExerciseHomeButton()
	{
		////Clicks on the exercise button and then the homebutton in viewExercise Activity
		//and verifies that the Main activity is shown
		solo.clickOnImageButton(1);
		solo.sleep(SLEEP);
		solo.clickOnActionBarHomeButton();
		solo.sleep(SLEEP);
		solo.sleep(SLEEP);
		solo.assertCurrentActivity("View Main activity", MainActivity.class);
	}
	
	/**Testing to enter a name to a new exercise
	 * Reference to Test-case in documentation: 3.3
	 */
	public void testEnterNameNewExercise()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//Clicks add exercise button from menu and enter exercise name folowd by add
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		//checks that the editExercise activity is shown
		solo.assertCurrentActivity("View Add exercise activity", EditExerciseActivity.class);
	}
	
	/**Testing to add a empty name to a new exercise
	 * Reference to Test-case in documentation: 3.4
	 */
	public void testEnterEmptyNameNewExercise()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//Clicks add exercise button from menu and enter a empty exercise name folowd by add
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, "");
		solo.clickOnButton(1);
		//Checks that an error message is displayed in the dialog textbox
		assertTrue("Error, text should be shown", solo.getEditText(0).getHint().equals("Please enter a name"));
	}
	
	/**Testing to add cardio exercise whith minimal settings
	 * Reference to Test-case in documentation: 3.5
	 */
	public void testAddExerciseMinimalSettingsCardio()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//adds a new cardio exercise whitout description or notes
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(0, 0);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(SLEEP);
		solo.clickOnButton(1);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to add static exercise whith minimal settings
	 * Reference to Test-case in documentation: 3.5
	 */
	public void testAddExerciseMinimalSettingsStatic()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//adds a new static exercise whitout description or notes
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(0, 2);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(SLEEP);
		solo.clickOnButton(1);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to add dynamic exercise whith minimal settings
	 * Reference to Test-case in documentation: 3.5
	 */
	public void testAddExerciseMinimalSettingsDynamic()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//adds a new dynamic exercise whitout description or notes
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(0, 1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(SLEEP);
		solo.clickOnButton(1);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to add cardio exercise whith maximal settings
	 * Reference to Test-case in documentation: 3.6
	 */
	public void testAddExerciseMaximalSettingsCardio()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//adds a new cardio exercise whit description and notes
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(0, 0);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(SLEEP);
		solo.enterText(0, DESC);
		solo.sleep(SLEEP);
		solo.enterText(1, NOTES);
		solo.sleep(SLEEP);
		solo.clickOnButton(1);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to add static exercise whith maximal settings
	 * Reference to Test-case in documentation: 3.6
	 */
	public void testAddExerciseMaximalSettingsStatic()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//adds a new static exercise whit description and notes
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(0, 2);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(SLEEP);
		solo.enterText(0, DESC);
		solo.sleep(SLEEP);
		solo.enterText(1, NOTES);
		solo.sleep(SLEEP);
		solo.clickOnButton(1);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to add dynamic exercise whith maximal settings
	 * Reference to Test-case in documentation: 3.6
	 */
	public void testAddExerciseMaximalSettingsDynamic()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//adds a new dynamic exercise whit description and notes
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(0, 1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(1, 1);
		solo.sleep(SLEEP);
		solo.pressSpinnerItem(2, 1);
		solo.sleep(SLEEP);
		solo.enterText(0, DESC);
		solo.sleep(SLEEP);
		solo.enterText(1, NOTES);
		solo.sleep(SLEEP);
		solo.clickOnButton(1);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to delete an exercise
	 * Reference to Test-case in documentation: 3.8
	 */
	public void testDeleteExercise()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		solo.clickLongInList(0);
		solo.clickOnText("Delete");
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to edit an exsisting exercise
	 * Reference to Test-case in documentation: 3.7
	 */
	public void testEditExercise()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		//Clicks on an exsisting exercise
		solo.clickInList(0);
		solo.sleep(SLEEP);
		//Enters the text edit in both description and notes
		solo.enterText(0, DESC);
		solo.sleep(SLEEP);
		solo.enterText(1, NOTES);
		solo.sleep(SLEEP);
		//Saves the changes
		solo.clickOnButton(1);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to cancel when adding a new exercise
	 * Reference to Test-case in documentation: 3.9
	 */
	public void testAbortingAddExercise()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		solo.sleep(SLEEP);
		//Adds exercise with name exercise name
		solo.clickOnMenuItem(ADD_EXERCISE);
		solo.enterText(0, EXERCISE_NAME);
		solo.clickOnButton(1);
		solo.sleep(SLEEP);
		//Pushs cancel
		solo.clickOnButton(0);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}
	
	/**Testing to cancel when editing a existing exercise
	 * Reference to Test-case in documentation: 3.11
	 */
	public void testAbortingEditExercise()
	{
		//Clicks exercise button
		solo.clickOnImageButton(1);
		solo.sleep(SLEEP);
		//Clicks exsisting exercise
		solo.clickInList(0);
		solo.sleep(SLEEP);
		//Clicks cancel
		solo.clickOnButton(0);
		//checks that listExercise activity is shown
		solo.assertCurrentActivity(VIEW_EXERCISEACTIVITY, ListExerciseActivity.class);
	}

	protected void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}

}