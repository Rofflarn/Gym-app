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

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Model.Database;

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed 
 * @date 
 * This class contains the necessary methods for accessing the database from the EditExercise
 * part of the GUI.
 */
public class EditExerciseDbHandler extends Database {

	/**
	 * This method forwards the Context to the superclass.
	 * @param c Reference to calling object.
	 */
	public EditExerciseDbHandler(Context c)
	{
		super(c);
	}

	/**
	 * 
	 * @param exerciseId
	 * @return
	 */
	public ExerciseData getExerciseById(int exerciseId)
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Exercises WHERE ExerciseId='" + 
		exerciseId + "';", null);
		c.moveToFirst();
		int id = c.getColumnIndex("ExerciseId");
		int pri = c.getColumnIndex("ExercisePri");
		int sec = c.getColumnIndex("ExerciseSec");
		int name = c.getColumnIndex("ExerciseName");
		int note = c.getColumnIndex("ExerciseDesc");
		int desc = c.getColumnIndex("ExerciseNote");
		int sport = c.getColumnIndex("ExerciseSportId");
		int type = c.getColumnIndex("ExerciseTypeId");
		ExerciseData temp = new ExerciseData(c.getInt(id), c.getInt(pri), c.getInt(sec), 
				c.getString(name), c.getString(note), c.getString(desc), c.getInt(sport), 
				c.getInt(type));
		c.close();
		close();
		return temp;
	}

	public List<IdName> getExerciseTypes()
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM ExerciseTypes;", null);
		List<IdName> idNameList = new LinkedList<IdName>();
		c.moveToFirst();
		int id = c.getColumnIndex("ExerciseTypeId");
		int name = c.getColumnIndex("ExerciseTypeName");
		//Loop through cursor c
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			idNameList.add(new IdName(c.getInt(id),c.getString(name)));
		}
		c.close();
		close();
		return idNameList;
	}

	public List<IdName> getSports()
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT SportId, SportName FROM Sports;", null);
		List<IdName> idNameList = new LinkedList<IdName>();
		c.moveToFirst();
		int id = c.getColumnIndex("SportId");
		int name = c.getColumnIndex("SportName");

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			idNameList.add(new IdName(c.getInt(id),c.getString(name)));
		}
		c.close();
		close();
		return idNameList;
	}

	public List<IdName> getMuscles()
	{
		Cursor c = ourDatabase.rawQuery("SELECT MuscleId, MuscleName FROM Muscles;", null);
		List<IdName> idNameList = new LinkedList<IdName>();

		c.moveToFirst();
		int id = c.getColumnIndex("MuscleId");
		int name = c.getColumnIndex("MuscleName");
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			idNameList.add(new IdName(c.getInt(id),c.getString(name)));
		}
		c.close();
		close();
		return idNameList;
	}
	
	/**
	 * 
	 * @param exerciseData
	 */
	public void editExercise(ExerciseData exerciseData)
	{
		open();
		ourDatabase.execSQL("UPDATE Exercises SET ExercisePri = '" + exerciseData.getPri() + 
				"', ExerciseSec = '" +
				exerciseData.getSec() + "', ExerciseDesc = '" + exerciseData.getDesc() + 
				"', ExerciseNote = '" + 
				exerciseData.getNote() + "', ExerciseSportId = '" + exerciseData.getSportId() + 
				"', ExerciseTypeId = '" + 
				exerciseData.getTypeId() + "' WHERE ExerciseId = '" + exerciseData.getId() + "';");
		close();
	}
	
	/**
	 * This method return the number of Exercises in the table Exercises. This is only used for
	 * testing purposes.
	 * @return The number of sports.
	 */
	public int getNumberOfSports(){
		open();
		Cursor c = ourDatabase.rawQuery("SELECT COUNT(*) FROM Sports;", null);
		c.moveToFirst();
		int count = c.getInt(0);
		close();
		return count;
	}
	
	/**
	 * This method return the number of Exercises in the table Exercises. This is only used for
	 * testing purposes.
	 * @return The number of exercises.
	 */
	public int getNumberOMuscles(){
		open();
		Cursor c = ourDatabase.rawQuery("SELECT COUNT(*) FROM Muscles;", null);
		c.moveToFirst();
		int count = c.getInt(0);
		close();
		return count;
	}
}
