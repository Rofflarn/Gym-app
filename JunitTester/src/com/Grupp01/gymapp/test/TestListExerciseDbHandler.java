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
import com.Grupp01.gymapp.Controller.Exercise.ListExerciseDbHandler;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by xxxxxxxxxxx
 * @date dd/mm/yyyy
 */
public class TestListExerciseDbHandler extends ActivityInstrumentationTestCase2<ListExerciseActivity>
{	
	private ListExerciseActivity activity;
	private ListExerciseDbHandler dbL;
	//Constants
	private final int MANY_EXERCISES = 100;
	private final int ZERO_EXERCISES = 0;
	
	public TestListExerciseDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListExerciseActivity.class);
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbL= new ListExerciseDbHandler(activity);
	}
	
	/**Tests to add 100 exercises
	 */
	public void testAddManyExercises()
	{
		String name = "n";
		dbL.open();
		int nrOfExercise = dbL.getNumberOfExercises();
		for(int i = 0; i < 100; i++)
		{		
			dbL.addExercise(name);
			name = name + "n";
		}
		assertEquals("Did not work to add exercises", MANY_EXERCISES, dbL.getNumberOfExercises() - nrOfExercise);
		dbL.close();
	}
	
	/**Tests to add one exercise
	 */
	public void testAddExercise()
	{
		dbL.open();
		int nrOfExercise = dbL.getNumberOfExercises();
		dbL.addExercise("test");
		assertEquals("Did not work to add exercise", nrOfExercise + 1, dbL.getNumberOfExercises());
		dbL.close();
	}
	
	/**Tests to delete all exercises
	 */
	public void testDeleteAllExercises()	
	{		
	  dbL.open();
	  int nrOfExercises = dbL.getNumberOfExercises();
	  for(int i = 0; i <= nrOfExercises; i++)
	  {
		  dbL.deleteExercise(i);
	  }
	  assertEquals("Did not work to delete all exercises from database", ZERO_EXERCISES, dbL.getNumberOfExercises());
	  dbL.close();
	}
	
	/**Tests to delete on exercise
	 */
	public void testDeleteOneExercise()	
	{
	  dbL.open();
	  int nrOfExercises = dbL.getNumberOfExercises();
	  int exerciseId = dbL.addExercise("test");
	  assertEquals("Did not work to add an exercise", dbL.getNumberOfExercises(), nrOfExercises + 1);
	  dbL.deleteExercise(exerciseId);
	  assertEquals("Did not work to delete exercise from database", nrOfExercises, dbL.getNumberOfExercises());
	  dbL.close();
	}
	
	/**Tests to receive a list with IdName
	 */
	public void testgetExerciseIdName()
	{
		assertEquals("Not match", dbL.getNumberOfExercises(), dbL.getExerciseIdName().size());
	}
}