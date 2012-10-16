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
import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.Exercise.EditExerciseActivity;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;
import com.jayway.android.robotium.solo.Solo;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by xxxxxx
 * @date dd/mm/yyyy
 */

public class TestExercise extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	public TestExercise() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	public void testViewAllExercises()
	{
		solo.clickOnImageButton(1);
		solo.assertCurrentActivity("View Exercise activity", ListExerciseActivity.class);
	}
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	public void testExerciseHomeButton()
	{
		solo.clickOnImageButton(1);
		solo.clickOnActionBarHomeButton();
		solo.assertCurrentActivity("View Main activity", MainActivity.class);
	}
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	public void testEnterNameNewExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "Exercise Name");
		solo.clickOnButton(1);
		solo.assertCurrentActivity("View Add exercise activity", EditExerciseActivity.class);
	}
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	public void testEnterEmptyNameNewExercise()
	{
		solo.clickOnImageButton(1);
		solo.clickOnMenuItem("ADD EXCERSICE!");
		solo.enterText(0, "");
		solo.clickOnButton(1);
		assertEquals("Error text should be shown", solo.getEditText(0).getHint().equals("Please enter a name"));
	}
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	public void testRenameExercise()
	{
		solo.clickOnImageButton(1);
	}
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
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
	
	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	public void testAbortingEditExercise()
	{
		solo.clickOnImageButton(1);
		solo.sleep(1);
		solo.clickInList(0);
		solo.sleep(1);
		solo.clickOnButton(0);
		solo.assertCurrentActivity("View ListExercise Activity", ListExerciseActivity.class);
	}

	/**Setups the class layout and some instance variables
	 * @param savedInstanceState
	 */
	protected void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}

}