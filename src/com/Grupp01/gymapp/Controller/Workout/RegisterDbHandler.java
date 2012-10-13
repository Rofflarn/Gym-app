package com.Grupp01.gymapp.Controller.Workout;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Model.Database;

public class RegisterDbHandler extends Database {

	public RegisterDbHandler(Context c)
	{
		super(c);
	}



	public void addCardioSet(int sec, int min, float distance, int workoutId, int exerciseId)
	{
		int duration = sec + (min*60);
		open();
		ourDatabase.execSQL("INSERT INTO Sets (SetDistance, WorkoutId, SetDuration, SetTime, ExerciseId) VALUES "
				+ "(" + distance + ", " + workoutId + ", " + duration +  ", " + "datetime('now')" + ", " + exerciseId + ");");
		close();
	}

	public void addDynamicSet(int weight, int reps, int workoutId, int exerciseId)
	{
		open();
		ourDatabase.execSQL("INSERT INTO Sets(SetReps, SetWeight, WorkoutId, ExerciseId) VALUES " +
				"(" + reps + ", " + weight + ", " + workoutId + ", " + exerciseId + ");");
		close();
	}

	public void addStaticSet(int min, int sec, int weight, int workoutId, int exerciseId)
	{
		int duration = sec + (min*60);
		open();
		ourDatabase.execSQL("INSERT INTO Sets(SetWeight, WorkoutId, SetDuration, ExerciseId) VALUES " +
				"(" + weight + ", " + workoutId + ", " + duration + ", " + exerciseId + ");");
		close();
	}

	public List<SetsData> getPreviouslyCardioSets(int workoutId, int exerciseId, int exerciseTypeId)
	{
		System.out.println("Inne i get PreviouslySets");
		List<SetsData> cardioSetsList = new LinkedList<SetsData>();
		open();	
		Cursor c = ourDatabase.rawQuery("SELECT Sets.SetDuration, Sets.SetDistance FROM Sets, Exercises WHERE Sets.WorkoutId = " + workoutId +" AND " +
				"Sets.ExerciseId = " + exerciseId + " AND Exercises.ExerciseId = Sets.ExerciseId AND " +
				"Exercises.ExerciseTypeId = " + exerciseTypeId + " ORDER BY SetId DESC LIMIT 4;", null);
		c.moveToFirst();
		int duration = c.getColumnIndex("SetDuration");
		int distance = c.getColumnIndex("SetDistance");

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			System.out.println("Inne i get PreviouslySetsFoorloop");

			String durationString = (((int) (c.getInt(duration) / 3600)) + ":" + (((int) (c.getInt(duration) / 60)) % 60) + ":" + (c.getInt(duration) % 60)); 
			cardioSetsList.add(new SetsData(durationString,c.getInt(distance)));
		}
		System.out.println("Efter Lopp");
		c.close();
		close();
		return cardioSetsList;
	}

	public List<SetsData> getPreviouslyDynamicSets(int workoutId, int exerciseId, int exerciseTypeId)
	{
		List<SetsData> dynamicSetsList = new LinkedList<SetsData>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT Sets.SetWeight, Sets.SetReps FROM Sets, Exercises WHERE Sets.WorkoutId = " + workoutId +" AND " +
				"Sets.ExerciseId = " + exerciseId + " AND Exercises.ExerciseId = Sets.ExerciseId AND " +
				"Exercises.ExerciseTypeId = " + exerciseTypeId + " ORDER BY SetId DESC LIMIT 4;", null);
		c.moveToFirst();
		int weight = c.getColumnIndex("SetWeight");
		int reps = c.getColumnIndex("SetReps");

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			dynamicSetsList.add(new SetsData(c.getInt(weight),c.getInt(reps)));
			System.out.println(c.getInt(weight) + " " + c.getInt(reps));
		}

		c.close();
		close();
		return dynamicSetsList;
	}


	public List<SetsData> getPreviouslyStaticSets(int workoutId, int exerciseId, int exerciseTypeId)
	{
		List<SetsData> staticSetsList = new LinkedList<SetsData>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT Sets.SetDuration, Sets.SetWeight FROM Sets, Exercises WHERE Sets.WorkoutId = " + workoutId +" AND " +
				"Sets.ExerciseId = " + exerciseId + " AND Exercises.ExerciseId = Sets.ExerciseId AND " +
				"Exercises.ExerciseTypeId = " + exerciseTypeId + " ORDER BY SetId DESC LIMIT 4;", null);
		c.moveToFirst();
		int duration = c.getColumnIndex("SetDuration");
		int weight = c.getColumnIndex("SetWeight");

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			String durationString = (((int) (c.getInt(duration) / 3600)) + ":" + (((int) (c.getInt(duration) / 60)) % 60) + ":" + (c.getInt(duration) % 60)); 
			staticSetsList.add(new SetsData(c.getInt(weight),durationString));
		}

		c.close();
		close();
		return staticSetsList;
	}

	public void removeLatestCardioSet(int setCardioId)
	{
		open();
		ourDatabase.execSQL("DELETE FROM Sets WHERE SetId="+setCardioId+";");
		getLatestCardioSetId();
		close();
	}

	public int getLatestCardioSetId()

	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT MAX(SetId) FROM Sets", null);
		c.moveToFirst();
		int latestRowId = c.getInt(0);
		System.out.println(c.getInt(0));
		c.close();
		close();
		return latestRowId;
	}

}
