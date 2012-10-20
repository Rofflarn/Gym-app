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
package com.Grupp01.gymapp.Controller.Exercise;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Model.Database;

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Erik
 * @date 20/10/12
 * This class contains the necessary methods for accessing the database from the ListExercise part
 * of the GUI.
 */
public class ListExerciseDbHandler extends Database {

	/**
	 * This method forwards the Context to the superclass.
	 * @param c Reference to calling object.
	 */
	public ListExerciseDbHandler(Context c)
	{
		super(c);
	}

	/**
	 * This method gets a list of all the elements in the Exercise table. The list contains 
	 * IdName objects.
	 * @return A list of IdNames objects containing ExerciseId and ExerciseName.
	 */
	public List<IdName> getExerciseIdName()
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT ExerciseId, ExerciseName FROM Exercises;", null);
		List<IdName> idNameList = new LinkedList<IdName>();
		c.moveToFirst();
		int id = c.getColumnIndex("ExerciseId");
		int name = c.getColumnIndex("ExerciseName");
		//Procedure that runs through the cursor.
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			idNameList.add(new IdName(c.getInt(id),c.getString(name)));
		}
		c.close();
		close();
		return idNameList;
	}
	
	/**
	 * Adds new Exercise to database.
	 * @param name
	 * @return
	 */
	public int addExercise(String name)
	{
		open();
		ContentValues values = new ContentValues();
		values.put("ExerciseName", name);
		int id = (int) ourDatabase.insert("Exercises", null, values);
		close();
		return id;
	}
	
	/**
	 * This method deletes the exercise with the specified id and all associated data in other
	 * tables.
	 * @param exerciseId The id of the deleted exercise.
	 */
	public void deleteExercise(int exerciseId)
	{
		open();
		//Delete WorkoutTemplateExercises associated with Exercise
		ourDatabase.execSQL("DELETE FROM WorkoutTemplateExercises WHERE ExerciseId = '" + exerciseId + "';");
		//Delete sets associated with Exercise
		ourDatabase.execSQL("DELETE FROM Sets WHERE ExerciseId = '" + exerciseId + "';");
		//Delete Exercises
		ourDatabase.execSQL("DELETE FROM Exercises WHERE ExerciseId = '" + exerciseId + "';");
		close();
	}
	
	/**
	 * This method return the number of Exercises in the table Exercises. This is only used for
	 * testing purposes.
	 * @return The number of exercises.
	 */
	public int getNumberOfExercises(){
		open();
		Cursor c = ourDatabase.rawQuery("SELECT COUNT(*) FROM Exercises;", null);
		c.moveToFirst();
		int count = c.getInt(0);
		close();
		return count;
	}
}
