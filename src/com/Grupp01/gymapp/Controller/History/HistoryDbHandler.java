package com.Grupp01.gymapp.Controller.History;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.Workout.SetsData;
import com.Grupp01.gymapp.Model.Database;

public class HistoryDbHandler extends Database {
	
	public final static int NUMBER_SECONDS_IN_HOUR  = 3600; //Used for timeformating equation
	public final static int NUMBER_SECONDS_IN_MIN = 60; //Used for timeformating equation
	public final static int NUMBER_OF_LATEST_SET = 4; //Number of sets that are going to be shown
	//in text: "Latest sets"



	public HistoryDbHandler(Context c)
	{
		super(c);
	}

	/**
	 * This method is used for return every unice exerciseId from a performed workout
	 * @param workoutId WorkoutId for the selected performed workout.
	 * @return LinkedList containing Integers of all unice ExerciseId in a performed workout
	 */
	public List<Integer> getAllExerciseIdFromPerformedWorkout(int workoutId)
	{
		List<Integer> exerciseIdList = new LinkedList<Integer>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT ExerciseId FROM Sets WHERE WorkoutId="+
		workoutId + ";", null); 
		c.moveToFirst();
		int id = c.getColumnIndex("ExerciseId");
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			int temp = c.getInt(id);
			//Om exerciseId inte finns i listan, lägg till i listan.
			if(!(exerciseIdList.contains(temp)))
			{
				exerciseIdList.add(temp);
			}
			
		}
		return exerciseIdList;
		
	}
	
	/**
	 * Gets exerciseTypeId for an exerciseId.
	 * @param exerciseIdList A LinkedList<Integer> of exerciseId.
	 * @return LinkedList<ExerciseIdTypeId> with exerciseId and exerciseTypeId as parameters.
	 */
	public List<ExerciseIdTypeId> getExerciseIdTypeIdList(List<Integer> exerciseIdList)
	{
		List<ExerciseIdTypeId> idTypeIdList = new LinkedList<ExerciseIdTypeId>();
		open();
		for(Integer id: exerciseIdList)
		{
			Cursor c = ourDatabase.rawQuery("SELECT ExerciseTypeId FROM Exercises Where " +
					"ExerciseId=" + id + ";", null);
			c.moveToFirst();
			int typeId = c.getColumnIndex("ExerciseTypeId");
			idTypeIdList.add(new ExerciseIdTypeId(id,c.getInt(typeId)));
			c.close();
		}
		close();
		return idTypeIdList;
	}
	
	/**
	 * This method returns a list of all performed workouts in form PerformedWorkoutData objects
	 * @return List of performerd workouts in PerformedWorkoutData objects
	 */
	public List<PerformedWorkoutData> getPerformedWorkoutsList()
	{
		List<PerformedWorkoutData> workoutsList = new LinkedList<PerformedWorkoutData>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Workouts;", null);
		c.moveToFirst();
		int id = c.getColumnIndex("WorkoutID");
		int date = c.getColumnIndex("WorkoutDate");
		int name = c.getColumnIndex("WorkoutName");
		//Forlopp som går igenom hela databastabellen, alla kolummer
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			workoutsList.add(new PerformedWorkoutData(c.getInt(id),c.getString(date),c.getString(name)));
		}
		c.close();
		close();
		return workoutsList;
	}

	public List<SetsData> getPerformedCardioSets(int exerciseId, int workoutId)
	{
		List<SetsData> setsList = new LinkedList<SetsData>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Sets WHERE WorkoutId = " + workoutId +
				" AND " + "ExerciseId = " + exerciseId + ";", null);
		c.moveToFirst();
		int setDistance = c.getColumnIndex("SetDistance");
		int setDuration = c.getColumnIndex("SetDuration");
		//Forlopp som går igenom hela databastabellen, alla kolummer
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			String durationString = (((int) (c.getInt(setDuration) / NUMBER_SECONDS_IN_HOUR)) + ":"
			+ (((int) (c.getInt(setDuration)/ NUMBER_SECONDS_IN_MIN)) % NUMBER_SECONDS_IN_MIN) + ":"
			+ (c.getInt(setDuration) % NUMBER_SECONDS_IN_MIN)); 
			setsList.add(new SetsData(durationString,c.getInt(setDistance)));
		}	
		c.close();
		close();
		return setsList;
		
		
}
}

