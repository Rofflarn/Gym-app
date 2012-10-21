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
import com.Grupp01.gymapp.View.History.ListHistoryActivity;
import com.jayway.android.robotium.solo.Solo;

/** 
 * @author GivDev
 * @version 1.0
 * @peer reviewed by Robert Blomberg
 * @date 12/10/2012
 * Test history activity
 */

public class TestHistory extends ActivityInstrumentationTestCase2<MainActivity> {
	private static final int HISTORY_BUTTON = 2;
	private static final int WORKOUT_BUTTON = 0;
	private static final String FULL_BODY = "Full body";
	private static final String START = "Start";
	private static final String DONE = "Done";
	private Solo solo;
	public TestHistory() {
		super(MainActivity.class);
	}
	/**Method for a "clean" start on every test case. Runs in the beginning
	 * of every test case.
	 * 
	 */
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
		solo.clickOnImageButton(HISTORY_BUTTON);
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
		solo.clickOnImageButton(WORKOUT_BUTTON);
		solo.clickOnText(FULL_BODY);
		//Start it and finish
		solo.clickOnText(START);
		solo.clickOnText(DONE);
		//Go back to main menu
		solo.goBack();
		//Select history
		solo.clickOnImageButton(HISTORY_BUTTON);
		//Make sure the workout is visible
		assertTrue(solo.searchText(FULL_BODY));
	}
	/**When a test method are done, close it.
	 */
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
