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

import com.Grupp01.gymapp.Controller.History.HistoryDbHandler;
import com.Grupp01.gymapp.View.History.ListHistoryActivity;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by xxxxxxxxxxx
 * @date dd/mm/yyyy
 */
public class TestHistoryDbHandler extends ActivityInstrumentationTestCase2<ListHistoryActivity>
{	
	private ListHistoryActivity activity;
	private HistoryDbHandler dbH;
	
	public TestHistoryDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListHistoryActivity.class);
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbH = new HistoryDbHandler(activity);
	}
	
	/**Tests to recive a list with all completed workouts
	 */
	public void testGetPerformedWorkouts()
	{
		//Saves the size of the list with all performed workouts
		int nrOfPerformedWorkouts = dbH.getPerformedWorkoutsList().size();
		assertEquals("Performed activitys not match", nrOfPerformedWorkouts, dbH.getNumberOfPerformedWorkouts());
	}
}