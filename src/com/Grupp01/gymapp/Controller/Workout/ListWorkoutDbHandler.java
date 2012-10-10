package com.Grupp01.gymapp.Controller.Workout;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Controller.Exercise.ExerciseData;
import com.Grupp01.gymapp.Model.Database;

public class ListWorkoutDbHandler extends Database {

	
	public ListWorkoutDbHandler(Context c)
	{
		super(c);
	}
	
	public List<IdName> getWorkoutIdName()
	{
		
		open();
		Cursor c = ourDatabase.rawQuery("SELECT WorkoutTemplateId, WorkoutTemplateName FROM WorkoutTemplates;", null);
		List<IdName> idNameList = new LinkedList<IdName>();
		c.moveToFirst();
		int id = c.getColumnIndex("WorkoutTemplateId");
		int name = c.getColumnIndex("WorkoutTemplateName");
		//Forlopp som går igenom hela databasen, alla kolummer
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
	public IdName getWorkoutIdNameById(int workoutId)
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT WorkoutTemplateId, WorkoutTemplateName FROM WorkoutTemplates WHERE " + 
										"WorkoutTemplateId= '" + workoutId + "';", null);
		c.moveToFirst();
		IdName temp = new IdName(c.getInt(c.getColumnIndex("WorkoutTemplateId")),c.getString(c.getColumnIndex("WorkoutTemplateName")));
		c.close();
		close();
		return temp;
		
	}
	
	
	/**
	 * 
	 * @param workoutName
	 */
	public void putNewWorkout(String workoutName)
	{
		open();
		ourDatabase.execSQL("INSERT INTO WorkoutTemplates (WorkoutTemplateName) VALUES ('" + workoutName + "');");
		close();
	}
	
	/**
	 * 
	 * @param workoutTemplateId
	 * @return List<Integer> that contains id for all exercises that are in a workout
	 */
	public List<Integer> getExercisesbyWorkoutId(int workoutTemplateId)
	{
		List<Integer> integerList = new LinkedList<Integer>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT ExerciseId FROM WorkoutTemplateExercises WHERE WorkoutTemplateId='" + workoutTemplateId + "';", null);
		c.moveToFirst();
		int id = c.getColumnIndex("ExerciseId");
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
	 * @return LinkedList of ExerciseData, ExerciseData contains ExerciseId, ExerciseName and ExerciseTypeId
	 */
	public List<ExerciseData> getExerciseIdNameById(List<Integer> integerList)
	{
		List<ExerciseData> exerciseDataList = new LinkedList<ExerciseData>();
		open();
		for(Integer exerciseId: integerList)
		{
			Cursor d = ourDatabase.rawQuery("SELECT ExerciseId, ExerciseName, ExerciseTypeId FROM Exercises WHERE ExerciseId='" + exerciseId + "';", null);
			d.moveToFirst();
			int id = d.getColumnIndex("ExerciseId");
			int name = d.getColumnIndex("ExerciseName");
			int typeId = d.getColumnIndex("ExerciseTypeId");
			exerciseDataList.add(new ExerciseData(d.getInt(id), d.getString(name), d.getInt(typeId)));
			d.close();
			
		}
		close();
		return exerciseDataList;
	}
}
