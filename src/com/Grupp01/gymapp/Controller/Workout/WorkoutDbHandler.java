/*Copyright � 2012 GivDev
 *
 * This file is part of Gymapp.
 *
 * Gymapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gymapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gymapp. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.Grupp01.gymapp.Controller.Workout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.Model.Database;
import com.Grupp01.gymapp.View.Workout.ExerciseListElementData;

public class WorkoutDbHandler extends Database {
	
	private static final String EXERCISE_ID = "ExerciseId";
	private static final String EXERCISE_NAME = "ExerciseName";

	public WorkoutDbHandler(Context c)
	{
		super(c);
	}


	/**
	 * Gets all exercises id and name from databasetable Exercises and puts these values into an 
<<<<<<< HEAD
	 * IdName object. 
=======
	 * IdName object.
>>>>>>> implement_history_database
	 * @return a LinkedList with type of IdName
	 */
	public List<IdName> getWorkoutTemplatesIdName()
	{

		open();
		Cursor c = ourDatabase.rawQuery("SELECT WorkoutTemplateId, WorkoutTemplateName FROM " +
				"WorkoutTemplates;", null);
		List<IdName> idNameList = new LinkedList<IdName>();
		c.moveToFirst();
		int id = c.getColumnIndex("WorkoutTemplateId");
		int name = c.getColumnIndex("WorkoutTemplateName");
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
	 * Returns the specific
	 * @param workoutId
	 * @return Id and Name of a workout in a IdName
	 */
	public IdName getWorkoutTemplateIdNameById(int workoutId)
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT WorkoutTemplateId, WorkoutTemplateName FROM " +
				"WorkoutTemplates WHERE " +
				"WorkoutTemplateId= '" + workoutId + "';", null);
		c.moveToFirst();
		IdName temp = new IdName(c.getInt(c.getColumnIndex("WorkoutTemplateId")),c.getString(
				c.getColumnIndex("WorkoutTemplateName")));
		c.close();
		close();
		return temp;

	}


	/**
	 *
	 * @param workoutName
	 */
	public int addWorkoutTemplate(String workoutTemplateName)
	{
		open();
		ContentValues values = new ContentValues();
		values.put("WorkoutTemplateName", workoutTemplateName);
		int id = (int) (long) ourDatabase.insert("WorkoutTemplates", null, values);
		close();
		return id;
	}

	/**
	 *
	 * @param workoutTemplateId
	 * @return List<Integer> that contains id for all exercises that are in a workout
	 */
	public List<Integer> getWorkoutTemplateExerciseByWorkoutTemplateId(int workoutTemplateId)
	{
		List<Integer> integerList = new LinkedList<Integer>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT ExerciseId FROM WorkoutTemplateExercises WHERE " +
				"WorkoutTemplateId='" + workoutTemplateId + "';", null);
		c.moveToFirst();
		int id = c.getColumnIndex(EXERCISE_ID);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			integerList.add(c.getInt(id));
		}
		c.close();
		close();
		return integerList;
	}

	/**
	 * Returns all exercises in form of ExerciseData that a workout contains.
	 *
	 * @param integerList
	 * @return LinkedList of ExerciseData, ExerciseData contains ExerciseId, ExerciseName and 
<<<<<<< HEAD
	 * ExerciseTypeId.
=======
	 * ExerciseTypeId
>>>>>>> implement_history_database
	 */
	public List<ExerciseData> getExerciseIdNameById(List<Integer> integerList)
	{
		List<ExerciseData> exerciseDataList = new LinkedList<ExerciseData>();
		open();
		for(Integer exerciseId: integerList)
		{
			Cursor d = ourDatabase.rawQuery("SELECT ExerciseId, ExerciseName, ExerciseTypeId " +
					"FROM Exercises WHERE ExerciseId='" + exerciseId + "';", null);
			d.moveToFirst();
			int id = d.getColumnIndex(EXERCISE_ID);
			int name = d.getColumnIndex(EXERCISE_NAME);
			int typeId = d.getColumnIndex("ExerciseTypeId");
			exerciseDataList.add(new ExerciseData(d.getInt(id), d.getString(name), 
					d.getInt(typeId)));
			d.close();

		}
		close();
		return exerciseDataList;

	}

	/**
	 * Gets an Exercise by ExerciseId from Exercises table in database.
	 *
	 * @param exerciseId
	 * @return a Exercise in form of a ExerciseData object
	 */
	public ExerciseData getExerciseDataFromExerciseId(int exerciseId)
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Exercises WHERE ExerciseId='" + 
				exerciseId + "';", null);
		c.moveToFirst();
		int id = c.getColumnIndex(EXERCISE_ID);
		int pri = c.getColumnIndex("ExercisePri");
		int sec = c.getColumnIndex("ExerciseSec");
		int name = c.getColumnIndex(EXERCISE_NAME);
		int desc = c.getColumnIndex("ExerciseDesc");
		int note = c.getColumnIndex("ExerciseNote");
		int sportid = c.getColumnIndex("ExerciseSportId");
		int type = c.getColumnIndex("ExerciseTypeId");
		ExerciseData temp = new ExerciseData(c.getInt(id), c.getInt(pri), c.getInt(sec), 
				c.getString(name), c.getString(desc), c.getString(note), c.getInt(sportid), 
				c.getInt(type));
		c.close();
		close();
		return temp;
	}


	public ArrayList<ExerciseListElementData> getExercisesCheckedByWorkoutTemplateId(int 
			workoutTemplateId)
	{
		open();

		ArrayList<ExerciseListElementData> list = new ArrayList<ExerciseListElementData>();

		Cursor c1 = ourDatabase.rawQuery("SELECT ExerciseId, ExerciseName FROM Exercises;", null);
		Cursor c2 = ourDatabase.rawQuery("SELECT * FROM WorkoutTemplateExercises WHERE " +
				"WorkoutTemplateId = '" + workoutTemplateId + "';" , null);

		int c1Id = c1.getColumnIndex(EXERCISE_ID);
		int c2Id = c2.getColumnIndex(EXERCISE_ID);
		int name = c1.getColumnIndex(EXERCISE_NAME);

		for(c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext())
		{
			boolean isChecked = false;

			for(c2.moveToFirst(); !c2.isAfterLast(); c2.moveToNext())
			{
				if(c1.getInt(c1Id) == c2.getInt(c2Id))
				{
					isChecked = true;
				}
			}

			ExerciseListElementData exerciseListElementData = new ExerciseListElementData(
					c1.getInt(c1Id), c1.getString(name), isChecked);
			list.add(exerciseListElementData);
		}
		close();

		return list;
	}




	public int getExerciseLastTimePerformed(int exerciseId)
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT WorkoutTime FROM Workouts WHERE WorkoutId = " +
				"Sets.WorkoutId AND " +
				"Sets.ExerciseId = '" + exerciseId + "';", null);
		int workoutTime = c.getColumnIndex("WorkoutTime");
		c.moveToFirst();
		close();
		return c.getInt(workoutTime);
	}

	public void editWorkoutTemplate(ExerciseListElementData exerciseListItemData, 
			int workoutTemplateId)
	{
		open();
		//Check if already exists
		Cursor c = ourDatabase.rawQuery("SELECT COUNT (*) FROM WorkoutTemplateExercises WHERE " +
				"WorkoutTemplateId = '" + workoutTemplateId + "' " +
				"AND ExerciseId = '" + exerciseListItemData.getId() + "';", null);
		c.moveToFirst();

		if(exerciseListItemData.isChecked() )//&& c.getInt(0) == 0)
		{
			if(c.getInt(0) == 0)
			{	
				//add
				String tmp = "INSERT OR REPLACE INTO WorkoutTemplateExercises " +
						"(WorkoutTemplateId, ExerciseId) VALUES " + 
						"('" + workoutTemplateId + "', '" + exerciseListItemData.getId() + "');";
				ourDatabase.execSQL(tmp);
			}
		} else
		{
			//remove
			String tmp = "DELETE FROM WorkoutTemplateExercises WHERE WorkoutTemplateId = '" + 
					workoutTemplateId + "' AND " + "ExerciseId = '" + 
						exerciseListItemData.getId() + "';";
			ourDatabase.execSQL(tmp);
		}
		close();
	}

	public int addWorkout(String workoutName)
	{
		open();
		System.out.println(workoutName);
		ContentValues values = new ContentValues();
		values.put("WorkoutName", workoutName);
		int workoutId = (int) (long) ourDatabase.insert("Workouts", null, values);
		close();
		return workoutId;
	}

	public void deleteWorkout(int workoutId)
	{
		open();
		//Delete sets associated with workout
		ourDatabase.execSQL("DELETE FROM Sets WHERE WorkoutId = '" + workoutId + "';");
		//Delete the workout
		ourDatabase.execSQL("DELETE FROM Workouts WHERE WorkoutId = '" + workoutId + "';");
		close();
	}

	public void deleteWorkoutTemplate(int workoutTemplateId)
	{
		open();
		//Delete exercise associations to WorkoutTemplate
		ourDatabase.execSQL("DELETE FROM WorkoutTemplateExercises WHERE WorkoutTemplateId = '" + 
				workoutTemplateId + "';");
		//Delete WorkoutTemplate
		ourDatabase.execSQL("DELETE FROM WorkoutTemplates WHERE WorkoutTemplateId = '" + 
				workoutTemplateId + "';");
		//Delete WorkoutTemplate
		ourDatabase.execSQL("DELETE FROM WorkoutTemplates WHERE WorkoutTemplateId = '" + 
				workoutTemplateId + "';");
		close();
	}

	public void deleteExercise(int ExerciseId)
	{
		open();
		//Delete WorkoutTemplateExercises associated with Exercise
		ourDatabase.execSQL("DELETE FROM WorkoutTemplateExercises WHERE ExerciseId = '" + 
				ExerciseId + "';");
		//Delete sets associated with Exercise
		ourDatabase.execSQL("DELETE FROM Sets WHERE ExerciseId = '" + ExerciseId + "';");
		//Delete Exercises
		ourDatabase.execSQL("DELETE FROM WorkoutTemplates WHERE WorkoutTemplateId = '" + 
				ExerciseId + "';");
		close();
	}
}
