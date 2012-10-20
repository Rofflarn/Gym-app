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

import com.Grupp01.gymapp.Controller.Workout.RegisterDbHandler;
import com.Grupp01.gymapp.View.Workout.ListWorkoutActivity;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by xxxxxxxxxxx
 * @date dd/mm/yyyy
 */
public class TestRegisterDbHandler extends ActivityInstrumentationTestCase2<ListWorkoutActivity>
{	
	private ListWorkoutActivity activity;
	private RegisterDbHandler dbR;
	//Constants
	private static final int MIN = 1;
	private static final int SEC = 10;
	private static final float DISTANCE = 5;
	private static final int WORKOUT_ID = 1;
	private static final int EXERCISE_ID = 1;
	private static final int WEIGTH = 20;
	private static final int REPS = 15;
	
	public TestRegisterDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListWorkoutActivity.class);
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbR = new RegisterDbHandler(activity);
	}
	
	/**Tests to add one cardio set
	 */
	public void testAddCardioSet()
	{
		int nrOfSets = dbR.getNumberOfSets();
		dbR.addCardioSet(SEC, MIN, DISTANCE, WORKOUT_ID, EXERCISE_ID);
		assertEquals("Did not work to add CardioSet", nrOfSets + 1, dbR.getNumberOfSets());
	}
	
	/**Tests to add one dynamic set
	 */
	public void testAddDynamicSet()
	{
		int nrOfSets = dbR.getNumberOfSets();
		dbR.addDynamicSet(WEIGTH, REPS, WORKOUT_ID, EXERCISE_ID);
		assertEquals("Did not work to add DynamicSet", nrOfSets + 1, dbR.getNumberOfSets());
	}
	
	/**Tests to add one static set
	 */
	public void testAddStaticSet()
	{
		int nrOfSets = dbR.getNumberOfSets();
		dbR.addStaticSet(MIN, SEC, WEIGTH, WORKOUT_ID, EXERCISE_ID);
		assertEquals("Did not work to add StaticSet", nrOfSets + 1, dbR.getNumberOfSets());
	}
	
	/**Tests to delete one set
	 */
	public void testDeleteCardioSet()
	{
		int numberOfSets = dbR.getNumberOfSets();
		dbR.deleteCardioSet(EXERCISE_ID);
		assertEquals("Did not work to delete CardioSet", numberOfSets - 1, dbR.getNumberOfSets());
	}
}