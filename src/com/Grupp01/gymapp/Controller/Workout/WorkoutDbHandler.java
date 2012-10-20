/*Copyright © 2012 GivDev
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

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Robert Blomberg
 * @date 
 * This class contains the necessary methods for accessing the database from the Workout part of
 * the GUI.
 */
public class WorkoutDbHandler extends Database {
	
	private static final String EXERCISE_ID = "ExerciseId";
	private static final String EXERCISE_NAME = "ExerciseName";

	/**
	 * This method forwards the Context to the superclass.
	 * @param c Reference to calling object.
	 */
	public WorkoutDbHandler(Context c)
	{
		super(c);
	}

	/**
	 * Gets all exercises id and name from database table Exercises and puts these values into an 
	 * IdName object. 
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
	 * Returns the specified workoutTemplate.
	 * @param workoutTemplateId The id of the workoutTemplate.
	 * @return Id and Name of the specified workoutTemplate in a IdName object.
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
	 * This method adds a new workoutTemplate.
	 * @param workoutTemplateName The name of the new workoutTemplate.
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
	 * Gets a List of the exercises associated with a workoutTemplate
	 * @param workoutTemplateId The id of the workoutTemplate
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
	 * @param integerList List of ExerciseId's.
	 * @return LinkedList of ExerciseData, ExerciseData contains ExerciseId, ExerciseName and 
	 * ExerciseTypeId.
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
	 * This method gets the exercise with the specified ExerciseId from the database.
	 *
	 * @param exerciseId The ExerciseId of the exercise to get.
	 * @return Returns an exercise in form of an ExerciseData object.
	 */
	public ExerciseData getExerciseDataFromExerciseId(int exerciseId)
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Exercises WHERE ExerciseId='" + 
				exerciseId + "';", null);
		c.moveToFirst();
		//Define the necessary column id's
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


	/**
	 * This method returns a list of exercises that are associated with a specified 
	 * workoutTemplate.
	 * @param workoutTemplateId 
	 * @return List of the exercises associated with a workoutTemplate
	 */
	public List<ExerciseListElementData> getExercisesCheckedByWorkoutTemplateId(int 
			workoutTemplateId)
	{
		open();
		List<ExerciseListElementData> list = new ArrayList<ExerciseListElementData>();

		//Get data from tables
		Cursor c1 = ourDatabase.rawQuery("SELECT ExerciseId, ExerciseName FROM Exercises;", null);
		Cursor c2 = ourDatabase.rawQuery("SELECT * FROM WorkoutTemplateExercises WHERE " +
				"WorkoutTemplateId = '" + workoutTemplateId + "';" , null);
		
		//Get column id's
		int c1Id = c1.getColumnIndex(EXERCISE_ID);
		int c2Id = c2.getColumnIndex(EXERCISE_ID);
		int name = c1.getColumnIndex(EXERCISE_NAME);

		//Loop through c1
		for(c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext())
		{
			boolean isChecked = false;
			//Loop through c2
			for(c2.moveToFirst(); !c2.isAfterLast(); c2.moveToNext())
			{
				//Check if reference to c1 is in c2
				if(c1.getInt(c1Id) == c2.getInt(c2Id))
				{
					isChecked = true;
				}
			}
			//Add data in the list which is returned from the method
			ExerciseListElementData exerciseListElementData = new ExerciseListElementData(
					c1.getInt(c1Id), c1.getString(name), isChecked);
			list.add(exerciseListElementData);
		}
		close();
		return list;
	}

	/**
	 * This method updates the relation table between WorkoutTemplates and Exercises.
	 * @param exerciseListItemData Contains an exercise. The data in this object will be 
	 * synchronized with the corresponding tables in the database.
	 * @param workoutTemplateId The WorkoutTemplateId that the exercise in exerciseListItemData will
	 * be connected to.
	 */
	public void editWorkoutTemplate(ExerciseListElementData exerciseListItemData, 
			int workoutTemplateId)
	{
		open();
		
		Cursor c = ourDatabase.rawQuery("SELECT COUNT (*) FROM WorkoutTemplateExercises WHERE " +
				"WorkoutTemplateId = '" + workoutTemplateId + "' " +
				"AND ExerciseId = '" + exerciseListItemData.getId() + "';", null);
		c.moveToFirst();
		
		/*
		 * If the the ListItem is checked, add the data to the database.
		 * Otherwise remove the data from database.
		 */
		if(exerciseListItemData.isChecked() )
		{
			//Only add the data if it does not already exist.
			if(c.getInt(0) == 0)
			{	
				//Add data to db
				String tmp = "INSERT INTO WorkoutTemplateExercises " +
						"(WorkoutTemplateId, ExerciseId) VALUES " + 
						"('" + workoutTemplateId + "', '" + exerciseListItemData.getId() + "');";
				ourDatabase.execSQL(tmp);
			}
		} else
		{
			//Remove data from db
			String tmp = "DELETE FROM WorkoutTemplateExercises WHERE WorkoutTemplateId = '" + 
					workoutTemplateId + "' AND " + "ExerciseId = '" + 
						exerciseListItemData.getId() + "';";
			ourDatabase.execSQL(tmp);
		}
		close();
	}

	/**
	 * This method adds a new workout to the database.
	 * @param workoutName The name of the new workout.
	 * @return The WorkoutId of the added workout.
	 */
	public int addWorkout(String workoutName)
	{
		open();
		ContentValues values = new ContentValues();
		values.put("WorkoutName", workoutName);
		int workoutId = (int) (long) ourDatabase.insert("Workouts", null, values);
		close();
		return workoutId;
	}

	/**
	 * This method deletes a workout and the associated data from the database.
	 * @param workoutId The WorkoutId of the workout to delete.
	 */
	public void deleteWorkout(int workoutId)
	{
		open();
		//Delete sets associated with workout
		ourDatabase.execSQL("DELETE FROM Sets WHERE WorkoutId = '" + workoutId + "';");
		//Delete the workout
		ourDatabase.execSQL("DELETE FROM Workouts WHERE WorkoutId = '" + workoutId + "';");
		close();
	}

	/**
	 * This method deletes a workoutTemplate and the associated data from the database.
	 * @param workoutId The WorkoutTemplateId of the workoutTemplate to delete.
	 */
	public void deleteWorkoutTemplate(int workoutTemplateId)
	{
		open();
		//Delete exercise associations to WorkoutTemplate
		ourDatabase.execSQL("DELETE FROM WorkoutTemplateExercises WHERE WorkoutTemplateId = '" + 
				workoutTemplateId + "';");
		//Delete WorkoutTemplate
		ourDatabase.execSQL("DELETE FROM WorkoutTemplates WHERE WorkoutTemplateId = '" + 
				workoutTemplateId + "';");
		close();
	}

	/**
	 * This method deletes an exercise from the database.
	 * @param exerciseId The ExerciseId of the exercise to delete.
	 */
	public void deleteExercise(int exerciseId)
	{
		open();
		//Delete WorkoutTemplateExercises associated with Exercise
		ourDatabase.execSQL("DELETE FROM WorkoutTemplateExercises WHERE ExerciseId = '" + 
				exerciseId + "';");
		//Delete sets associated with Exercise
		ourDatabase.execSQL("DELETE FROM Sets WHERE ExerciseId = '" + exerciseId + "';");
		//Delete Exercises
		ourDatabase.execSQL("DELETE FROM Exercises WHERE ExerciseId = '" + 
				exerciseId + "';");
		close();
	}
}
