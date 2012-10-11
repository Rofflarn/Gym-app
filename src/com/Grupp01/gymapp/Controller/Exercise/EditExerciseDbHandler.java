/*This file is part of Gymapp.
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Model.Database;

public class EditExerciseDbHandler extends Database {
	
	
		public EditExerciseDbHandler(Context c)
		{
			super(c);
		}
		
		public ExerciseData getExerciseById(int exerciseId)
		{
			open();
			Cursor c = ourDatabase.rawQuery("SELECT * FROM Exercises WHERE ExerciseId='" + exerciseId + "';", null);
			c.moveToFirst();
			int id = c.getColumnIndex("ExerciseId");
			int pri = c.getColumnIndex("ExercisePri");
			int sec = c.getColumnIndex("ExerciseSec");
			int name = c.getColumnIndex("ExerciseName");
			int note = c.getColumnIndex("ExerciseDesc");
			int desc = c.getColumnIndex("ExerciseNote");
			int sport = c.getColumnIndex("ExerciseSportId");
			int type = c.getColumnIndex("ExerciseTypeId");
			ExerciseData temp = new ExerciseData(c.getInt(id), c.getInt(pri), c.getInt(sec), c.getString(name), c.getString(note), c.getString(desc), c.getInt(sport), c.getInt(type));
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
			//Forlopp som går igenom hela databasen, alla kolummer
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
		public void editExercise(ExerciseData exerciseData)
		{
			open();
			ourDatabase.execSQL("UPDATE Exercises SET ExercisePri = '" + exerciseData.getPri() + "', ExerciseSec = '" +
					exerciseData.getSec() + "', ExerciseDesc = '" + exerciseData.getDesc() + "', ExerciseNote = '" + 
					exerciseData.getNote() + "', ExerciseSportId = '" + exerciseData.getSportId() + "', ExerciseTypeId = '" + 
					exerciseData.getTypeId() + "' WHERE ExerciseId = '" + exerciseData.getId() + "';");
			close();
		}
		/*
		public List<ExerciseData> getExercisesByTypeId(int ExerciseTypeId){
			
			List<ExerciseData> ExerciseDataList = new LinkedList<ExerciseData>();
			
			Cursor c = ourDatabase.rawQuery("SELECT * FROM Exercises WHERE ExerciseTypeId = '" + ExerciseTypeId + "';", null);
			c.moveToFirst();
			int id = c.getColumnIndex("ExerciseId");
			int pri = c.getColumnIndex("ExercisePri");
			int sec = c.getColumnIndex("ExerciseSec");
			int name = c.getColumnIndex("ExerciseName");
			int note = c.getColumnIndex("ExerciseDesc");
			int desc = c.getColumnIndex("ExerciseNote");
			int type = c.getColumnIndex("ExerciseTypeId");
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			{
				ExerciseDataList.add(new ExerciseData(c.getInt(id), c.getInt(pri), c.getInt(sec), c.getString(name), c.getString(note), c.getString(desc), c.getInt(type)));
			}
			c.close();
			close();
			return ExerciseDataList;
		}*/

}
