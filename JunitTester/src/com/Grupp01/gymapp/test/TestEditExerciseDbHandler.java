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

import java.util.List;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Controller.Exercise.EditExerciseDbHandler;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by xxxxxxxxxxx
 * @date dd/mm/yyyy
 */
public class TestEditExerciseDbHandler extends ActivityInstrumentationTestCase2<ListExerciseActivity>
{	
	private ListExerciseActivity activity;
	private EditExerciseDbHandler dbE;
	
	public TestEditExerciseDbHandler()	
	{		
		super("com.Grupp01.gymapp", ListExerciseActivity.class);
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbE= new EditExerciseDbHandler(activity);
	}
	
	/**Tests to receive a exercise whit specific id
	 */
	public void testGetExerciseById()
	{
		dbE.open();
		ExerciseData exerciseData = dbE.getExerciseById(1);
		assertEquals("Wrong id", exerciseData.getId(), 1);
		assertNotNull("No name", exerciseData.getName());
		assertNotNull("No type id", exerciseData.getTypeId());
		dbE.close();
	}
	
	/**Tests to receive a list with all sports
	 */
	public void testGetSports()
	{
		dbE.open();
		List<IdName> allSports = dbE.getSports();
		assertEquals("List not contains rigth nr of sport", allSports.size(), dbE.getNumberOfSports());
		dbE.close();
	}
	
	/**Tests to receive a list whit all Muscles
	 */
	public void testGetMuscles()
	{
		dbE.open();
		List<IdName> allMuscles = dbE.getMuscles();
		assertEquals("List not contains rigth nr of muscles", allMuscles.size(), dbE.getNumberOfMuscles());
		dbE.close();
	}
	
	/**Tests to edit a exercise whit specific id
	 */
	public void testEditExercise()
	{
		dbE.open();
		ExerciseData exerciseData = dbE.getExerciseById(1);
		exerciseData.putDesc("Edit");
		dbE.editExercise(exerciseData);
		assertTrue("Wrong Description", dbE.getExerciseById(1).getDesc().equals("Edit"));
		dbE.close();
	}
}